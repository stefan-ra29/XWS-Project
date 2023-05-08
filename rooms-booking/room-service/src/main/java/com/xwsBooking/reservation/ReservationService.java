package com.xwsBooking.reservation;

import com.xwsBooking.availability.Availability;
import com.xwsBooking.availability.AvailabilityRepository;
import com.xwsBooking.price.Price;
import com.xwsBooking.price.PriceRepository;
import com.xwsBooking.price.PriceType;
import com.xwsBooking.room.*;
import io.grpc.stub.StreamObserver;
import lombok.RequiredArgsConstructor;
import net.devh.boot.grpc.server.service.GrpcService;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

@RequiredArgsConstructor
@GrpcService
@Service
public class ReservationService extends ReservationServiceGrpc.ReservationServiceImplBase {

    private final RoomRepository roomRepository;
    private final AvailabilityRepository availabilityRepository;
    private final PriceRepository priceRepository;
    private final RoomImageRepository roomImageRepository;
    private final ReservationRequestRepository reservationRequestRepository;


    @Override
    public void sendReservationRequest(ReservationRequestRequest request, StreamObserver<ReservationRequestResponse> responseObserver) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        formatter = formatter.withLocale(Locale.US);  // Locale specifies human language for translating, and cultural norms for lowercase/uppercase and abbreviations and such. Example: Locale.US or Locale.CANADA_FRENCH
        LocalDate dateFrom = LocalDate.parse(request.getFromDate(), formatter);
        LocalDate dateTo = LocalDate.parse(request.getToDate(), formatter);


        ReservationRequest reservationRequest = ReservationRequest.builder()
                .customerId(request.getGuestId())
                .room(roomRepository.findById(request.getRoomId()).get())
                .numberOfGuests(request.getNumberOfGuests())
                .fromDate(dateFrom)
                .toDate(dateTo)
                .build();

        reservationRequestRepository.save(reservationRequest);

        ReservationRequestResponse reservationRequestResponse = ReservationRequestResponse.newBuilder().setSuccessfullySaved(true).build();
        responseObserver.onNext(reservationRequestResponse);
        responseObserver.onCompleted();
    }

    @Override
    public void searchRooms(SearchRequest request, StreamObserver<SearchResponse> responseObserver) {
        List<Room> roomsOnLocation = roomRepository.findRoomsByLocation(request.getLocation());
        List<SearchResultGrpcDTO> response = new ArrayList<>();

        for(Room room : roomsOnLocation) {
            if(isAvailable(room, request)) {

                double totalPrice = calculateTotalPrice(request, room);
                double pricePerDay = calculatePricePerDay(totalPrice, request);

                List<RoomImage> roomImages = roomImageRepository.findAllByRoom_Id(room.getId());
                List<String> imageLinks = new ArrayList<>();
                for(RoomImage roomImage : roomImages) {
                    imageLinks.add(roomImage.getPath());
                }

                SearchResultGrpcDTO searchResultGrpcDTO = SearchResultGrpcDTO.newBuilder()
                        .setLocation(room.getLocation())
                        .setName(room.getName())
                        .setHasAC(room.isAirConditioning())
                        .setHasFreeParking(room.isFreeParking())
                        .setHasKitchen(room.isKitchen())
                        .setHasWifi(room.isWifi())
                        .setMinGuests(room.getMinNumberOfGuests())
                        .setMaxGuests(room.getMaxNumberOfGuests())
                        .setTotalPrice(totalPrice)
                        .setPricePerDay(pricePerDay)
                        .setId(room.getId())
                        .addAllImages(imageLinks)
                        .build();

                response.add(searchResultGrpcDTO);
            }
        }

        SearchResponse searchResponse = SearchResponse.newBuilder().addAllRooms(response).build();

        responseObserver.onNext(searchResponse);
        responseObserver.onCompleted();
    }

    public double calculatePricePerDay(double totalPrice, SearchRequest request) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        formatter = formatter.withLocale(Locale.US);  // Locale specifies human language for translating, and cultural norms for lowercase/uppercase and abbreviations and such. Example: Locale.US or Locale.CANADA_FRENCH
        LocalDate dateFrom = LocalDate.parse(request.getDateFrom(), formatter);
        LocalDate dateTo = LocalDate.parse(request.getDateTo(), formatter);
        long numberOfDays = ChronoUnit.DAYS.between(dateFrom, dateTo);

        return totalPrice/numberOfDays;
    }

    public double calculateTotalPrice(SearchRequest request, Room room) {
        double totalPrice = 0;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        formatter = formatter.withLocale(Locale.US);  // Locale specifies human language for translating, and cultural norms for lowercase/uppercase and abbreviations and such. Example: Locale.US or Locale.CANADA_FRENCH
        LocalDate dateFrom = LocalDate.parse(request.getDateFrom(), formatter);
        LocalDate dateTo = LocalDate.parse(request.getDateTo(), formatter);

        List<Price> pricesForRoom = priceRepository.findPricesByRoom_Id(room.getId());

        while(dateFrom.isBefore(dateTo)) {

            for(Price price : pricesForRoom) {
                if((dateFrom.isAfter(price.getFromDate()) || dateFrom.equals(price.getFromDate())) &&
                        (dateFrom.isBefore(price.getToDate()) || dateFrom.equals(price.getToDate()))) {

                    if(price.getType() == PriceType.BY_ROOM_UNIT) {
                        totalPrice += price.getValue();
                    }
                    else {
                        totalPrice += price.getValue()*request.getNumberOfGuests();
                    }

                    break;
                }
            }

            dateFrom = dateFrom.plusDays(1);
            System.out.println(dateFrom);
        }

        return totalPrice;
    }

    public boolean isAvailable(Room room, SearchRequest request) {
        if(room.getMinNumberOfGuests() <= request.getNumberOfGuests() && room.getMaxNumberOfGuests() >= request.getNumberOfGuests()) {
            for(Availability availability : availabilityRepository.findAvailabilitiesByRoom_Id(room.getId())) {
                if(datesFit(availability, request)) {
                    return true;
                }
            }
        }

        return false;
    }

    public boolean datesFit(Availability availability, SearchRequest request) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        formatter = formatter.withLocale(Locale.US);  // Locale specifies human language for translating, and cultural norms for lowercase/uppercase and abbreviations and such. Example: Locale.US or Locale.CANADA_FRENCH
        LocalDate dateFrom = LocalDate.parse(request.getDateFrom(), formatter);
        LocalDate dateTo = LocalDate.parse(request.getDateTo(), formatter);

        if( (availability.getFromDate().isBefore(dateFrom) || availability.getFromDate().equals(dateFrom)) &&
                (availability.getToDate().isAfter(dateTo) || availability.getToDate().equals(dateTo))) {

            return true;
        }

        return false;
    }
}
