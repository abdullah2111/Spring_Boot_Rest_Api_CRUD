package com.example.studentsystem.dto;

import lombok.*;


/**
 * Data transfer object representing a student for read operations.
 * Carries {@code id}, {@code name}, and {@code email} to clients.
 * Used in controller responses and service layer boundaries.
 *
 * @author Abdullah Al Mamun
 * @since 18-10-25
 */
@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor


public class StudentDto {
    private Long id;
    private String name;
    private String email;
}
