package com.tekarch.TafDataStoreService.Repository;

import com.tekarch.TafDataStoreService.Models.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<Users,Long> {
}
