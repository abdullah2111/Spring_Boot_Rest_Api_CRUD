package com.example.studentsystem.repository;

import com.example.studentsystem.dto.StudentDto;
import com.example.studentsystem.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * JPA repository for {@code Student} entities.
 * Provides CRUD operations and query method support.
 *
 * @author Abdullah Al Mamun
 * @since 18-10-25
 */
@Repository
public interface StudentRepository extends JpaRepository<Student,Long> {

}
