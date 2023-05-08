package com.xwsBooking.reservation;

import com.xwsBooking.reservation.dtos.SearchRequestDTO;
import com.xwsBooking.reservation.dtos.SearchResultDTO;
import com.xwsBooking.room.ReservationServiceGrpc;
import com.xwsBooking.room.SearchRequest;
import com.xwsBooking.room.SearchResponse;
import com.xwsBooking.room.SearchResultGrpcDTO;
import net.devh.boot.grpc.client.inject.GrpcClient;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ReservationService {

    @GrpcClient("room-service")
    private ReservationServiceGrpc.ReservationServiceBlockingStub reservationServiceBlockingStub;

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

}
