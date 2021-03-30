package com.mediscreen.patient.dao;

import com.mediscreen.patient.model.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PatientDao extends JpaRepository<Patient, Integer> {
    boolean existsByLastNameAndFirstName(String lastName, String firstName);
    Patient findById (long id);
    List<Patient> findByLastName (String familyName);
}
