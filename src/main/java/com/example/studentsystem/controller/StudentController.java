package com.example.studentsystem.controller;


import com.example.studentsystem.dto.AddStudentRequestDto;
import com.example.studentsystem.dto.StudentDto;
import com.example.studentsystem.service.StudentService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;




/**
 * REST controller exposing CRUD endpoints for student records.
 * <p>
 * Endpoints in this controller are intended to be protected by JWT at the security layer.
 * Clients should include {@code Authorization: Bearer <jwt>} when calling these routes.
 * </p>
 *
 * <p><b>Routes:</b></p>
 * <ul>
 *   <li>{@code GET /students}</li>
 *   <li>{@code GET /students/{id}}</li>
 *   <li>{@code POST /students/create}</li>
 *   <li>{@code PUT /students/{id}}</li>
 *   <li>{@code DELETE /students/{id}}</li>
 * </ul>
 *
 * <p>Uses {@link StudentService} for business operations.</p>
 *
 * @author Abdullah Al Mamun
 * @since 18-10-25
 */
@RequiredArgsConstructor
@RestController
public class StudentController {

    private final StudentService studentService;




    /**
     * Returns all students.
     *
     * @return 200 OK with a list of students (possibly empty)
     */
    @GetMapping("/students")
    public ResponseEntity<List<StudentDto>> getAllStudent(){
        return ResponseEntity.status(HttpStatus.OK).body(studentService.getAllStudents());
    }


    /**
     * Returns a specific student by id.
     *
     * @param id unique identifier of the student
     * @return 200 OK with the student DTO if found
     * @throws IllegalArgumentException if no student exists with the given id
     */
    @GetMapping("/students/{id}")
    public ResponseEntity<StudentDto> getStudent(@PathVariable Long id){
        return ResponseEntity.status(HttpStatus.OK).body(studentService.getStudentById(id));

    }




    /**
     * Creates a new student.
     *
     * @param addStudentRequestDto validated payload with name and email
     * @return 201 Created with the created student DTO
     */
    @PostMapping("/students/create")
    public ResponseEntity<StudentDto> CreateNewStudent(@RequestBody @Valid AddStudentRequestDto addStudentRequestDto){
        return ResponseEntity.status(HttpStatus.CREATED).body(studentService.createNewStudent(addStudentRequestDto));
    }


    /**
     * Deletes a student by id.
     *
     * @param id unique identifier of the student
     * @return 204 No Content if deleted
     * @throws IllegalArgumentException if no student exists with the given id
     */
    @DeleteMapping("/students/{id}")
    public ResponseEntity<Void> deleteAStudentById(@PathVariable Long id){
        studentService.deleteStudentById(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }



    /**
     * Updates an existing student.
     *
     * @param id unique identifier of the student
     * @param addStudentRequestDto payload containing new name/email values
     * @return 200 OK with updated student DTO
     * @throws IllegalArgumentException if no student exists with the given id
     */
    @PutMapping("/students/{id}")
    public ResponseEntity<StudentDto> updateStudent(@PathVariable Long id, @RequestBody AddStudentRequestDto addStudentRequestDto){
        return ResponseEntity.status(HttpStatus.OK).body(studentService.updateStudent(id, addStudentRequestDto));
    }






}
