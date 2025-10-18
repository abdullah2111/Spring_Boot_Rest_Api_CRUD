package com.example.studentsystem.dto;


import lombok.Data;


/**
 * Login request payload carrying username and password.
 * Used to authenticate and obtain a JWT.
 *
 * @author Abdullah Al Mamun
 * @since 18-10-25
 */
@Data
public class LoginRequestDto {
    private String username;
    private String password;
}
