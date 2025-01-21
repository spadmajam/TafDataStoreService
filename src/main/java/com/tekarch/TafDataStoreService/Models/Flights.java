package com.tekarch.TafDataStoreService.Models;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Data
@Table(name = "Flights")
public class Flights {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Long flight_id;
    @Column(unique = true, nullable = false)
    private String flight_number;
    /*@Column(nullable = false)
    private String password_hash;*/
    @Column(nullable = false)
    private String departure;
    @Column(nullable = false)
    private String arrival;
    @Column
    private LocalDateTime departure_time;
    @Column
    private LocalDateTime arrival_time;
    @Column(precision = 10, scale = 2)
    private BigDecimal price;
    @Column
    private Long available_seats;
    @CreationTimestamp
    @Column(updatable = false)
    private LocalDateTime created_at;
    @UpdateTimestamp
    @Column
    private LocalDateTime updated_at;
    /*@Version
    private Integer version;*/
}
