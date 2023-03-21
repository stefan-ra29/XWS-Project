package com.xwsProject.FlightsBackend.flight;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class FlightServiceImpl implements FlightService{

    private final FlightRepository flightRepository;
    @Override
    public List<Flight> getAll() {
        return flightRepository.findAll();
    }
}
