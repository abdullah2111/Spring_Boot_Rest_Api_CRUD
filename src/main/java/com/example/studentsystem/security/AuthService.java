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

@Service
@RequiredArgsConstructor
public class AuthService {
    private final AuthenticationManager authenticationManager;
    private final AuthUtil authUtil;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;


    public LoginResponseDto login(LoginRequestDto loginRequestDto) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequestDto.getUsername(), loginRequestDto.getPassword())
        );
        User user = (User) authentication.getPrincipal();
        String token = authUtil.generatetAccessToken(user);
        return new LoginResponseDto(token, user.getId());
    }

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
