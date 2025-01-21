package com.tekarch.TafDataStoreService.Controllers;

import com.tekarch.TafDataStoreService.DTO.BookingRequest;
import com.tekarch.TafDataStoreService.DTO.BookingResponseDTO;
import com.tekarch.TafDataStoreService.Models.Bookings;
import com.tekarch.TafDataStoreService.Repository.BookingRepository;
import com.tekarch.TafDataStoreService.Repository.FlightRepository;
import com.tekarch.TafDataStoreService.Repository.UserRepository;
import com.tekarch.TafDataStoreService.Services.DataStoreServiceImpl;
import com.tekarch.TafDataStoreService.Services.DataStoreServiceImpl_old;
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

   /* //Create a new booking
    @PostMapping("/bookings")
    public ResponseEntity<Bookings> createBooking(@RequestBody BookingRequest booking) {
        bookingRepository.save(booking);
        return ResponseEntity.status(HttpStatus.CREATED).body(booking);
        //return ResponseEntity.ok(booking);
    }*/

    //////////////////////////////////////////////////////////////////////////
    //Create a new booking -- working one-------> previous working
    /*@PostMapping("/bookings")
    public ResponseEntity<Bookings> createBooking(@RequestBody BookingRequest request) {
        System.out.println("Received Booking: " + request);
        Bookings booking = dataStoreService.createBooking(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(booking);
        //return ResponseEntity.ok(booking);
    }*/

  /*  @PostMapping("/bookings")
    public ResponseEntity<BookingResponseDTO> saveBooking(@RequestBody BookingResponseDTO booking) {
        System.out.println("Received Booking: " + booking);*/

    /*//Create a new booking -- working one
    @PostMapping("/bookings")
    public ResponseEntity<?> createBooking(@RequestBody BookingRequest request) {
        BookingResponseDTO newbooking = dataStoreService.createBooking(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(newbooking);
        //return ResponseEntity.ok(booking);
    }
*/
    //Retrieve all bookings of a user by ID -- working one
    @GetMapping("/bookings/user/{userId}")
    public ResponseEntity<?> getAllBookings(@PathVariable Long userId) {
        try {
            List<Bookings> bookings = dataStoreService.getUserBookings(userId);
            return ResponseEntity.ok(bookings);
        } catch (RuntimeException ex) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
        }

       /* if(bookings.isEmpty())
        {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(bookingRepository.findByUser_Id(userId));*/
    }

    /*// CRUD for Flights
    @GetMapping("/flights/{flight_id}")
    public ResponseEntity<Flights> getFlight(@PathVariable Long flight_id) {
        return ResponseEntity.of(flightRepository.findById(flight_id));
    }

    @PutMapping("/flights/{flight_id}")
    public ResponseEntity<Flights> updateFlight(@PathVariable Long flight_id, @RequestBody Flights flightDetails) {
        return flightRepository.findById(flight_id)
                .map(flight -> {
                    flightDetails.setAvailable_seats(flightDetails.getAvailable_seats());
                    return ResponseEntity.ok(flightRepository.save(flight));
                })
                .orElse(ResponseEntity.notFound().build());
    }

    // Get user ID
    @GetMapping("/users/{user_id}")  // working one
    public Users getUser(@PathVariable Long user_id) {
        return userRepository.findById(user_id).orElse(null);
    }*/

   /* //Retrieve all bookings of a user by ID
    @GetMapping("/bookings/users/{userId}")
    public ResponseEntity<List<Bookings>> getAllBookings(@RequestParam Long userId) {
        List<Bookings> bookings = dataStoreService.getUserBookings(userId);
        if(bookings.isEmpty())
        {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(bookings);
    }*/

    //Cancel a booking. Do not delete the record. Instead mark the status as cancelled  --  working one
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
