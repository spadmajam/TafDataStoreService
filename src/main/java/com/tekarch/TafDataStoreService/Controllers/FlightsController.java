package com.tekarch.TafDataStoreService.Controllers;

import com.tekarch.TafDataStoreService.Models.Flights;
import com.tekarch.TafDataStoreService.Models.Users;
import com.tekarch.TafDataStoreService.Repository.FlightRepository;
import com.tekarch.TafDataStoreService.Repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/datastore")
@AllArgsConstructor
public class FlightsController {
    @Autowired
    private FlightRepository flightRepository;

    @GetMapping("/flights/{flight_id}")
    public Flights getFlight(@PathVariable Long flight_id) {
        return flightRepository.findById(flight_id).orElse(null);
    }

    @PostMapping("/flights")
    public ResponseEntity<Flights> createFlight(@RequestBody Flights flight) {
        flightRepository.save(flight);
        return ResponseEntity.status(HttpStatus.CREATED).body(flight);

    }

    @GetMapping("/flights")
    public List<Flights> getAllFlights() {
        return flightRepository.findAll();
    }

    @PutMapping("/flights/{flight_id}")
    public Flights updateFlight(@PathVariable Long flight_id, @RequestBody Flights flight) {
        flight.setFlight_id(flight_id);
        return flightRepository.save(flight);
    }

    @DeleteMapping("/flights/{flight_id}")
    public void deleteFlight(@PathVariable Long flight_id) {
        flightRepository.deleteById(flight_id);
    }
}
