package com.xwsBooking.reservation;

import com.xwsBooking.reservation.dtos.*;
import com.xwsBooking.room.*;
import net.devh.boot.grpc.client.inject.GrpcClient;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ReservationService {

    @GrpcClient("room-service")
    private ReservationServiceGrpc.ReservationServiceBlockingStub reservationServiceBlockingStub;

    public void deleteReservationRequest(long requestId) {
        DeleteReservationRequestRequest request = DeleteReservationRequestRequest.newBuilder().setRequestId(requestId).build();
        reservationServiceBlockingStub.deleteReservationRequest(request);
    }

    public List<ReservationRequestDisplayDTO> getReservationRequestsByGuest(long guestId) {
        ReservationRequestsByGuestRequest request = ReservationRequestsByGuestRequest.newBuilder().setGuestId(guestId).build();
        ReservationRequestsByGuestResponse response = reservationServiceBlockingStub.getReservationRequestsByGuest(request);

        List<ReservationRequestDisplayDTO> requestsByGuest = new ArrayList<>();
        for(ReservationRequestGrpcDTO dto : response.getReservationRequestsList()) {
            ReservationRequestDisplayDTO reservationRequestDisplayDTO = ReservationRequestDisplayDTO.builder()
                    .numberOfGuests(dto.getNumberOfGuests())
                    .requestId(dto.getRequestId())
                    .fromDate(dto.getFromDate())
                    .toDate(dto.getToDate())
                    .roomName(dto.getRoomName())
                    .location(dto.getLocation())
                    .build();

            requestsByGuest.add(reservationRequestDisplayDTO);
        }

        return requestsByGuest;
    }

    public List<String> getAvailablePlaces() {
        AvailablePlacesRequest request = AvailablePlacesRequest.newBuilder().build();
        AvailablePlacesResponse response = reservationServiceBlockingStub.getAvailablePlaces(request);

        return response.getAvailablePlacesList();
    }

    public List<SearchResultDTO> search(SearchRequestDTO searchRequestDTO) {
        SearchRequest searchRequest = SearchRequest.newBuilder()
                .setLocation(searchRequestDTO.getLocation())
                .setDateFrom(searchRequestDTO.getDateFrom())
                .setDateTo(searchRequestDTO.getDateTo())
                .setNumberOfGuests(searchRequestDTO.getNumberOfGuests())
                .build();

        SearchResponse searchResponse = reservationServiceBlockingStub.searchRooms(searchRequest);

        List<SearchResultDTO> searchResultDTOS = new ArrayList<>();
        for(SearchResultGrpcDTO searchResultGrpcDTO : searchResponse.getRoomsList()) {
            SearchResultDTO searchResultDTO = SearchResultDTO.builder()
                    .hasAC(searchResultGrpcDTO.getHasAC())
                    .hasFreeParking(searchResultGrpcDTO.getHasFreeParking())
                    .hasKitchen(searchResultGrpcDTO.getHasKitchen())
                    .hasWifi(searchResultGrpcDTO.getHasWifi())
                    .name(searchResultGrpcDTO.getName())
                    .location(searchRequestDTO.getLocation())
                    .maxGuests(searchResultGrpcDTO.getMaxGuests())
                    .minGuests(searchResultGrpcDTO.getMinGuests())
                    .pricePerDay(searchResultGrpcDTO.getPricePerDay())
                    .totalPrice(searchResultGrpcDTO.getTotalPrice())
                    .id(searchResultGrpcDTO.getId())
                    .images(searchResultGrpcDTO.getImagesList())
                    .build();

            searchResultDTOS.add(searchResultDTO);
        }

        return searchResultDTOS;
    }

    public boolean sendReservationRequest(ReservationRequestDTO reservationRequestDTO) {

        ReservationRequestRequest request = ReservationRequestRequest.newBuilder()
                .setNumberOfGuests(reservationRequestDTO.getNumberOfGuests())
                .setFromDate(reservationRequestDTO.getFromDate())
                .setToDate(reservationRequestDTO.getToDate())
                .setGuestId(reservationRequestDTO.getGuestId())
                .setRoomId(reservationRequestDTO.getRoomId())
                .build();

        reservationServiceBlockingStub.sendReservationRequest(request);
        return true;
    }

    public boolean doesGuestHaveReservation(long guestId){
        GuestReservationExistRequest guestReservationExistRequest = GuestReservationExistRequest.newBuilder()
                .setGuestId(guestId).build();

        GuestReservationExistResponse response = reservationServiceBlockingStub.doesReservationExistsForUser(guestReservationExistRequest);

        return response.getReservationExists();
    }

    public List<ApprovedReservationDTO> getApprovedReservationForGuest(long guestId){
        GuestApprovedReservationsRequest request = GuestApprovedReservationsRequest.newBuilder().setGuestId(guestId).build();
        GuestApprovedReservationsResponse response = reservationServiceBlockingStub.getApprovedReservationsForGuest(request);
        ArrayList<ApprovedReservationDTO> approvedReservationDTOs = new ArrayList<>();

        for (GuestApprovedReservationDTO reservationDTO: response.getApprovedReservationsList()) {
            ApprovedReservationDTO reservation = ApprovedReservationDTO.builder()
                    .numberOfGuests(reservationDTO.getNumberOfGuests())
                    .reservationId(reservationDTO.getReservationId())
                    .fromDate(reservationDTO.getFromDate())
                    .toDate(reservationDTO.getToDate())
                    .roomName(reservationDTO.getRoomName())
                    .location(reservationDTO.getLocation())
                    .build();

            approvedReservationDTOs.add(reservation);
        }

        return approvedReservationDTOs;
    }

    public void cancelApprovedReservation(long reservationId){
        CancelApprovedReservationRequest request = CancelApprovedReservationRequest.newBuilder().setReservationId(reservationId).build();
        reservationServiceBlockingStub.cancelApprovedReservation(request);
    }

    public List<HostReservationRequestsDisplayDTO> getAllReservationRequestsForHost(long hostId){
        ReservationRequestsByHostRequest request = ReservationRequestsByHostRequest.newBuilder().setHostId(hostId).build();
        ReservationRequestsByHostResponse response = reservationServiceBlockingStub.getReservationRequestsByHost(request);

        List<HostReservationRequestsDisplayDTO> reservationRequestsDisplayDTOs = new ArrayList<>();

        for(ReservationRequestsByHostDTO reservationDTO: response.getReservationRequestsList()){
            HostReservationRequestsDisplayDTO dto = HostReservationRequestsDisplayDTO.builder()
                    .numberOfGuests(reservationDTO.getNumberOfGuests())
                    .requestId(reservationDTO.getRequestId())
                    .fromDate(reservationDTO.getFromDate())
                    .toDate(reservationDTO.getToDate())
                    .roomName(reservationDTO.getRoomName())
                    .location(reservationDTO.getLocation())
                    .guestCancellationCount(reservationDTO.getGuestCancellationCount())
                    .build();

            reservationRequestsDisplayDTOs.add(dto);
        }
        return reservationRequestsDisplayDTOs;
    }

    public void declineReservationRequest(long requestId){
        DeclineReservationRequest request = DeclineReservationRequest.newBuilder().setRequestId(requestId).build();
        reservationServiceBlockingStub.declineReservationRequest(request);
    }

    public void approveReservationRequest(long requestId){
        ApproveReservationRequest request = ApproveReservationRequest.newBuilder().setRequestId(requestId).build();
        reservationServiceBlockingStub.approveReservationRequest(request);
    }
}
