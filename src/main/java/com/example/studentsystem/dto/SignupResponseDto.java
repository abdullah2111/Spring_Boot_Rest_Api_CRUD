package com.example.studentsystem.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


/**
 * Signup response payload returned after successful registration.
 * Includes the generated user id and the persisted username.
 *
 * @author Abdullah Al Mamun
 * @since 18-10-25
 */
@Data
@AllArgsConstructor
@NoArgsConstructor

public class SignupResponseDto {
    private Long id;
    private String username;
}
