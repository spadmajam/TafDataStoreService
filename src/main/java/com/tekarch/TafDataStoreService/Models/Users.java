package com.tekarch.TafDataStoreService.Models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;
import org.antlr.v4.runtime.misc.NotNull;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Data
@Table(name = "Users", uniqueConstraints = @UniqueConstraint(columnNames = {"username", "email","phone_number"}))
public class Users {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Long id;
    @Column(nullable = false,length = 50)
    private String username;
    /*@JsonIgnore
    @Column(nullable = false)
    private String password_hash;*/
    @Column(nullable = false,length = 100)
    private String email;
    @Column(nullable = false,length = 15)
    private String phone_number;
    /*@OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Bookings> bookings;*/
    @CreationTimestamp
    @Column(updatable = false)
    private LocalDateTime created_at;
    @UpdateTimestamp
    @Column
    private LocalDateTime updated_at;
}
