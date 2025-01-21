/*
package com.tekarch.TafDataStoreService.Controllers;

import com.tekarch.TafDataStoreService.Models.Users;
import com.tekarch.TafDataStoreService.Services.UsersServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/users")
@AllArgsConstructor
public class UsersController1 {

    private final UsersServiceImpl usersServiceImpl;

    @PostMapping
    public ResponseEntity<Users> addUser(@RequestBody Users user) {
        return new ResponseEntity<>(usersServiceImpl.addUser(user), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<Users>> getAllUsers()
    {
        return  new ResponseEntity<>(usersServiceImpl.getAllUsers(),HttpStatus.OK);
    }

    @GetMapping("/{user_id}")
    public ResponseEntity<Users>  getUser(@PathVariable("user_id") Long user_id) {
        return new ResponseEntity<>(usersServiceImpl.getUserById(user_id),
                HttpStatus.OK);
    }

    @PutMapping("/{user_id}")
    public ResponseEntity<Users> updateUser(@PathVariable("user_id") Long user_id, @RequestBody Users userData) {
        try {
            Users updatedUser = usersServiceImpl.updateUserById(user_id,userData);
            return ResponseEntity.ok(updatedUser); // Return the updated user
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build(); // User not found, return 404
        }
    }

    @DeleteMapping("/{user_id}")
    public ResponseEntity<String> deleteUser(@PathVariable("user_id") Long user_id) {
        boolean isDeleted = usersServiceImpl.deleteUser(user_id);
        return isDeleted ? ResponseEntity.ok("user profile deleted successfully for user id: "+ user_id) :
                ResponseEntity.status(HttpStatus.NOT_FOUND).body("user profile not found for id: "+user_id);
       */
/* usersServiceImpl.deleteUser(user_id);
        return new ResponseEntity<>("User with ID " + user_id + " has been deleted", HttpStatus.OK);*//*

    }

}
*/
