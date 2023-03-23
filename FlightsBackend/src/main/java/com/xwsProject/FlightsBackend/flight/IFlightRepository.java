package com.xwsProject.FlightsBackend.flight;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IFlightRepository extends MongoRepository<Flight, String> {
}
