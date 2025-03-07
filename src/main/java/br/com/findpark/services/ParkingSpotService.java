package br.com.findpark.services;

import br.com.findpark.domain.ParkingSpot;
import br.com.findpark.repository.ParkingSpotRepository;
import br.com.findpark.services.exception.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class ParkingSpotService {

    @Autowired
    private ParkingSpotRepository repository;

    public List<ParkingSpot> findAll() {
        return repository.findAll();
    }

    public ParkingSpot findById(String id) {
        Optional<ParkingSpot> obj = repository.findById(id);
        return obj.orElseThrow(() -> new ObjectNotFoundException("Parking spot not found! ID: " + id));
    }
}
