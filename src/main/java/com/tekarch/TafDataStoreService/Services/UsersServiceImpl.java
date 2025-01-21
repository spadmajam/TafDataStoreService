/*
package com.tekarch.TafDataStoreService.Services;

import com.tekarch.TafDataStoreService.Models.Users;
import com.tekarch.TafDataStoreService.Repository.UserRepository;
import com.tekarch.TafDataStoreService.Services.Interface.UsersService;
import lombok.AllArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class UsersServiceImpl implements UsersService {
    @Autowired
    private UserRepository tafDataStoreRepository;

    private static final Logger logger = LogManager.getLogger(UsersServiceImpl.class);
    @Override
    public Users addUser(Users user) {
        return tafDataStoreRepository.save(user);
    }

    @Override
    public Users getUserById(Long user_id) {
        logger.info("Retrieving specific user details by user ID");
        //Commenting below thing as no business logic(if else condition should be included)
       */
/* // Fetch the user from the database using the repository
        Optional<Users> userOptional = tafDataStoreRepository.findById(user_id);
        return userOptional.orElseThrow(() -> new UserNotFoundException("User with ID " + user_id + " not found"));*//*

        return tafDataStoreRepository.findById(user_id).orElse(null);
    }

    @Override
    public Users updateUserById(Long user_id,Users userData) {
        Optional<Users> userOptional = tafDataStoreRepository.findById(user_id);
        Users existingUser = userOptional.get();
        existingUser.setUsername(userData.getUsername());
        existingUser.setEmail(userData.getEmail());
        existingUser.setPhone_number(userData.getPhone_number());
        return tafDataStoreRepository.save(existingUser);
    }

    @Override
    public List<Users> getAllUsers() {
        return tafDataStoreRepository.findAll();
    }

    @Override
    public boolean deleteUser(Long user_id) {
        tafDataStoreRepository.deleteById(user_id);
        return true;
    }
}
*/
