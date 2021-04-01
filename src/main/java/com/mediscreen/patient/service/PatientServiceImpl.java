package com.mediscreen.patient.service;

import com.mediscreen.patient.dao.PatientDao;
import com.mediscreen.patient.model.Patient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

@Service
public class PatientServiceImpl implements PatientService {
    private static final Logger logger = LogManager.getLogger(PatientServiceImpl.class);
    @Autowired
    private PatientDao patientDao;
    @Override
    public List<Patient> findAll() {
        logger.info("Start/finish");
        List<Patient> resultPatients = patientDao.findAll();
        logger.info("size:"+resultPatients.size());
        return resultPatients;
    }

    @Override
    public Patient findById(long id) {
        logger.info("Start/finish");
        Patient resultPatient = patientDao.findById(id);
        return resultPatient;
    }

    @Override
    public List<Patient> findByFamilyName(String familyName) {
        logger.info("Start/finish");
        List<Patient> patientListResult = patientDao.findByLastName(familyName);
        return patientListResult;
    }

    @Override
    public boolean addPatient(Patient patient) {
        logger.info("Start");
        boolean result = false;
        //Check if patient already exist
        if (!patientDao.existsByLastNameAndFirstName(patient.getLastName(),patient.getFirstName()))  {
            patientDao.save(patient);
            result = true;
            logger.info("The patient "+ patient.getId() + " is create");
        } else {
            result = false;
            logger.info("The patient "+ patient.getId() + " already exist");
        }
        logger.info("Finish");
        return result;
    }

    @Override
    public Patient updatePatient(Patient patient) {
        logger.info("Start");
        Patient resultPatient = null;
        resultPatient = patientDao.findById(patient.getId());

        if (resultPatient!=null) { // ID exist in DB
            if  (resultPatient.getLastName().equals(patient.getLastName())){ //And it is the good lastname
                resultPatient = patientDao.save(patient);
                logger.info("The patient "+ patient.getId() + " is updated");
            }  else {
                resultPatient = null;
                logger.info("The patient "+ patient.getId() + " does not exist");
            }
        }

        logger.info("Finish");

        return resultPatient;
    }

    @Override
    public Patient deletePatient(long id, Patient patient) {
        logger.info("Start");
        Patient resultPatient = null;
        Patient patientById = patientDao.findById(id);
        //Check correspondance between id and patient with
        if (patientById!=null) {
            if ((patientById.getLastName().equals(patient.getLastName())) && (patientById.getFirstName().equals(patient.getFirstName()))) {
                patientDao.delete(patientById);
                resultPatient = patient;
                logger.info("The patient "+ patient.getId() + " is deleted");
            } else {
                resultPatient = null;
                logger.info("The patient " + patient.getId() + "-" + patient.getLastName() + "-" + patient.getFirstName() + " not correspond to " + patientById.getId() + "-" + patientById.getLastName() + "-" + patientById.getFirstName());
            }
        } else {
            resultPatient = null;
            logger.info("The patient " + id + " is not found");

        }
        logger.info("Finish");
        return resultPatient;
    }
}
