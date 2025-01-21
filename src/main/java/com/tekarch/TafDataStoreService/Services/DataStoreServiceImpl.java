package com.tekarch.TafDataStoreService.Services;

import com.tekarch.TafDataStoreService.DTO.BookingRequest;
import com.tekarch.TafDataStoreService.DTO.BookingResponseDTO;
import com.tekarch.TafDataStoreService.Models.Bookings;
import com.tekarch.TafDataStoreService.Models.Flights;
import com.tekarch.TafDataStoreService.Models.Users;
import com.tekarch.TafDataStoreService.Repository.BookingRepository;
import com.tekarch.TafDataStoreService.Repository.FlightRepository;
import com.tekarch.TafDataStoreService.Repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class DataStoreServiceImpl {
    @Autowired
    private BookingRepository bookingRepository;

    private final FlightRepository flightRepository;
    private final UserRepository userRepository;

    public BookingResponseDTO saveBooking(BookingResponseDTO bookingRequest) {

        // Validate User
        Users user = userRepository.findById(bookingRequest.getUser_id())
                .orElseThrow(() -> new IllegalArgumentException("Invalid user ID"));

        // Validate Flight
        Flights flight = flightRepository.findById(bookingRequest.getFlight_id())
                .orElseThrow(() -> new IllegalArgumentException("Invalid flight ID"));

        // Ensure there are available seats
        if (flight.getAvailable_seats() <= 0) {
            throw new IllegalStateException("No available seats on this flight");
        }

        // Create a new booking
        Bookings booking = new Bookings();
        booking.setUser(user);
        booking.setFlight(flight);
        booking.setStatus("Booked");
        booking.setCreated_at(LocalDateTime.now());
        booking.setUpdated_at(LocalDateTime.now());

        // Save the booking to the database
        Bookings savedBooking = bookingRepository.save(booking);

        // Update the flight's available seats
        flight.setAvailable_seats(flight.getAvailable_seats() - 1);
        flightRepository.save(flight);

        // Convert to response DTO
        return mapToResponseDTO(savedBooking);

    }

    private BookingResponseDTO mapToResponseDTO(Bookings booking) {
        BookingResponseDTO responseDTO = new BookingResponseDTO();
        responseDTO.setBooking_id(booking.getBookingId());
        responseDTO.setUser_id(booking.getUser().getId());
        responseDTO.setFlight_id(booking.getFlight().getFlight_id());
        responseDTO.setStatus(booking.getStatus());
        responseDTO.setCreated_at(booking.getCreated_at());
        responseDTO.setUpdated_at(booking.getUpdated_at());
        return responseDTO;
    }

    // Retrieve all bookings for a user
    public List<BookingResponseDTO> getUserBookings(Long user_id)
    {
        List<Bookings> bookings = bookingRepository.findByUser_Id(user_id);
        return bookings.stream().map(this::mapToResponse).toList();

    }

    private BookingResponseDTO mapToResponse(Bookings booking)
    {
        return new BookingResponseDTO(
                booking.getBookingId(),
                booking.getUser().getId(),
                booking.getFlight().getFlight_id(),
                booking.getStatus(),
                booking.getCreated_at(),
                booking.getUpdated_at()
        );

    }

    public BookingResponseDTO getBookingById(Long booking_id)
    {
        Bookings booking = bookingRepository.findByBookingId(booking_id);

        if (booking != null) {
            BookingResponseDTO responseDTO = new BookingResponseDTO();
            responseDTO.setBooking_id(booking.getBookingId());
            responseDTO.setUser_id(booking.getUser().getId());
            responseDTO.setFlight_id(booking.getFlight().getFlight_id());
            responseDTO.setStatus(booking.getStatus());
            responseDTO.setCreated_at(booking.getCreated_at());
            responseDTO.setUpdated_at(booking.getUpdated_at());
            return responseDTO;
            //return booking;
        } else {
            throw new RuntimeException("Booking not found with ID: " + booking_id);
        }
    }

    //Cancel the booking. Set the status as "Cancel"
    public boolean cancelBooking(Long bookingId)
    {
        // Check if the booking exists
        Optional<Bookings> bookingOptional = bookingRepository.findById(bookingId);
        if (bookingOptional.isPresent()) {
            // Update the status to "Cancelled"
            Bookings booking = bookingOptional.get();
            booking.setStatus("Cancelled");
            bookingRepository.save(booking); // Save the updated booking
            return true;
        }
        return false; // If booking doesn't exist it returns false
    }


}
