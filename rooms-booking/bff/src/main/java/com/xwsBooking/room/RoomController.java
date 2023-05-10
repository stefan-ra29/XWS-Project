package com.xwsBooking.room;

import com.xwsBooking.room.dtos.AvailabilityDto;
import com.xwsBooking.room.dtos.PriceDto;
import com.xwsBooking.room.dtos.RoomDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.security.RolesAllowed;
import java.util.List;


@RestController
@RequiredArgsConstructor
@RequestMapping("api/rooms")
public class RoomController {

    private final RoomService roomService;

    @RolesAllowed("ROLE_HOST")
    @PostMapping
    public RoomDto create(@RequestParam("files") List<MultipartFile> files, @ModelAttribute RoomDto roomDto) {
        return roomService.create(roomDto, files);
    }

    @RolesAllowed("ROLE_HOST")
    @PostMapping
    @RequestMapping("/create-availability")
    public AvailabilityDto createAvailability(@RequestBody AvailabilityDto availabilityDto) {
        return roomService.createAvailability(availabilityDto);
    }

    @RolesAllowed("ROLE_HOST")
    @PostMapping
    @RequestMapping("/create-price")
    public PriceDto createPrice(@RequestBody PriceDto priceDto) {
        return roomService.createPrice(priceDto);
    }
}
