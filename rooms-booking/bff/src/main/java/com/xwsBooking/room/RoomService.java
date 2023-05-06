package com.xwsBooking.room;

import com.xwsBooking.room.dtos.RoomDto;
import net.devh.boot.grpc.client.inject.GrpcClient;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class RoomService {

    @GrpcClient("room-service")
    private RoomServiceGrpc.RoomServiceBlockingStub roomstub;

    public RoomDto create(RoomDto roomDto) {
        return convert(roomstub.create(convert(roomDto)));
    }

    private RoomGrpcDto convert(RoomDto roomDto) {

        var r = RoomGrpcDto.newBuilder()
                .setAirConditioning(roomDto.isAirConditioning())
                .setFreeParking(roomDto.isFreeParking())
                .setHostId(roomDto.getHostId())
                .setLocation(roomDto.getLocation())
                .setId(roomDto.getId())
                .setName(roomDto.getName())
                .setKitchen(roomDto.isKitchen())
                .setMaxNumberOfGuests(roomDto.getMaxNumberOfGuests())
                .setMinNumberOfGuests(roomDto.getMinNumberOfGuests())
                .setWifi(roomDto.isWifi())
                .buildPartial();
        return r;
    }

    private RoomDto convert(RoomGrpcDto roomGrpcDto) {
        return RoomDto.builder()
                .airConditioning(roomGrpcDto.getAirConditioning())
                .freeParking(roomGrpcDto.getFreeParking())
                .hostId(roomGrpcDto.getHostId())
                .location(roomGrpcDto.getLocation())
                .id(roomGrpcDto.getId())
                .name(roomGrpcDto.getName())
                .kitchen(roomGrpcDto.getKitchen())
                .maxNumberOfGuests(roomGrpcDto.getMaxNumberOfGuests())
                .minNumberOfGuests(roomGrpcDto.getMinNumberOfGuests())
                .wifi(roomGrpcDto.getWifi())
                .images(new ArrayList<>())
                .build();
    }
}
