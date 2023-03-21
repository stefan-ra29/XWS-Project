package com.xwsProject.FlightsBackend;

import com.xwsProject.FlightsBackend.flight.Flight;
import com.xwsProject.FlightsBackend.flight.FlightRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import java.time.LocalDateTime;
import java.util.Arrays;

@SpringBootApplication
public class FlightsBackendApplication {

	public static void main(String[] args) {
		SpringApplication.run(FlightsBackendApplication.class, args);
	}


	@Bean
	public CorsFilter corsFilter() {
		CorsConfiguration corsConfiguration = new CorsConfiguration();
		corsConfiguration.setAllowCredentials(true);
		corsConfiguration.setAllowedOrigins(Arrays.asList("http://localhost:3000"));
		corsConfiguration.setAllowedHeaders(Arrays.asList("Origin", "Access-Control-Allow-Origin", "Content-Type",
				"Accept", "Authorization", "Origin, Accept", "X-Requested-With",
				"Access-Control-Request-Method", "Access-Control-Request-Headers"));
		corsConfiguration.setExposedHeaders(Arrays.asList("Origin", "Content-Type", "Accept", "Authorization",
				"Access-Control-Allow-Origin", "Access-Control-Allow-Origin", "Access-Control-Allow-Credentials"));
		corsConfiguration.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE", "OPTIONS"));
		UrlBasedCorsConfigurationSource urlBasedCorsConfigurationSource = new UrlBasedCorsConfigurationSource();
		urlBasedCorsConfigurationSource.registerCorsConfiguration("/**", corsConfiguration);
		return new CorsFilter(urlBasedCorsConfigurationSource);
	}

//	@Bean
//	CommandLineRunner runner(FlightRepository flightRepository) {
//		return args -> {
//			Flight flight = new Flight();
//			flight.setDestination("Madrid");
//			flight.setDeparture("Belgrade");
//			flight.setPricePerTicket(145);
//			flight.setAvailableSeats(20);
//			flight.setDepartureDateTime(LocalDateTime.of(2023, 4, 1, 12, 0));
//			flight.setArrivalDateTime(LocalDateTime.of(2023, 4, 1, 14, 0));
//
//			flightRepository.insert(flight);
//		};
//	}


}
