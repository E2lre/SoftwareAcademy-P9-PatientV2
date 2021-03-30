package com.mediscreen.patient.service;

import com.mediscreen.patient.model.Patient;

import java.util.List;

public interface PatientService {
    /**
     * get the list of patient
     * @return list of patient
     */
    public List<Patient> findAll();

    /**
     * get a patient by Id
     * @param id Id of patient
     * @return patient
     */
    public Patient findById(long id);

    /**
     * get patient list by familyName
     * @param familyName Family Name for te search
     * @return list of patient
     */
    public List<Patient> findByFamilyName(String familyName);

    /**
     * create a patient
     * @param patient patient to be create
     * @return true if created
     */
    public boolean addPatient(Patient patient);

    /**
     * update a patient - id is the key
     * @param patient new patient info
     * @return patient updated
     */
    public Patient updatePatient(Patient patient);

    /**
     * delete a patient
     * @param id patient id to be delete
     * @param patient patient to be deleted
     * @return patient deleted
     */
    public Patient deletePatient(long id,Patient patient);
}
