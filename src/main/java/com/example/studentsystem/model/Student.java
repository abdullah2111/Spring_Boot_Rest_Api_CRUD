package com.example.studentsystem.model;


import jakarta.persistence.*;
import lombok.*;



/**
 * Student entity representing student record in the system.
 * Persisted in the {@code students} table. Lombok generates getters, setters,
 * and toString.
 *
 * @author Abdullah Al Mamun
 * @since 18-10-25
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Student {

    /**
     * Primary key (auto-increment).
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    /**
     * Full name of the student.
     */
    private String name;

    /**
     * Email address of the student.
     * email must be unique.
     */
    @Column(nullable = false , unique = true )
    private String email;
}
