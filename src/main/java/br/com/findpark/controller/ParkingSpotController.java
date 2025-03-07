package br.com.findpark.controller;

import br.com.findpark.domain.ParkingSpot;
import br.com.findpark.dto.ParkingSpotDTO;
import br.com.findpark.services.ParkingSpotService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/parking-spot")
public class ParkingSpotController {

    @Autowired
    private ParkingSpotService service;

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<List<ParkingSpotDTO>> findAll() {
        List<ParkingSpot> list = service.findAll();
        List<ParkingSpotDTO> listDto = list.stream()
                .map(ParkingSpotDTO::new).collect(Collectors.toList());
        return ResponseEntity.ok().body(listDto);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<ParkingSpotDTO> findById(@PathVariable("id") String id) {
        ParkingSpot obj = service.findById(id);
        return ResponseEntity.ok().body(new ParkingSpotDTO(obj));
    }
}
