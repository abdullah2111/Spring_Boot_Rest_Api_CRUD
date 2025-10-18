package com.example.studentsystem.model;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;
import lombok.RequiredArgsConstructor;


/**
 * Teacher entity representing teacher record in the system.
 * Persisted in the {@code teachers} table. Lombok generates getters, setters,
 * and toString.
 * @author Abdullah Al Mamun
 * @since 18-10-25
 */
@Entity
@Data
@RequiredArgsConstructor
public class Teacher {

    /** Primary key (auto-increment). */
    @Id
    private Long id;

    /** Full name of the teacher. */
    @Column(nullable = false)
    private String name;

    /** Email address of the teacher. */
    @Column(nullable = false , unique = true )
    private String email;

    /** Department the teacher belongs to. */
    private String department;
}


