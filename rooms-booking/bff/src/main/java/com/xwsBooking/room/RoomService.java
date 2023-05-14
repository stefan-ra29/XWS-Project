package com.xwsBooking.room;

import com.xwsBooking.room.dtos.*;
import com.xwsBooking.utils.CustomBadRequestException;
import com.xwsBooking.utils.Mappers;
import ij.ImagePlus;
import ij.process.ImageProcessor;
import net.devh.boot.grpc.client.inject.GrpcClient;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import javax.imageio.ImageWriteParam;
import javax.imageio.ImageWriter;
import javax.imageio.plugins.jpeg.JPEGImageWriteParam;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class RoomService {

    @GrpcClient("room-service")
    private RoomServiceGrpc.RoomServiceBlockingStub roomstub;

    public RoomDto create(RoomDto roomDto, List<MultipartFile> files) {
        var images = compressImages(files);
        return Mappers.map(roomstub.create(Mappers.map(roomDto, images)));
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

    public HostRoomsDto getHostRooms(long hostId) {
        return new HostRoomsDto(roomstub
                .getAllHostRooms(HostIdRequest.newBuilder().setHostId(hostId).build())
                .getRoomsList()
                .stream()
                .map(Mappers::map)
                .collect(Collectors.toList()));
    }

    public RoomAvailabilitiesDto getRoomAvailabilities(long roomId) {
        return new RoomAvailabilitiesDto(roomstub
                .getAllRoomAvailabilities(RoomAvailabilitiesRequest.newBuilder().setRoomId(roomId).build())
                .getAvailabilitiesList().stream().map(Mappers::map).collect(Collectors.toList()));
    }

    public RoomPricesDto getRoomPrices(long roomId) {
        return new RoomPricesDto(roomstub
                .getAllRoomPrices(RoomPricesRequest.newBuilder().setRoomId(roomId).build())
                .getPricesList().stream().map(Mappers::map).collect(Collectors.toList()));
    }

    private List<byte[]> compressImages(List<MultipartFile> files) {
        List<byte[]> images = new ArrayList<>();
        try {
            for (var file : files) {
                var newFile = File.createTempFile("temp", null);
                file.transferTo(newFile);
                BufferedImage image = ImageIO.read(newFile);

                // set the compression quality to 50%
                float quality = 0.5f;
                Iterator<ImageWriter> writers = ImageIO.getImageWritersByFormatName("jpeg");
                ImageWriter writer = writers.next();
                ImageWriteParam params = new JPEGImageWriteParam(null);
                params.setCompressionMode(ImageWriteParam.MODE_EXPLICIT);
                params.setCompressionQuality(quality);
                File compressedFile = new File("compressed.jpg");
                ImageIO.write(image, "jpeg", compressedFile);
                var bytes = Files.readAllBytes(compressedFile.toPath());
                images.add(bytes);
                newFile.delete();
                compressedFile.delete();
            }
        } catch (IOException e) {
            System.out.println("Compressing failed");
        }
        return images;
    }

}
