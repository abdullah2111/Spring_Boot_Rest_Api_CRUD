package com.example.studentsystem.service.impl;

import com.example.studentsystem.dto.AddStudentRequestDto;
import com.example.studentsystem.dto.StudentDto;
import com.example.studentsystem.model.Student;
import com.example.studentsystem.repository.StudentRepository;
import com.example.studentsystem.service.StudentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@RequiredArgsConstructor
public class StudentServiceImpl implements StudentService {

    private final StudentRepository studentRepository;


    @Override
    public List<StudentDto> getAllStudents() {
        List<Student> students = studentRepository.findAll();
        List<StudentDto> studentDtoList = students.stream().map(student -> new StudentDto(student.getId(), student.getName(),student.getEmail())).toList();
        return studentDtoList;
    }

    @Override
    public StudentDto getStudentById(Long id) {
        Student student = studentRepository.findById(id).orElseThrow(()->  new IllegalArgumentException("Student not found with id"+id));
        StudentDto studentDto = new StudentDto(student.getId(), student.getName(), student.getEmail());
        return studentDto;
    }

    @Override
    public StudentDto createNewStudent(AddStudentRequestDto addStudentRequestDto) {
        Student newStudent = new Student();
        newStudent.setName(addStudentRequestDto.getName());
        newStudent.setEmail(addStudentRequestDto.getEmail());

        Student student = studentRepository.save(newStudent);

        return new StudentDto(student.getId(), student.getName(), student.getEmail());

    }


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
