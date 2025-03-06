package br.com.findpark.services;

import br.com.findpark.domain.ParkingSpot;
import br.com.findpark.repository.ParkingSpotRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ParkingSpotService {

    @Autowired
    private ParkingSpotRepository repository;

    public List<ParkingSpot> findAll() {
        return repository.findAll();
    }
}
