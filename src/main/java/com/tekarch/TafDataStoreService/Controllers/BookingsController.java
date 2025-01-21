package com.tekarch.TafDataStoreService.Controllers;

import com.tekarch.TafDataStoreService.DTO.BookingResponseDTO;
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
public class BookingsController {
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
    public ResponseEntity<?> getBooking(@PathVariable("booking_id") Long booking_id) {
        try {
            BookingResponseDTO booking = dataStoreService.getBookingById(booking_id);
            return ResponseEntity.ok(booking);
        } catch (RuntimeException ex) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
        }
    }

    @PostMapping("/bookings")
    public ResponseEntity<BookingResponseDTO> saveBooking(@RequestBody BookingResponseDTO bookingRequest) {
        // Log the incoming request
        System.out.println("Incoming BookingRequest: " + bookingRequest);

        // Validate the request
        if (bookingRequest.getUser_id() == null || bookingRequest.getFlight_id() == null) {
            throw new IllegalArgumentException("User ID and Flight ID cannot be null.");
        }

        // Save the booking
        BookingResponseDTO savedBooking = dataStoreService.saveBooking(bookingRequest);

        // Return response
        return ResponseEntity.status(HttpStatus.CREATED).body(savedBooking);
    }

    //Retrieve all bookings of a user by ID
    @GetMapping("/bookings/user/{userId}")
    public ResponseEntity<List<BookingResponseDTO>> getAllBookings(@PathVariable("userId") Long userId) {
        /*try {
            List<BookingResponseDTO> bookings = dataStoreService.getUserBookings(userId);
            return ResponseEntity.ok(bookings);
        } catch (RuntimeException ex) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
        }*/

        List<BookingResponseDTO> bookings = dataStoreService.getUserBookings(userId);
        return ResponseEntity.ok(bookings);
    }

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
