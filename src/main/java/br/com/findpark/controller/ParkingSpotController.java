package br.com.findpark.controller;

import br.com.findpark.domain.ParkingSpot;
import br.com.findpark.services.ParkingSpotService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/parking-spot")
public class ParkingSpotController {

    @Autowired
    private ParkingSpotService service;

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<List<ParkingSpot>> findAll() {
        List<ParkingSpot> list = service.findAll();
        return ResponseEntity.ok().body(list);
    }
}
