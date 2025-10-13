package com.example.studentsystem.dto;


import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.*;
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
