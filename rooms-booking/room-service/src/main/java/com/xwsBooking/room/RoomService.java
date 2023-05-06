package com.xwsBooking.room;

import com.xwsBooking.availability.AvailabilityService;
import com.xwsBooking.price.PriceService;
import io.grpc.stub.StreamObserver;
import lombok.RequiredArgsConstructor;
import net.devh.boot.grpc.server.service.GrpcService;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@GrpcService
@Service
public class RoomService extends RoomServiceGrpc.RoomServiceImplBase {

    private final RoomRepository roomRepository;
    private final AvailabilityService availabilityService;
    private final PriceService priceService;

    @Override
    public void create(RoomGrpcDto request, StreamObserver<RoomGrpcDto> responseObserver) {
        Room room = roomRepository.save(Room.builder()
                .airConditioning(request.getAirConditioning())
                .hostId(request.getHostId())
                .freeParking(request.getFreeParking())
                .kitchen(request.getKitchen())
                .location(request.getLocation())
                .name(request.getName())
                .wifi(request.getWifi())
                .maxNumberOfGuests(request.getMaxNumberOfGuests())
                .minNumberOfGuests(request.getMinNumberOfGuests())
                .build());
        responseObserver.onNext(convert(room));
        responseObserver.onCompleted();
    }

    @Override
    public void createAvailability(AvailabilityRequest request, StreamObserver<AvailabilityResponse> responseObserver) {
        var response = availabilityService.create(request);
        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }

    @Override
    public void createPrice(PriceRequest request, StreamObserver<PriceResponse> responseObserver) {
        var response = priceService.create(request);
        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }

    private RoomGrpcDto convert(Room room) {
        return RoomGrpcDto.newBuilder()
                .setId(room.getId())
                .setAirConditioning(room.isAirConditioning())
                .setFreeParking(room.isFreeParking())
                .setKitchen(room.isKitchen())
                .setLocation(room.getLocation())
                .setMaxNumberOfGuests(room.getMaxNumberOfGuests())
                .setMinNumberOfGuests(room.getMinNumberOfGuests())
                .setName(room.getName())
                .setWifi(room.isWifi())
                .setHostId(room.getHostId())
                .build();
    }
}
