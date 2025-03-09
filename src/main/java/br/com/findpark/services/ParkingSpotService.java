package br.com.findpark.services;

import br.com.findpark.domain.ParkingSpot;
import br.com.findpark.dto.ParkingSpotDTO;
import br.com.findpark.mapper.ParkingSpotMapper;
import br.com.findpark.repository.ParkingSpotRepository;
import br.com.findpark.services.exception.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ParkingSpotService {

    @Autowired
    private ParkingSpotRepository repository;

    @Autowired
    private ParkingSpotMapper mapper;

    public List<ParkingSpotDTO> findAll() {
        List<ParkingSpot> parkingSpots = repository.findAll();
        return mapper.toDtoList(parkingSpots);
    }

    public ParkingSpotDTO findById(String id) {
        ParkingSpot obj = repository.findById(id).
                orElseThrow(() -> new ObjectNotFoundException("Parking spot not found! ID: " + id));
        return mapper.toDto(obj);
    }

    public ParkingSpotDTO create(ParkingSpotDTO obj) {
        ParkingSpot entity = mapper.toEntity(obj);
        ParkingSpot savedEntity = repository.save(entity);
        return mapper.toDto(savedEntity);
    }

    public ParkingSpotDTO update(String id, ParkingSpotDTO obj) {
        ParkingSpot existingSpot = repository.findById(id)
                .orElseThrow(() -> new ObjectNotFoundException("Parking spot not found! ID: " + id));

        existingSpot.setStatus(obj.status());
        existingSpot.setType(obj.type());
        existingSpot.setPrice(obj.price());

        ParkingSpot updatedSpot = repository.save(existingSpot);
        return mapper.toDto(updatedSpot);
    }

    public void delete(String id) {
        ParkingSpot obj = repository.findById(id)
                .orElseThrow(() -> new ObjectNotFoundException("Parking spot not found! ID: " + id));
        repository.delete(obj);
    }
}
