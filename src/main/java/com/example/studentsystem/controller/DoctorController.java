package com.example.studentsystem.controller;

import com.example.studentsystem.model.Doctor;
import com.example.studentsystem.service.DoctorService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequiredArgsConstructor
@RequestMapping("/public/doctors")
public class DoctorController {

    private final DoctorService doctorService;




    @PostMapping("/create")
    public ResponseEntity<Doctor> createDoctor(@RequestBody Doctor doctor) {
        Doctor saved = doctorService.createDoctor(doctor);
        return ResponseEntity.ok(saved);
    }


    @GetMapping("/{id}")
    public ResponseEntity<Doctor> getDoctorById(@PathVariable Long id) {
        return doctorService.getDoctorById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }



    @PostMapping("/{doctorId}/departments/{deptId}")
    public ResponseEntity<Doctor> addDepartmentToDoctor(@PathVariable Long doctorId,
                                                        @PathVariable Long deptId) {
        Doctor updated = doctorService.addDepartmentToDoctor(doctorId, deptId);
        return ResponseEntity.ok(updated);
    }



}
