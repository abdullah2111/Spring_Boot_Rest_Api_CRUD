package com.example.studentsystem.dto;

import lombok.*;

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
