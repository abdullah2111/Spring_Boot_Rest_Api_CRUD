package com.example.studentsystem.dto;


import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.*;


/**
 * Request payload for creating a new student.
 * Validated at the controller boundary before processing.
 *Includes {@code name} and {@code email}; both are required.
 *
 * @author Abdullah Al Mamun
 * @since 18-10-25
 */
@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AddStudentRequestDto {
    @NotBlank
    private String name;

    @Email
    @NotBlank
    private String email;
}
