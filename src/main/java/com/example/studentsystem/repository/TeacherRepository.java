package com.example.studentsystem.repository;


import com.example.studentsystem.model.Teacher;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * JPA repository for {@code Teacher} entities.
 * Exposes CRUD access for public teacher data endpoints.
 *
 * @author Abdullah Al Mamun
 * @since 18-10-25
 */
@Repository
public interface TeacherRepository extends JpaRepository<Teacher,Long> {


    @Query("SELECT t FROM Teacher t WHERE t.name LIKE %:name%")
    List<Teacher> findByNameLike(String name);


    List<Teacher> findByDepartment(String department);


    @Query("SELECT t FROM Teacher t ORDER BY t.name ASC")
    List<Teacher> findAllTeachersSortedByNameAsc();



    @Query("SELECT t FROM Teacher t ORDER BY t.name DESC")
    List<Teacher> findAllTeachersSortedByNameDesc();




    Optional<Teacher> findByEmail(String email);



    @Query("SELECT t.department, COUNT(t) FROM Teacher t GROUP BY t.department")
    List<Object[]> countTeachersByDepartment();


    @Modifying
    @Transactional
    @Query("UPDATE Teacher t SET t.department = :department WHERE t.id = :id")
    void updateTeacherDepartment(Long id, String department);




    @Query(value = "SELECT * FROM teachers WHERE name LIKE %?1%", nativeQuery = true)
    List<Teacher> findTeachersByNameNative(String name);
}
