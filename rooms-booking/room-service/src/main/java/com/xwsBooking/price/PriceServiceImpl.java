package com.xwsBooking.price;

import com.xwsBooking.room.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@RequiredArgsConstructor
@Service
public class PriceServiceImpl implements PriceService {

    private final PriceRepository priceRepository;

    private final RoomRepository roomRepository;

    @Override
    public PriceResponse create(PriceRequest request) {
        var room = roomRepository.findById(request.getRoomId());
        if (room.isEmpty() || room.get().getHostId() != request.getHostId()) {
            return PriceResponse.newBuilder()
                    .setResponseMessage("Cannot create price")
                    .build();
        }
        return convert(priceRepository.save(convert(request)), room.get().getHostId());
    }

    private PriceResponse convert(Price price, long hostId) {
        return PriceResponse.newBuilder()
                .setPrice(price.getValue())
                .setFrom(price.getFromDate().toString())
                .setTo(price.getToDate().toString())
                .setHostId(hostId)
                .setRoomId(price.getRoom().getId())
                .setId(price.getId())
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
