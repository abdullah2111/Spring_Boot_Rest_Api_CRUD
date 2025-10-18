package com.example.studentsystem.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;



/**
 * Signup request payload for registering a new user.
 * Carries username and password for account creation.
 *
 * @author Abdullah Al Mamun
 * @since 18-10-25
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SignupRequestDto {
    private String username;
    private String password;
}
