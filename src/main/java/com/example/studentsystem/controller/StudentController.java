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


@RequiredArgsConstructor
@RestController
public class StudentController {

    private final StudentService studentService;



    @GetMapping("/public")
    public String index(){
        String s = "This is public API";
        return s;

    }

    @GetMapping("/students")
    public ResponseEntity<List<StudentDto>> getAllStudent(){
        return ResponseEntity.status(HttpStatus.OK).body(studentService.getAllStudents());
    }

    @GetMapping("/students/{id}")
    public ResponseEntity<StudentDto> getStudent(@PathVariable Long id){
        return ResponseEntity.status(HttpStatus.OK).body(studentService.getStudentById(id));

    }



    @PostMapping("/students/create")
    public ResponseEntity<StudentDto> CreateNewStudent(@RequestBody @Valid AddStudentRequestDto addStudentRequestDto){
        return ResponseEntity.status(HttpStatus.CREATED).body(studentService.createNewStudent(addStudentRequestDto));
    }

    @DeleteMapping("/students/{id}")
    public ResponseEntity<Void> deleteAStudentById(@PathVariable Long id){
        studentService.deleteStudentById(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }


    @PutMapping("/students/{id}")
    public ResponseEntity<StudentDto> updateStudent(@PathVariable Long id, @RequestBody AddStudentRequestDto addStudentRequestDto){
        return ResponseEntity.status(HttpStatus.OK).body(studentService.updateStudent(id, addStudentRequestDto));
    }






}
