package com.example.studentsystem.controller;


import com.example.studentsystem.model.Teacher;
import com.example.studentsystem.service.TeacherService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


/**
 * Public REST controller exposing read-only endpoints for teacher data.
 * <p>
 * Endpoints here are intentionally <b>public (no authentication required)</b>.
 * Use these routes to fetch teacher information without a JWT.
 * </p>
 *
 * <p><b>Routes:</b></p>
 * <ul>
 *   <li>{@code GET /public/teachers} — list all teachers</li>
 * </ul>
 *
 * <p>Uses {@link TeacherService} for data access.</p>
 *
 * @author Abdullah Al Mamun
 * @since 18-10-25
 */
@RequiredArgsConstructor
@RestController
@RequestMapping("public/")
public class TeacherPublicController {
    private final TeacherService teacherService;



    /**
     * Returns all teachers.
     *
     * @return 200 OK with a list of teachers (possibly empty)
     */
    @GetMapping("/teachers")
    public ResponseEntity<List<Teacher>>  getTeacherList(){
        return ResponseEntity.status(HttpStatus.OK).body(teacherService.getAllTeachers());
    }


    @GetMapping("/teachers/search")
    public ResponseEntity<List<Teacher>> findTeachersByName(@RequestParam String name) {
        return ResponseEntity.status(HttpStatus.OK).body(teacherService.findTeachersByName(name));
    }



    @GetMapping("/teachers/department")
    public ResponseEntity<List<Teacher>> findTeachersByDepartment(@RequestParam String department) {
        return ResponseEntity.status(HttpStatus.OK).body(teacherService.findTeachersByDepartment(department));
    }









    @GetMapping("/teachers/sorted/asc")
    public ResponseEntity<List<Teacher>> getTeachersSortedByNameAsc() {
        return ResponseEntity.status(HttpStatus.OK).body(teacherService.findAllTeachersSortedByNameAsc());
    }










    @GetMapping("/teachers/sorted/desc")
    public ResponseEntity<List<Teacher>> getTeachersSortedByNameDesc() {
        return ResponseEntity.status(HttpStatus.OK).body(teacherService.findAllTeachersSortedByNameDesc());
    }







    @GetMapping("/teachers/email")
    public ResponseEntity<Teacher> findTeacherByEmail(@RequestParam String email) {
        Optional<Teacher> teacher = teacherService.findTeacherByEmail(email);
        return teacher.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }










    @GetMapping("/teachers/count/department")
    public ResponseEntity<List<Object[]>> countTeachersByDepartment() {
        return ResponseEntity.status(HttpStatus.OK).body(teacherService.countTeachersByDepartment());
    }






    @PutMapping("/teachers/{id}/department")
    public ResponseEntity<Void> updateTeacherDepartment(@PathVariable Long id, @RequestParam String department) {
        teacherService.updateTeacherDepartment(id, department);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }





    // Endpoint to find teachers by name using native query
    @GetMapping("/teachers/native/search")
    public ResponseEntity<List<Teacher>> findTeachersByNameNative(@RequestParam String name) {
        return ResponseEntity.status(HttpStatus.OK).body(teacherService.findTeachersByNameNative(name));
    }




}
