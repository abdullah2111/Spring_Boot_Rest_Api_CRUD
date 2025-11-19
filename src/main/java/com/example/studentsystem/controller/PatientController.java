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
    public ResponseEntity<Patient>  addPatient(@RequestBody Patient patient) {
        Patient saved = patientService.createPatient(patient);
        return ResponseEntity.status(HttpStatus.CREATED).body(saved);
    }


//    @GetMapping("/all")
//    public ResponseEntity<List<Patient>> getAllPatients() {
//        return patientService.getAllPatientById(id).orElseThrow();
//    }




    @GetMapping("/{id}")
    public ResponseEntity<Patient> getPatientById(@PathVariable Long id) {
        return patientService.getPatientById(id)
                .map(patient -> ResponseEntity.ok(patient))
                .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }


}
