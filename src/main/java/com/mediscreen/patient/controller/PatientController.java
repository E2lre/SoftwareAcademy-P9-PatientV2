package com.mediscreen.patient.controller;

import com.mediscreen.patient.controller.exception.PatientCanNotBeDeleteException;
import com.mediscreen.patient.controller.exception.PatientCanNotBeSavedException;
import com.mediscreen.patient.controller.exception.PatientCanNotbeAddedException;
import com.mediscreen.patient.controller.exception.PatientNotFoundException;
import com.mediscreen.patient.model.Patient;
import com.mediscreen.patient.service.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;


@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
public class PatientController {
    private static final Logger logger = LogManager.getLogger(PatientController.class);
    @Autowired
    private PatientService patientService;

    /*---------------------------  GET Find All -----------------------------*/
    /**
     * get patient list
     * @return patients list
     */
    @GetMapping(value = "patients")
    @ResponseStatus(HttpStatus.OK)
    public List<Patient> listPatients()  {
        logger.info("listPatients start/finish");
        return patientService.findAll();

    }
    /*---------------------------  GET by id -----------------------------*/
    /**
     * get a patient by Id
     * @param id Id of patient
     * @return patient
     * @throws PatientNotFoundException patient not found
     */
    @GetMapping(value = "patient/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Patient getPatientById(@PathVariable long id) throws PatientNotFoundException {

        logger.info("getPatientById start-"+id);
        Patient resulPatient = patientService.findById(id);
        if (resulPatient==null){
            logger.warn("The patient " + id + " does not exist");
            throw new PatientNotFoundException("The patient " + id + " does not exist");
        }
        logger.info("getPatientById finish");
        return resulPatient;

    }
    /*---------------------------  GET by FamilyName -----------------------------*/
    @GetMapping(value = "patientFamilyName/{familyName}")
    @ResponseStatus(HttpStatus.OK)
    public List<Patient> getPatientByFamilyName(@PathVariable String familyName) throws PatientNotFoundException {

        logger.info("getPatientByFamilyName start-"+familyName);
        List<Patient> patientListResult = patientService.findByFamilyName(familyName);
        if ((patientListResult==null) || (patientListResult.size() ==0)){
            logger.warn("The patient whith family Name" + familyName + " does not exist");
            throw new PatientNotFoundException("The patient whith family Name" + familyName + " does not exist");
        }
        logger.info("getPatientByFamilyName finish");
        return patientListResult;

    }
    /*---------------------------  POST Patient -----------------------------*/
    /**
     * create a patient
     * @param patient patient to be create
     * @return patient created
     * @throws PatientCanNotbeAddedException patient already exist
     */
    @PostMapping(value = "patient")
    @ResponseStatus(HttpStatus.CREATED)
    public String addPatient(@RequestBody Patient patient) throws PatientCanNotbeAddedException {
        String finalResult;
        logger.info("addPatients start");
        boolean result = patientService.addPatient(patient);
        if (result) {
            finalResult = "The patient " + patient.getId() + " has been created";
        } else {
            finalResult = "The patient " + patient.getId() + " already exist";
            logger.warn(finalResult);
            throw new PatientCanNotbeAddedException("The patient " + patient.getId() + " already exist");
        }
        return finalResult;
    }

    /*---------------------------  PUT Patient -----------------------------*/

    /**
     * update an existing patient
     * @param patient patient to be updated
     * @return patient updated
     * @throws PatientCanNotBeSavedException patient not exist
     */
    @PutMapping(value = "patient")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public Patient updatePatient(@RequestBody Patient patient) throws PatientCanNotBeSavedException {
        Patient finalResult = null;
        logger.info("savePatients start");
        finalResult = patientService.updatePatient(patient);
        if (finalResult == null) {

            logger.warn("The patient " + patient.getId() + " does not exist");
            throw new PatientCanNotBeSavedException("The patient " + patient.getId() + " does not exist");
        }
        return finalResult;
    }

    /*---------------------------  DELETE Patient -----------------------------*/
    /**
     * Delete Patient
     * @param id Patient Id to be delete
     * @param patient Patient to be delete : it is for check with ID
     * @return patient deleted
     * @throws PatientCanNotBeDeleteException exception if db error or if patient is not correspond to the id
     */
    @DeleteMapping(value = "patient/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Patient deletePatient(@PathVariable long id,@RequestBody Patient patient) throws PatientCanNotBeDeleteException {
        Patient finalResult = null;
        logger.info("deletePatients start");
        finalResult = patientService.deletePatient(id,patient);
        if (finalResult == null) {

            logger.warn("The patient " + patient.getId() + " can not be delete");
            throw new PatientCanNotBeDeleteException("The patient " + patient.getId() + " can not be delete");
        }
        return finalResult;
    }
}
