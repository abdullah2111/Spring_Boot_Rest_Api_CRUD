package com.example.studentsystem.repository;

import com.example.studentsystem.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * JPA repository for application {@code User} entities.
 * Supports lookups needed for authentication (by username).
 *
 * @author Abdullah Al Mamun
 * @since 18-10-25
 */
@Repository
public interface UserRepository extends JpaRepository<User, Long> {


    /**
     * Finds a user by unique username for authentication.
     *
     * @param username .
     * @return optional containing the user if present
     */
    Optional<User> findByUsername(String username);
}
