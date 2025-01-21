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

    /*@GetMapping("/{user_id}")
    public ResponseEntity<Users> getUser(@PathVariable("user_id") Long user_id) {
        //Users user = usersServiceImpl.getUserById(user_id);
       return ResponseEntity.of(userRepository.findById(user_id));
    }*/

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

    /*@GetMapping()
    public ResponseEntity<List<Users>> getAllUsers() {
        return ResponseEntity.of(Optional.of(userRepository.findAll()));
    }

    @PostMapping
    public ResponseEntity<Users> createUser(@RequestBody Users user) {
        return ResponseEntity.status(HttpStatus.CREATED).body(userRepository.save(user));
    }

    @PutMapping("/{user_id}")
    public ResponseEntity<Users> updateUser(@PathVariable Long id, @RequestBody Users userData) {
        return userRepository.findById(id)
                .map(user -> {
                    user.setUsername(userData.getUsername());
                    user.setEmail(userData.getEmail());
                    user.setPhone_number(userData.getPhone_number());
                    return ResponseEntity.ok(userRepository.save(user));
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{user_id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        userRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }*/
}


