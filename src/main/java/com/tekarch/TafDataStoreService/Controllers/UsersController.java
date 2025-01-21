package com.tekarch.TafDataStoreService.Controllers;

import com.tekarch.TafDataStoreService.Models.Users;
import com.tekarch.TafDataStoreService.Repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/datastore")
@AllArgsConstructor
public class UsersController {

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/users/{user_id}")  // working one
    public Users getUser(@PathVariable Long user_id) {
        return userRepository.findById(user_id).orElse(null);
    }

    @PostMapping("/users")
    public ResponseEntity<Users> createUser(@RequestBody Users user) {
        userRepository.save(user);
        return ResponseEntity.status(HttpStatus.CREATED).body(user);
    }

    @GetMapping("/users")
    public List<Users> getAllUsers() {
        return userRepository.findAll();
    }

    @PutMapping("/users/{user_id}")
    public Users updateUser(@PathVariable Long user_id, @RequestBody Users user) {
        user.setId(user_id);
        return userRepository.save(user);
    }

    @DeleteMapping("/users/{user_id}")
    public void deleteUser(@PathVariable Long user_id) {
        userRepository.deleteById(user_id);
    }
}


