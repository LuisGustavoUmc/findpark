package br.com.findpark.repository;

import br.com.findpark.domain.ParkingSpot;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface ParkingSpotRepository extends MongoRepository<ParkingSpot, UUID> {
}
