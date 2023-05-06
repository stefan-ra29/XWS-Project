package com.xwsBooking.room;

import com.xwsBooking.room.dtos.AvailabilityDto;
import com.xwsBooking.room.dtos.PriceDto;
import com.xwsBooking.room.dtos.RoomDto;
import com.xwsBooking.utils.CustomBadRequestException;
import com.xwsBooking.utils.Mappers;
import net.devh.boot.grpc.client.inject.GrpcClient;
import org.springframework.stereotype.Service;

@Service
public class RoomService {

    @GrpcClient("room-service")
    private RoomServiceGrpc.RoomServiceBlockingStub roomstub;

    public RoomDto create(RoomDto roomDto) {
        return Mappers.map(roomstub.create(Mappers.map(roomDto)));
    }

    public AvailabilityDto createAvailability(AvailabilityDto availabilityDto) {
        var response = roomstub.createAvailability(Mappers.map(availabilityDto));
        if(response.getResponseMessage().length() > 0) {
            throw new CustomBadRequestException(response.getResponseMessage());
        }
        return Mappers.map(response);
    }

    public PriceDto createPrice(PriceDto priceDto) {
        var response = roomstub.createPrice(Mappers.map(priceDto));
        if(response.getResponseMessage().length() > 0) {
            throw new CustomBadRequestException(response.getResponseMessage());
        }
        return Mappers.map(response);
    }

}
