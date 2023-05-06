package com.xwsBooking.availability;

import com.xwsBooking.room.AvailabilityRequest;
import com.xwsBooking.room.AvailabilityResponse;
import com.xwsBooking.room.Room;
import com.xwsBooking.room.RoomRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@RequiredArgsConstructor
@Service
public class AvailabilityServiceImpl implements AvailabilityService{

    private final AvailabilityRepository availabilityRepository;
    private final RoomRepository roomRepository;

    @Override
    public AvailabilityResponse create(AvailabilityRequest dto) {
        var room = roomRepository.findById(dto.getRoomId());
        if (room.isEmpty() || room.get().getHostId() != dto.getHostId()) {
            return AvailabilityResponse.newBuilder()
                    .setResponseMessage("Cannot create availability")
                    .build();
        }
        return convert(availabilityRepository.save(convert(dto)), room.get().getHostId());
    }

    private AvailabilityResponse convert(Availability availability, long hostId) {
        return AvailabilityResponse.newBuilder()
                .setId(availability.getId())
                .setHostId(hostId)
                .setFrom(availability.getFromDate().toString())
                .setTo(availability.getToDate().toString())
                .setRoomId(availability.getRoom().getId())
                .setResponseMessage("")
                .build();
    }

    private Availability convert(AvailabilityRequest dto) {
        return Availability.builder()
                .id(dto.getId())
                .fromDate(LocalDate.parse(dto.getFrom()))
                .toDate(LocalDate.parse(dto.getTo()))
                .room(Room.builder()
                        .id(dto.getRoomId())
                        .build())
                .build();
    }
}
