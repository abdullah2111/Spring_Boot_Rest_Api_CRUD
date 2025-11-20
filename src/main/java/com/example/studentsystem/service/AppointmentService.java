package com.example.studentsystem.service;


import com.example.studentsystem.model.Appointment;
import com.example.studentsystem.model.Doctor;
import com.example.studentsystem.model.Patient;
import com.example.studentsystem.repository.AppointmentRepository;
import com.example.studentsystem.repository.DoctorRepository;
import com.example.studentsystem.repository.PatientRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class AppointmentService {

    private final AppointmentRepository appointmentRepository;
    private final PatientRepository patientRepository;
    private final DoctorRepository doctorRepository;



    public Appointment createAppointment(Appointment incoming) {

        log.info("Creating new appointment at {}", incoming.getAppointmentTime());

        if (incoming.getPatient() == null || incoming.getPatient().getId() == null) {
            log.error("Patient id is not provided");
            throw new RuntimeException("Patient id must be provided");
        }

        if (incoming.getDoctor() == null || incoming.getDoctor().getId() == null) {
            log.error("doctor id is not provided");
            throw new RuntimeException("Doctor id must be provided");
        }

        Long patientId = incoming.getPatient().getId();
        Long doctorId = incoming.getDoctor().getId();

        Patient patient = patientRepository.findById(patientId)
                .orElseThrow(() -> new RuntimeException("Patient not found with id " + patientId));

        Doctor doctor = doctorRepository.findById(doctorId)
                .orElseThrow(() -> new RuntimeException("Doctor not found with id " + doctorId));


        Appointment appointment = new Appointment();
        appointment.setAppointmentTime(incoming.getAppointmentTime());
        appointment.setDescription(incoming.getDescription());
        appointment.setPatient(patient);
        appointment.setDoctor(doctor);
        Appointment saved = appointmentRepository.save(appointment);
        log.info("Appointment saved with id {}", saved.getId());

        return saved;


    }

    public Optional<Appointment> getAppointmentById(Long id) {
        return appointmentRepository.findById(id);
    }
}
