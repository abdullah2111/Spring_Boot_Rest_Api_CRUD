package com.example.studentsystem.security;

import com.example.studentsystem.repository.UserRepository;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;



/**
 * * @author Abdullah Al Mamun
 *  * @since 18-10-25

 * Spring Security {@link UserDetailsService} implementation that loads users by username.

 * Delegates lookup to {@link UserRepository}. Returned {@link UserDetails} is your JPA entity,
 * which implements {@link org.springframework.security.core.userdetails.UserDetails}.


 *Throw a {@link UsernameNotFoundException} for unknown users to integrate
 * correctly with Spring Security's authentication flow.
 *
 */
@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;


    /**
     * Loads a user by username for authentication.
     *
     * @param username unique username supplied by the client
     * @return a {@link UserDetails} instance if found
     * @throws UsernameNotFoundException if no user is found with the given username
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByUsername(username).orElseThrow();


    }
}
