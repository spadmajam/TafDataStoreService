package com.tekarch.TafDataStoreService.DTO;

import jakarta.persistence.Column;
import lombok.Data;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

@Data
public class BookingRequest {
    private Long user_id;
    private Long flight_id;
    private LocalDateTime created_at;
    private LocalDateTime updated_at;
}
