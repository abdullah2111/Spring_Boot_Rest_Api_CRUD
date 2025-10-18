package com.example.studentsystem.controller;


import com.example.studentsystem.model.Teacher;
import com.example.studentsystem.service.TeacherService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


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



}
