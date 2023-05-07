package com.xwsBooking.room;

import com.xwsBooking.availability.AvailabilityService;
import com.xwsBooking.files.FirebaseFileService;
import com.xwsBooking.price.PriceService;
import io.grpc.stub.StreamObserver;
import lombok.RequiredArgsConstructor;
import net.devh.boot.grpc.server.service.GrpcService;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@GrpcService
@Service
public class RoomService extends RoomServiceGrpc.RoomServiceImplBase {

    private final RoomRepository roomRepository;
    private final AvailabilityService availabilityService;
    private final PriceService priceService;
    private final FirebaseFileService fileService;

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
                .images(new ArrayList<>())
                .build());
        for (int i = 0; i < request.getUploadImagesList().size(); i++) {
            try {
                var imagePath = fileService.saveTest(request.getUploadImages(i).toByteArray(), String.valueOf(i));
                room.getImages().add(RoomImage.builder().path(imagePath).room(room).build());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        roomRepository.save(room);

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
                .addAllImages(room.getImages().stream().map(RoomImage::getPath).collect(Collectors.toSet()))
                .setWifi(room.isWifi())
                .setHostId(room.getHostId())
                .build();
    }
}
