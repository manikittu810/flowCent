package com.flowcent.authservice.repository;

import com.flowcent.authservice.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User,String> {
    Optional<User> findUserByUsername(String username);
    Optional<User> findByPhoneNumber(String phoneNumber);
}
