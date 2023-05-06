package com.xwsBooking.room;

import com.xwsBooking.room.dtos.RoomDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequiredArgsConstructor
@RequestMapping("api/rooms")
public class RoomController {

    private final RoomService roomService;

    @PostMapping
    public RoomDto create(@RequestBody RoomDto roomDto) {
        return roomService.create(roomDto);
    }

}
