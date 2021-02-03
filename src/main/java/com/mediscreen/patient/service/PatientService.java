package com.mediscreen.patient.service;

import com.mediscreen.patient.model.Patient;

import java.util.List;

public interface PatientService {
    public List<Patient> findAll();
    public Patient findById(long id);
    public boolean addPatient(Patient patient);
    public Patient updatePatient(Patient patient);
    public Patient deletePatient(long id,Patient patient);
}
