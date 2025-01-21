package com.tekarch.TafDataStoreService.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class BookingResponseDTO {
    private Long booking_id;
    private Long user_id;
    private Long flight_id;
    private String status;
    private LocalDateTime created_at;
    private LocalDateTime updated_at;
}
