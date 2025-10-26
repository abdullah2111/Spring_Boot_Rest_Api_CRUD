//package com.example.studentsystem;
//
//
//import com.example.studentsystem.model.Teacher;
//import com.example.studentsystem.repository.TeacherRepository;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//
//import java.util.List;
//import java.util.Optional;
//
//import static org.junit.jupiter.api.Assertions.*;
//
//@SpringBootTest
//public class JpaTest {
//
//    @Autowired
//    private TeacherRepository teacherRepository;
//
//    @Test
//    public void testFindByNameLike() {
//        // Assuming the teacher with name "John" exists in your database
//        List<Teacher> teachers = teacherRepository.findByNameLike("John");
//        assertFalse(teachers.isEmpty(), "Teachers list should not be empty");
//
//        // Print the result to the console
//        teachers.forEach(t -> System.out.println("Found teacher: " + t.getName()));
//    }
//
//    @Test
//    public void testFindByDepartment() {
//        // Assuming there are teachers in the "Mathematics" department
//        List<Teacher> teachers = teacherRepository.findByDepartment("Mathematics");
//
//        // Print the result to the console
//        teachers.forEach(t -> System.out.println("Teacher in department: " + t.getName() + ", Department: " + t.getDepartment()));
//    }
//
//    @Test
//    public void testFindByEmail() {
//        // Assuming there is a teacher with the email "john.doe@example.com"
//        Optional<Teacher> teacherOptional = teacherRepository.findByEmail("john.doe@example.com");
//
//        // Print the result to the console
//        teacherOptional.ifPresent(t -> System.out.println("Found teacher by email: " + t.getName() + ", Email: " + t.getEmail()));
//    }
//
//    @Test
//    public void testUpdateTeacherDepartment() {
//        // Assuming there's a teacher with ID 1
//        teacherRepository.updateTeacherDepartment(1L, "Science");
//        Teacher updatedTeacher = teacherRepository.findById(1L).get();
//
//        // Print the result to the console
//        System.out.println("Updated teacher department: " + updatedTeacher.getDepartment());
//    }
//
//    @Test
//    public void testCountTeachersByDepartment() {
//        List<Object[]> results = teacherRepository.countTeachersByDepartment();
//
//        // Print the results to the console
//        results.forEach(r -> System.out.println("Department: " + r[0] + ", Teacher Count: " + r[1]));
//    }
//}
