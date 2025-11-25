package com.example.studentsystem.service;


import com.example.studentsystem.globalExceptions.PatientNotFoundException;
import com.example.studentsystem.model.Insurance;
import com.example.studentsystem.model.Patient;
import com.example.studentsystem.repository.PatientRepository;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Data
@RequiredArgsConstructor
public class PatientService {

    private final PatientRepository patientRepository;

    public Patient createPatient(Patient patient) {
        Insurance insurance = patient.getInsurance();
        if (insurance != null) {
            insurance.setPatient(patient);
            patient.setInsurance(insurance);
        }

        return patientRepository.save(patient);
    }

    public Patient getPatientById(Long id) {
        return patientRepository.findById(id)
                .orElseThrow(() -> new PatientNotFoundException("Patient not found with id " + id)); // Throw exception if not found
    }

    public List<Patient> getAllPatients() {
        return patientRepository.findAll();
    }








}
