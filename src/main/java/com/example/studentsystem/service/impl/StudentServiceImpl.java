package com.example.studentsystem.service.impl;

import com.example.studentsystem.dto.AddStudentRequestDto;
import com.example.studentsystem.dto.StudentDto;
import com.example.studentsystem.model.Student;
import com.example.studentsystem.repository.StudentRepository;
import com.example.studentsystem.service.StudentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;



/**
 * Default implementation of {@link StudentService} using Spring Data JPA.
 * Maps between entities and DTOs and enforces simple existence checks.
 *  Throws {@link IllegalArgumentException} when a student
 * cannot be found by id.
 *
 * @author Abdullah Al Mamun
 * @since 18-10-25
 */
@Service
@RequiredArgsConstructor
public class StudentServiceImpl implements StudentService {

    private final StudentRepository studentRepository;




    /**
     * Returns all students.
     *
     * @return list of student DTOs (may be empty, never {@code null})
     */
    @Override
    public List<StudentDto> getAllStudents() {
        List<Student> students = studentRepository.findAll();
        List<StudentDto> studentDtoList = students.stream().map(student -> new StudentDto(student.getId(), student.getName(),student.getEmail())).toList();
        return studentDtoList;
    }


    /**
     * Returns a student by id.
     *
     * @param id unique identifier of the student
     * @return student DTO
     * @throws IllegalArgumentException if no student is found with the given id
     */
    @Override
    public StudentDto getStudentById(Long id) {
        Student student = studentRepository.findById(id).orElseThrow(()->  new IllegalArgumentException("Student not found with id"+id));
        StudentDto studentDto = new StudentDto(student.getId(), student.getName(), student.getEmail());
        return studentDto;
    }



    /**
     * Deletes a student by id.
     *
     * @param id unique identifier of the student
     * @throws IllegalArgumentException if no student is found with the given id
     */
    @Override
    public StudentDto createNewStudent(AddStudentRequestDto addStudentRequestDto) {
        Student newStudent = new Student();
        newStudent.setName(addStudentRequestDto.getName());
        newStudent.setEmail(addStudentRequestDto.getEmail());

        Student student = studentRepository.save(newStudent);

        return new StudentDto(student.getId(), student.getName(), student.getEmail());

    }



    /**
     * Updates an existing student.
     *
     * @param id unique identifier of the student to update
     * @param addStudentRequestDto payload with new values for name and email
     * @return updated student DTO
     * @throws IllegalArgumentException if no student is found with the given id
     */
    @Override
    public void deleteStudentById(Long id) {
        if(studentRepository.existsById(id)){
            studentRepository.deleteById(id);
        }
        else{
            throw new IllegalArgumentException("Student not found with id"+id);
        }
    }

    @Override
    public StudentDto updateStudent(Long id, AddStudentRequestDto addStudentRequestDto) {
        Student existingStudent = studentRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Student not found with id " + id));
        existingStudent.setName(addStudentRequestDto.getName());
        existingStudent.setEmail(addStudentRequestDto.getEmail());
        Student updatedStudent = studentRepository.save(existingStudent);

        return new StudentDto(updatedStudent.getId(), updatedStudent.getName(), updatedStudent.getEmail());

    }


}
