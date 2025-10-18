package com.example.studentsystem.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


/**
 * Login response payload returned after successful authentication.
 * Contains the issued JWT and the authenticated user's id.
 *
 * @author Abdullah Al Mamun
 * @since 18-10-25
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class LoginResponseDto {
    private String jwt;
    private Long userId;
}
