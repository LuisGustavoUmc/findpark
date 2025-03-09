package br.com.findpark.controller;

import br.com.findpark.dto.ParkingSpotDTO;
import br.com.findpark.services.ParkingSpotService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/parking-spot")
public class ParkingSpotController {

    @Autowired
    private ParkingSpotService service;

    @GetMapping
    public ResponseEntity<List<ParkingSpotDTO>> findAll() {
        List<ParkingSpotDTO> parkingSpots = service.findAll();
        return ResponseEntity.ok().body(parkingSpots);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ParkingSpotDTO> findById(@PathVariable String id) {
        ParkingSpotDTO parkingSpot = service.findById(id);
        return ResponseEntity.ok().body(parkingSpot);
    }

    @PostMapping
    public ResponseEntity<ParkingSpotDTO> create(@RequestBody @Valid ParkingSpotDTO parkingSpotDTO) {
        ParkingSpotDTO newObj = service.create(parkingSpotDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(newObj);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ParkingSpotDTO> update(@PathVariable String id, @RequestBody @Valid ParkingSpotDTO parkingSpotDTO) {
        ParkingSpotDTO updatedObj = service.update(id, parkingSpotDTO);
        return ResponseEntity.ok().body(updatedObj);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable String id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
