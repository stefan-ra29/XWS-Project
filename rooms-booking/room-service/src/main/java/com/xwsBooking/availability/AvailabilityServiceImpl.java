package com.xwsBooking.availability;

import com.xwsBooking.reservation.ApprovedReservationRepository;
import com.xwsBooking.room.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class AvailabilityServiceImpl implements AvailabilityService{

    private final AvailabilityRepository availabilityRepository;
    private final RoomRepository roomRepository;
    private final ApprovedReservationRepository approvedReservationRepository;

    @Override
    public AvailabilityResponse create(AvailabilityRequest dto) {
        if(dto.getId() != 0 ) {
            return update(dto);
        }
        var room = roomRepository.findById(dto.getRoomId());
        if(!availabilityRepository.findAvailableByFromDateAndToDate(LocalDate.parse(dto.getFrom()),
                LocalDate.parse(dto.getTo()), room.get().getId()).isEmpty()) {
            return AvailabilityResponse.newBuilder()
                    .setResponseMessage("There is already availabilities in given interval")
                    .build();
        }
        if (room.isEmpty() || room.get().getHostId() != dto.getHostId()) {
            return AvailabilityResponse.newBuilder()
                    .setResponseMessage("Cannot create availability")
                    .build();
        }
        return convert(availabilityRepository.save(convert(dto)), room.get().getHostId());
    }

    private AvailabilityResponse update(AvailabilityRequest dto) {
        var room = roomRepository.findById(dto.getRoomId());
        if(!availabilityRepository.findAvailableByFromDateAndToDate(LocalDate.parse(dto.getFrom()),
                LocalDate.parse(dto.getTo()), room.get().getId()).stream()
                .filter(a -> !a.getId().equals(dto.getId())).collect(Collectors.toList()).isEmpty() ||
                !approvedReservationRepository.findAllByFromDateAndToDateAndRoom(LocalDate.parse(dto.getFrom()),
                LocalDate.parse(dto.getTo()), room.get().getId()).isEmpty()) {
            return AvailabilityResponse.newBuilder()
                    .setResponseMessage("There is already availabilities in given interval")
                    .build();
        }
        return convert(availabilityRepository.save(convert(dto)), room.get().getHostId());
    }

    @Override
    public RoomAvailabilitiesResponse getAllFromRoom(RoomAvailabilitiesRequest request) {
        var availabilities = availabilityRepository.findAvailabilitiesByRoom_Id(request.getRoomId());
        var room = roomRepository.findById(request.getRoomId());
        return RoomAvailabilitiesResponse.newBuilder()
                .addAllAvailabilities(availabilities.stream()
                        .map(a -> convert(a, room.orElseThrow().getHostId()))
                        .collect(Collectors.toList()))
                .build();

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
