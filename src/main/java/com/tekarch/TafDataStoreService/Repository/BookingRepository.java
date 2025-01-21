package com.tekarch.TafDataStoreService.Repository;

import com.tekarch.TafDataStoreService.Models.Bookings;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookingRepository extends JpaRepository<Bookings,Long> {
    // Here we are using custom query to fetch bookings by user ID
    List<Bookings> findByUser_Id(Long userId);
    //custom method to find by booking ID
    Bookings findByBookingId(Long booking_id);
}
