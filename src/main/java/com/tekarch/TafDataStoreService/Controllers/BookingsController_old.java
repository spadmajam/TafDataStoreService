/*
package com.tekarch.TafDataStoreService.Controllers;

import com.tekarch.TafDataStoreService.DTO.BookingRequest;
import com.tekarch.TafDataStoreService.Models.Bookings;
import com.tekarch.TafDataStoreService.Repository.BookingRepository;
import com.tekarch.TafDataStoreService.Repository.FlightRepository;
import com.tekarch.TafDataStoreService.Repository.UserRepository;
import com.tekarch.TafDataStoreService.Services.DataStoreServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/datastore")
@AllArgsConstructor
public class BookingsController_old {
    @Autowired
    private BookingRepository bookingRepository;
    @Autowired
    private FlightRepository flightRepository;
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private DataStoreServiceImpl dataStoreService;

    //Get booking details by ID
    @GetMapping("/bookings/{booking_id}")
    public Bookings getBooking(@PathVariable("booking_id") Long booking_id) {
        return bookingRepository.findById(booking_id).orElse(null);
    }

    */
/*//*
/Create a new booking
    @PostMapping("/bookings")
    public Bookings createBooking(@RequestBody Bookings booking) {
        return bookingRepository.save(booking);
    }*//*


//    //Create a new booking
//    @PostMapping("/bookings")
//    public ResponseEntity<Bookings> createBooking(@RequestBody BookingRequest request) {
//        Bookings booking = dataStoreService.createBooking(request);
//        return ResponseEntity.status(HttpStatus.CREATED).body(booking);
//        //return ResponseEntity.ok(booking);
//    }

    //Retrieve all bookings of a user by ID
    @GetMapping("/bookings/users/{userId}")
    public ResponseEntity<List<Bookings>> getAllBookings(@PathVariable Long userId) {
        List<Bookings> bookings = dataStoreService.getUserBookings(userId);
        if(bookings.isEmpty())
        {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(bookings);
    }

   */
/* //Retrieve all bookings of a user by ID
    @GetMapping("/bookings/users/{userId}")
    public ResponseEntity<List<Bookings>> getAllBookings(@RequestParam Long userId) {
        List<Bookings> bookings = dataStoreService.getUserBookings(userId);
        if(bookings.isEmpty())
        {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(bookings);
    }*//*


    //Cancel a booking. Do not delete the record. Instead mark the status as cancelled
    @DeleteMapping("/bookings/{booking_id}")
    public ResponseEntity<String> cancelBooking(@PathVariable Long booking_id) {
        boolean isCancelled = dataStoreService.cancelBooking(booking_id);
        if (isCancelled) {
            return ResponseEntity.ok("Booking successfully cancelled.");
        } else {
            return ResponseEntity.notFound().build(); // If booking doesn't exist
        }
    }
}
*/
