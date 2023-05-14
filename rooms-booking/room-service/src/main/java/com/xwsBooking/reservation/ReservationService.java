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
    private final ApprovedReservationRepository approvedReservationRepository;
    private final GuestCancellationRepository guestCancellationRepository;


    @Override
    public void deleteReservationRequest(DeleteReservationRequestRequest request, StreamObserver<DeleteReservationRequestResponse> responseObserver) {
        reservationRequestRepository.deleteById(request.getRequestId());

        DeleteReservationRequestResponse response = DeleteReservationRequestResponse.newBuilder().build();
        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }

    @Override
    public void getReservationRequestsByGuest(ReservationRequestsByGuestRequest request, StreamObserver<ReservationRequestsByGuestResponse> responseObserver) {
        List<ReservationRequest> reservationRequestsByGuest = reservationRequestRepository.findAllByCustomerId(request.getGuestId());
        List<ReservationRequestGrpcDTO> reservationRequestGrpcDTOS = new ArrayList<>();

        for(ReservationRequest reservationRequest : reservationRequestsByGuest) {
            ReservationRequestGrpcDTO reservationRequestGrpcDTO = ReservationRequestGrpcDTO.newBuilder()
                    .setNumberOfGuests(reservationRequest.getNumberOfGuests())
                    .setRequestId(reservationRequest.getId())
                    .setLocation(reservationRequest.getRoom().getLocation())
                    .setFromDate(reservationRequest.getFromDate().toString())
                    .setToDate(reservationRequest.getToDate().toString())
                    .setRoomName(reservationRequest.getRoom().getName())
                    .build();

            reservationRequestGrpcDTOS.add(reservationRequestGrpcDTO);
        }

        ReservationRequestsByGuestResponse response = ReservationRequestsByGuestResponse.newBuilder().addAllReservationRequests(reservationRequestGrpcDTOS).build();
        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }

    @Override
    public void getAvailablePlaces(AvailablePlacesRequest request, StreamObserver<AvailablePlacesResponse> responseObserver) {
        List<String> availablePlaces = roomRepository.findDistinctLocations();

        AvailablePlacesResponse response = AvailablePlacesResponse.newBuilder().addAllAvailablePlaces(availablePlaces).build();

        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }

    @Override
    public void sendReservationRequest(ReservationRequestRequest request, StreamObserver<ReservationRequestResponse> responseObserver) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        formatter = formatter.withLocale(Locale.US);  // Locale specifies human language for translating, and cultural norms for lowercase/uppercase and abbreviations and such. Example: Locale.US or Locale.CANADA_FRENCH
        LocalDate dateFrom = LocalDate.parse(request.getFromDate(), formatter);
        LocalDate dateTo = LocalDate.parse(request.getToDate(), formatter);

        if(roomRepository.findById(request.getRoomId()).get().isAutomaticReservationConfirmation()){
            saveApprovedReservation(request, dateFrom, dateTo);

            Availability roomAvailability = null;
            for(Availability availability : availabilityRepository.findAvailabilitiesByRoom_Id(request.getRoomId())) {
                if(datesFit(availability, request.getFromDate().toString(), request.getToDate().toString())) {
                    roomAvailability = availability;
                    availabilityRepository.delete(availability);
                    break;
                }
            }

            if(!roomAvailability.getFromDate().equals(request.getFromDate())){
                Availability newAvailabilityBefore = Availability.builder()
                        .fromDate(roomAvailability.getFromDate())
                        .toDate(LocalDate.parse(request.getFromDate()).minusDays(1))
                        .room(roomRepository.findById(request.getRoomId()).get())
                        .build();

                availabilityRepository.save(newAvailabilityBefore);
            }

            if(!roomAvailability.getToDate().equals(request.getToDate())){
                Availability newAvailabilityAfter = Availability.builder()
                        .fromDate(LocalDate.parse(request.getToDate()).plusDays(1))
                        .toDate(roomAvailability.getToDate())
                        .room(roomRepository.findById(request.getRoomId()).get())
                        .build();

                availabilityRepository.save(newAvailabilityAfter);
            }
        }
        else{
            saveReservationRequest(request, dateFrom, dateTo);
        }

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

    @Override
    public void doesReservationExistsForUser(GuestReservationExistRequest request, StreamObserver<GuestReservationExistResponse> responseObserver) {
        boolean reservationExist = approvedReservationRepository.findAllByCustomerId(request.getGuestId()).size() > 0;

        GuestReservationExistResponse guestReservationExistResponse =
                GuestReservationExistResponse.newBuilder().setReservationExists(reservationExist).build();

        responseObserver.onNext(guestReservationExistResponse);
        responseObserver.onCompleted();
    }

    @Override
    public void getApprovedReservationsForGuest(GuestApprovedReservationsRequest request, StreamObserver<GuestApprovedReservationsResponse> responseObserver) {
        List<ApprovedReservation> approvedReservations = approvedReservationRepository.findAllByCustomerId(request.getGuestId());
        ArrayList<GuestApprovedReservationDTO> approvedReservationsDTOs = new ArrayList<>();

        for(ApprovedReservation reservation : approvedReservations){
            GuestApprovedReservationDTO response = GuestApprovedReservationDTO.newBuilder()
                    .setFromDate(reservation.getFromDate().toString())
                    .setToDate(reservation.getToDate().toString())
                    .setNumberOfGuests(reservation.getNumberOfGuests())
                    .setLocation(reservation.getRoom().getLocation())
                    .setRoomName(reservation.getRoom().getName())
                    .setReservationId(reservation.getId())
                    .build();

            approvedReservationsDTOs.add(response);
        }

        GuestApprovedReservationsResponse response = GuestApprovedReservationsResponse.newBuilder().addAllApprovedReservations(approvedReservationsDTOs).build();

        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }

    @Override
    public void cancelApprovedReservation(CancelApprovedReservationRequest request, StreamObserver<CancelApprovedReservationResponse> responseObserver) {

        GuestCancellation guestCancellation = null;
        ApprovedReservation reservation = approvedReservationRepository.findById(request.getReservationId()).get();

        for (GuestCancellation cancellation: guestCancellationRepository.findAll()) {
            if(cancellation.getCustomerId() == reservation.getCustomerId()){
                guestCancellation = cancellation;
            }
        }
        if(guestCancellation == null){
            guestCancellation = GuestCancellation.builder()
                    .customerId(reservation.getCustomerId())
                    .cancellationCount(1)
                    .build();
            guestCancellationRepository.save(guestCancellation);
        }
        else{
            guestCancellation.setCancellationCount(guestCancellation.getCancellationCount() + 1);
        }

        Availability newAvailability = Availability.builder()
                .fromDate(reservation.getFromDate())
                .toDate(reservation.getToDate())
                .room(reservation.getRoom())
                .build();

        for(Availability availability : availabilityRepository.findAvailabilitiesByRoom_Id(reservation.getRoom().getId())) {
            if(availability.getToDate().equals(newAvailability.getFromDate().minusDays(1))){
                newAvailability.setFromDate(availability.getFromDate());
                availabilityRepository.delete(availability);
            }
            else if(availability.getFromDate().equals(newAvailability.getToDate().plusDays(1))){
                newAvailability.setToDate(availability.getToDate());
                availabilityRepository.delete(availability);
            }
        }
        availabilityRepository.save(newAvailability);

        approvedReservationRepository.deleteById(request.getReservationId());

        CancelApprovedReservationResponse response = CancelApprovedReservationResponse.newBuilder().build();
        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }

    @Override
    public void getReservationRequestsByHost(ReservationRequestsByHostRequest request, StreamObserver<ReservationRequestsByHostResponse> responseObserver) {
        List<ReservationRequest> reservationRequests = reservationRequestRepository.findAll();
        List<ReservationRequestsByHostDTO> hostRequests = new ArrayList<>();

        for (ReservationRequest reservationRequest: reservationRequests) {
            if(reservationRequest.getRoom().getHostId() == request.getHostId()){

                GuestCancellation guestCancellation = null;
                for (GuestCancellation cancellation: guestCancellationRepository.findAll()) {
                    if(cancellation.getCustomerId() == reservationRequest.getCustomerId()){
                        guestCancellation = cancellation;
                    }
                }

                ReservationRequestsByHostDTO requestDTO = ReservationRequestsByHostDTO.newBuilder()
                        .setFromDate(reservationRequest.getFromDate().toString())
                        .setToDate(reservationRequest.getToDate().toString())
                        .setNumberOfGuests(reservationRequest.getNumberOfGuests())
                        .setLocation(reservationRequest.getRoom().getLocation())
                        .setRoomName(reservationRequest.getRoom().getName())
                        .setRequestId(reservationRequest.getId())
                        .setGuestCancellationCount(guestCancellation == null ? 0 : guestCancellation.getCancellationCount())
                        .build();

                hostRequests.add(requestDTO);
            }
        }
        ReservationRequestsByHostResponse response = ReservationRequestsByHostResponse.newBuilder().addAllReservationRequests(hostRequests).build();

        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }

    @Override
    public void approveReservationRequest(ApproveReservationRequest request, StreamObserver<ApproveReservationResponse> responseObserver) {
        ReservationRequest reservationRequest = reservationRequestRepository.findById(request.getRequestId()).get();
        reservationRequestRepository.delete(reservationRequest);

        Availability roomAvailability = null;
        for(Availability availability : availabilityRepository.findAvailabilitiesByRoom_Id(reservationRequest.getRoom().getId())) {
            if(datesFit(availability, reservationRequest.getFromDate().toString(), reservationRequest.getToDate().toString())) {
                roomAvailability = availability;
                availabilityRepository.delete(availability);
                break;
            }
        }

        if(!roomAvailability.getFromDate().equals(reservationRequest.getFromDate())){
            Availability newAvailabilityBefore = Availability.builder()
                    .fromDate(roomAvailability.getFromDate())
                    .toDate(reservationRequest.getFromDate().minusDays(1))
                    .room(reservationRequest.getRoom())
                    .build();

            availabilityRepository.save(newAvailabilityBefore);
        }

        if(!roomAvailability.getToDate().equals(reservationRequest.getToDate())){
            Availability newAvailabilityAfter = Availability.builder()
                    .fromDate(reservationRequest.getToDate().plusDays(1))
                    .toDate(roomAvailability.getToDate())
                    .room(reservationRequest.getRoom())
                    .build();

            availabilityRepository.save(newAvailabilityAfter);
        }

        ApprovedReservation approvedReservation = new ApprovedReservation(reservationRequest);
        approvedReservationRepository.save(approvedReservation);

        ApproveReservationResponse response = ApproveReservationResponse.newBuilder().build();
        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }

    @Override
    public void declineReservationRequest(DeclineReservationRequest request, StreamObserver<com.xwsBooking.room.DeclineReservationResponse> responseObserver) {
        ReservationRequest reservationRequest = reservationRequestRepository.findById(request.getRequestId()).get();
        reservationRequestRepository.delete(reservationRequest);

        DeclineReservationResponse response = DeclineReservationResponse.newBuilder().build();
        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }

    
    @Override
    public void doesReservationExistsForHost(HostReservationsExistRequest request, StreamObserver<HostReservationsExistResponse> responseObserver) {
        List<ApprovedReservation> approvedReservations = approvedReservationRepository.findAll();
        for(ApprovedReservation approvedReservation : approvedReservations) {
            if(approvedReservation.getRoom().getHostId() == request.getHostId() && (approvedReservation.getFromDate().isAfter(LocalDate.now()) || approvedReservation.getToDate().isAfter(LocalDate.now()))) {
                HostReservationsExistResponse hostReservationsExistResponse = HostReservationsExistResponse.newBuilder().setReservationsExists(true).build();
                responseObserver.onNext(hostReservationsExistResponse);
                responseObserver.onCompleted();
            }
        }

        HostReservationsExistResponse hostReservationsExistResponse = HostReservationsExistResponse.newBuilder().setReservationsExists(false).build();
        responseObserver.onNext(hostReservationsExistResponse);
        responseObserver.onCompleted();
    }

    private void saveReservationRequest(ReservationRequestRequest request, LocalDate dateFrom, LocalDate dateTo) {
        ReservationRequest reservationRequest = ReservationRequest.builder()
                .customerId(request.getGuestId())
                .room(roomRepository.findById(request.getRoomId()).get())
                .numberOfGuests(request.getNumberOfGuests())
                .fromDate(dateFrom)
                .toDate(dateTo)
                .build();

        reservationRequestRepository.save(reservationRequest);
    }

    private void saveApprovedReservation(ReservationRequestRequest request, LocalDate dateFrom, LocalDate dateTo) {
        ApprovedReservation approvedReservation = ApprovedReservation.builder()
                .customerId(request.getGuestId())
                .room(roomRepository.findById(request.getRoomId()).get())
                .numberOfGuests(request.getNumberOfGuests())
                .fromDate(dateFrom)
                .toDate(dateTo)
                .build();

        approvedReservationRepository.save(approvedReservation);
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
        return datesFit(availability, request.getDateFrom(), request.getDateTo());
    }

    public boolean datesFit(Availability availability, String requestDateFrom, String requestDateTo) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        formatter = formatter.withLocale(Locale.US);  // Locale specifies human language for translating, and cultural norms for lowercase/uppercase and abbreviations and such. Example: Locale.US or Locale.CANADA_FRENCH
        LocalDate dateFrom = LocalDate.parse(requestDateFrom, formatter);
        LocalDate dateTo = LocalDate.parse(requestDateTo, formatter);

        if( (availability.getFromDate().isBefore(dateFrom) || availability.getFromDate().equals(dateFrom)) &&
                (availability.getToDate().isAfter(dateTo) || availability.getToDate().equals(dateTo))) {

            return true;
        }

        return false;
    }


}
