package com.example.studentsystem.controller;


import com.example.studentsystem.dto.LoginRequestDto;
import com.example.studentsystem.dto.LoginResponseDto;
import com.example.studentsystem.dto.SignupRequestDto;
import com.example.studentsystem.dto.SignupResponseDto;
import com.example.studentsystem.security.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;




/**
 * @author Abdullah Al Mamun
 * @since 18-10-25
 * REST controller exposing authentication endpoints.

 * Provides login to obtain a JWT and signup to register a new user account.

 *
 * <p><b>Routes:</b></p>
 * <ul>
 *   <li>{@code POST /auth/login} — authenticate and receive a JWT</li>
 *   <li>{@code POST /auth/signup} — create a new user account</li>
 * </ul>
 *
 * Uses {@link AuthService} for authentication and user registration.
 *

 */
@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {
    private final AuthService authService;



    /**
     * Authenticates a user by username and password.
     *
     * @param loginRequestDto payload containing {@code username} and {@code password}
     * @return 200 OK with a {@link LoginResponseDto} containing the issued JWT and user id
     */
    @PostMapping("/login")
    public ResponseEntity<LoginResponseDto> login(@RequestBody LoginRequestDto loginRequestDto) {
        return ResponseEntity.ok(authService.login(loginRequestDto));

    }



    /**
     * Registers a new user account.
     *
     * @param signupRequestDto payload containing desired {@code username} and {@code password}
     * @return 200 OK with a {@link SignupResponseDto} containing the new user's id and username
     */
    @PostMapping("/signup")
    public ResponseEntity<SignupResponseDto> signup(@RequestBody SignupRequestDto signupRequestDto) {
        return ResponseEntity.ok(authService.signup(signupRequestDto));

    }



}
