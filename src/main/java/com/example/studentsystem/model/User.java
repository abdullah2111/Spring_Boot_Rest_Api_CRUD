package com.example.studentsystem.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;


/**
 * Application user entity for authentication and authorization.
 * Persisted in the {@code app_user} table. Integrates with Spring Security
 * via {@code UserDetails}; store passwords as strong hashes (e.g., BCrypt).
 *
 * @author Abdullah Al Mamun
 * @since 18-10-25
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "app_user")
public class User implements UserDetails {

    /** Primary key (auto-increment). */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /** Unique username for login. */
    @JoinColumn(unique = true)
    private String username;

    /** BCrypt-hashed password (never plaintext). */
    private String password;


    /**
     * Granted authorities (roles/permissions) for this user.
     * Currently, returns an empty list; replace with real roles if needed.
     *
     * @return immutable collection of granted authorities
     */
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of();
    }
}
