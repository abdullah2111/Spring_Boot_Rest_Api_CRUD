package com.example.studentsystem.controller;

import com.example.studentsystem.model.Patient;
import com.example.studentsystem.service.PatientService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("public/patients")
public class PatientController {
    private final PatientService patientService;



    @PostMapping("/create")
    public ResponseEntity<Patient> addPatient(@RequestBody Patient patient) {
        Patient saved = patientService.createPatient(patient);
        return ResponseEntity.status(HttpStatus.CREATED).body(saved);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Patient> getPatientById(@PathVariable Long id) {
        Patient patient = patientService.getPatientById(id);
        return ResponseEntity.ok(patient);
    }


}
