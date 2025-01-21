package com.tekarch.TafDataStoreService.Repository;

import com.tekarch.TafDataStoreService.Models.Flights;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FlightRepository extends JpaRepository<Flights,Long> {
}
