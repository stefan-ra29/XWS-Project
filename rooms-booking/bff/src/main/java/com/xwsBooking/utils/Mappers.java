package com.xwsBooking.utils;

import com.google.protobuf.ByteString;
import com.xwsBooking.room.*;
import com.xwsBooking.room.dtos.AvailabilityDto;
import com.xwsBooking.room.dtos.PriceDto;
import com.xwsBooking.room.dtos.RoomDto;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Mappers {

    public static RoomGrpcDto map(RoomDto roomDto, List<byte[]> images) {
//        List<ByteString> bytes = new ArrayList<>();
//        for(var f : files) {
//            try {
//                bytes.add(ByteString.copyFrom(f.getBytes()));
//            } catch (IOException e) {
//                throw new CustomBadRequestException("");
//            }
//        }
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
                .addAllUploadImages(images.stream().map(ByteString::copyFrom).collect(Collectors.toSet()))
                .setWifi(roomDto.isWifi())
                .buildPartial();
        return r;
    }

    public static RoomDto map(RoomGrpcDto roomGrpcDto) {
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
                .images(roomGrpcDto.getImagesList())
                .build();
    }

    public static AvailabilityDto map(AvailabilityResponse response) {
        return AvailabilityDto.builder()
                .from(LocalDate.parse(response.getFrom()))
                .to(LocalDate.parse(response.getTo()))
                .roomId(response.getRoomId())
                .hostId(response.getHostId())
                .id(response.getId())
                .build();
    }

    public static AvailabilityRequest map(AvailabilityDto dto) {
        return AvailabilityRequest.newBuilder()
                .setId(dto.getId())
                .setHostId(dto.getHostId())
                .setFrom(dto.getFrom().toString())
                .setTo(dto.getTo().toString())
                .setRoomId(dto.getRoomId())
                .build();
    }

    public static PriceRequest map(PriceDto dto) {
        return PriceRequest.newBuilder()
                .setFrom(dto.getFrom().toString())
                .setTo(dto.getTo().toString())
                .setId(dto.getId())
                .setHostId(dto.getHostId())
                .setPrice(dto.getPrice())
                .setRoomId(dto.getRoomId())
                .setType(dto.getType())
                .build();
    }

    public static PriceDto map(PriceResponse dto) {
        return PriceDto.builder()
                .from(LocalDate.parse(dto.getFrom()))
                .to(LocalDate.parse(dto.getTo()))
                .id(dto.getId())
                .hostId(dto.getHostId())
                .price(dto.getPrice())
                .roomId(dto.getRoomId())
                .type(dto.getType())
                .build();
    }
}
