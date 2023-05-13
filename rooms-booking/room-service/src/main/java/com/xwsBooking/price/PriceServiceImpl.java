package com.xwsBooking.price;

import com.xwsBooking.room.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class PriceServiceImpl implements PriceService {

    private final PriceRepository priceRepository;

    private final RoomRepository roomRepository;

    @Override
    public PriceResponse create(PriceRequest request) {
        var room = roomRepository.findById(request.getRoomId());
        if(!priceRepository.findAvailableByFromDateAndToDate(LocalDate.parse(request.getFrom()),
                LocalDate.parse(request.getTo())).isEmpty()) {
            return PriceResponse.newBuilder()
                    .setResponseMessage("There is already availabilities in given interval")
                    .build();
        }
        if (room.isEmpty() || room.get().getHostId() != request.getHostId()) {
            return PriceResponse.newBuilder()
                    .setResponseMessage("Cannot create price")
                    .build();
        }
        return convert(priceRepository.save(convert(request)), room.get().getHostId());
    }

    @Override
    public RoomPricesResponse getAllRoomPrices(RoomPricesRequest request) {
        var response = priceRepository.findPricesByRoom_Id(request.getRoomId());
        var room = roomRepository.findById(request.getRoomId());
        return RoomPricesResponse.newBuilder()
                .addAllPrices(response.stream()
                        .map(p -> convert(p, room.orElseThrow().getHostId()))
                        .collect(Collectors.toList()))
                .build();
    }

    private PriceResponse convert(Price price, long hostId) {
        return PriceResponse.newBuilder()
                .setPrice(price.getValue())
                .setFrom(price.getFromDate().toString())
                .setTo(price.getToDate().toString())
                .setHostId(hostId)
                .setRoomId(price.getRoom().getId())
                .setId(price.getId())
                .setType(price.getType() == PriceType.BY_GUEST ? 0 : 1)
                .build();
    }

    private Price convert(PriceRequest priceRequest) {
        return Price.builder()
                .id(priceRequest.getId())
                .fromDate(LocalDate.parse(priceRequest.getFrom()))
                .toDate(LocalDate.parse(priceRequest.getTo()))
                .type(PriceType.values()[priceRequest.getType()])
                .value(priceRequest.getPrice())
                .room(Room.builder()
                        .id(priceRequest.getRoomId())
                        .build())
                .build();
    }
}
