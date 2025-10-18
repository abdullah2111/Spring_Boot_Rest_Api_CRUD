package com.example.studentsystem.security;

import com.example.studentsystem.dto.LoginRequestDto;
import com.example.studentsystem.dto.LoginResponseDto;
import com.example.studentsystem.dto.SignupRequestDto;
import com.example.studentsystem.dto.SignupResponseDto;
import com.example.studentsystem.model.User;
import com.example.studentsystem.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


/**
 * @author Abdullah Al Mamun
 * @since 18-10-25
 * Authentication service handling login and signup flows.
 * Uses Spring Security's {@link AuthenticationManager} to authenticate users,
 * issues JWTs via {@link AuthUtil}, and persists users in {@link UserRepository}.
 * Passwords are stored as strong hashes using {@link PasswordEncoder}.

 * Endpoints using this service: typically /auth/login and /auth/signup.
 *

 */
@Service
@RequiredArgsConstructor
public class AuthService {
    /** Spring Security authentication manager for username/password auth. */
    private final AuthenticationManager authenticationManager;

    /** JWT helper for token generation and parsing. */
    private final AuthUtil authUtil;

    /** Repository for persisting and looking up application users. */
    private final UserRepository userRepository;

    /** Password encoder (e.g., BCrypt) for hashing passwords. */
    private final PasswordEncoder passwordEncoder;



    /**
     * Authenticates a user by username and password and returns a JWT.
     *
     * @param loginRequestDto request payload containing {@code username} and {@code password}
     * @return response payload with the issued JWT and the authenticated user's id
     * @throws org.springframework.security.core.AuthenticationException if credentials are invalid
     */
    public LoginResponseDto login(LoginRequestDto loginRequestDto) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequestDto.getUsername(), loginRequestDto.getPassword())
        );
        User user = (User) authentication.getPrincipal();
        String token = authUtil.generatetAccessToken(user);
        return new LoginResponseDto(token, user.getId());
    }



    /**
     * Registers a new user account with a unique username.
     *
     * @param signupRequestDto request payload containing desired {@code username} and {@code password}
     * @return response payload containing the new user's id and username
     * @throws IllegalArgumentException if a user already exists with the given username
     */
    public SignupResponseDto signup(SignupRequestDto signupRequestDto) {

       User user = userRepository.findByUsername(signupRequestDto.getUsername()).orElse(null);
       if(user != null) throw new IllegalArgumentException("User already exist");

       user = userRepository.save(User.builder()
               .username(signupRequestDto.getUsername())
               .password(passwordEncoder.encode(signupRequestDto.getPassword()))
               .build());

       return new SignupResponseDto(user.getId(), user.getUsername());


    }
}
