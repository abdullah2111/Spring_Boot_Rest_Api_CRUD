package com.example.studentsystem.repository;


import com.example.studentsystem.model.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * JPA repository for {@code Teacher} entities.
 * Exposes CRUD access for public teacher data endpoints.
 *
 * @author Abdullah Al Mamun
 * @since 18-10-25
 */
@Repository
public interface TeacherRepository extends JpaRepository<Teacher,Long> {
}
