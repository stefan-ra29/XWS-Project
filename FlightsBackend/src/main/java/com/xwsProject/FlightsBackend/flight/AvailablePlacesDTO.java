package com.xwsProject.FlightsBackend.flight;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AvailablePlacesDTO {
    private List<String> departures;
    private List<String> destinations;
}
