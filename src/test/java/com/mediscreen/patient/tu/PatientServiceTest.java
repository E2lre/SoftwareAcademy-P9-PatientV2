package com.mediscreen.patient.tu;

import com.mediscreen.patient.dao.PatientDao;
import com.mediscreen.patient.model.Patient;
import com.mediscreen.patient.service.PatientService;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.anyString;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit.jupiter.SpringExtension;


import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureTestDatabase
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_CLASS)
public class PatientServiceTest {

    @Autowired
    private PatientService patientService;

    @MockBean
    private PatientDao patientDao;

    private Patient patient;
    //constantes de test
    String firstNameConst = "Tatiana";
    String lastNameConst = "Romanova";
    String birthdateConst = "01/03/1693";
    String sexConst ="F";
    String addressConst = "10 Downing St";
    String phoneConst = "000-111-2222";
    String incorrectlastNameConst = "Bond";

    @BeforeEach
    public void setUpEach() {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");

        DateTimeFormatter df = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            LocalDate birthdate = LocalDate.parse(birthdateConst,df);
            patient = new Patient();
            patient.setFirstName(firstNameConst);
            patient.setLastName(lastNameConst);
            patient.setBirthdate(birthdate);
            patient.setAddress(addressConst);
            patient.setSex(sexConst);
            patient.setPhone(phoneConst);
            patient.setId(0);

            List<Patient> patientList = new ArrayList<>();
            patientList.add(patient);

    }

    /*------------------------ findAll ---------------------------------*/
    @Test
    @DirtiesContext(methodMode = DirtiesContext.MethodMode.AFTER_METHOD)
    public void findAll_WhenDBISNotEmpty_PatientLisIsReturn(){
        //GIVEN
        List<Patient> patientList = new ArrayList<>();
        patientList.add(patient);
        Mockito.when(patientDao.findAll()).thenReturn(patientList);

        //WHEN
        List<Patient> patientResultList =  patientService.findAll();
        //THEN
        assertThat(patientResultList).isNotEmpty();
    }

    /*------------------------ findByID ---------------------------------*/
    @Test
    @DirtiesContext(methodMode = DirtiesContext.MethodMode.AFTER_METHOD)
    public void findById_existingPatientId_PatientIsReturn(){
        //GIVEN
                Mockito.when(patientDao.findById(0)).thenReturn(patient);

        //WHEN
        Patient patientResult =  patientService.findById(0);
        //THEN
        assertThat(patientResult).isNotNull();
    }
    @Test
    @DirtiesContext(methodMode = DirtiesContext.MethodMode.AFTER_METHOD)
    public void findById_inexistingPatientId_nullIsReturn(){
        //GIVEN
        Mockito.when(patientDao.findById(0)).thenReturn(null);

        //WHEN
        Patient patientResult =  patientService.findById(0);
        //THEN
        assertThat(patientResult).isNull();
    }
    /*------------------------ findByFamilyName ---------------------------------*/
    @Test
    @DirtiesContext(methodMode = DirtiesContext.MethodMode.AFTER_METHOD)
    public void findByFamilyName_existingfamilyNamePatient_patientListIsReturn(){
        //GIVEN
        List<Patient> patientList = new ArrayList<>();
        patientList.add(patient);
        Mockito.when(patientDao.findByLastName (anyString())).thenReturn(patientList);

        //WHEN
        List<Patient> patientListResult =  patientService.findByFamilyName(lastNameConst);
        //THEN
        assertThat(patientListResult).isNotNull();
        assertThat(patientListResult.size()).isEqualTo(patientList.size());
    }
    @Test
    @DirtiesContext(methodMode = DirtiesContext.MethodMode.AFTER_METHOD)
    public void findByFamilyName_inexistingfamilyNamePatient_nullIsReturn(){
        //GIVEN

        Mockito.when(patientDao.findByLastName (anyString())).thenReturn(null);

        //WHEN
        List<Patient> patientListResult =  patientService.findByFamilyName(lastNameConst);
        //THEN
        assertThat(patientListResult).isNull();
    }
    /*------------------------ addPatient ---------------------------------*/
    @Test
    @DirtiesContext(methodMode = DirtiesContext.MethodMode.AFTER_METHOD)
    public void addPatient_inexistingPatientGiven_patientCreated(){

        //GIVEN
        Mockito.when(patientDao.existsByLastNameAndFirstName(lastNameConst,firstNameConst)).thenReturn(false);

        //WHEN
        boolean result =  patientService.addPatient(patient);
        //THEN
        assertThat(result).isTrue();
    }
    @Test
    @DirtiesContext(methodMode = DirtiesContext.MethodMode.AFTER_METHOD)
    public void addPatient_existingPatientGiven_patientNotCreated(){

        //GIVEN
        Mockito.when(patientDao.existsByLastNameAndFirstName(lastNameConst,firstNameConst)).thenReturn(true);

        //WHEN
        boolean result =  patientService.addPatient(patient);
        //THEN
        assertThat(result).isFalse();
    }

    /*------------------------ update Patient ---------------------------------*/
    @Test
    @DirtiesContext(methodMode = DirtiesContext.MethodMode.AFTER_METHOD)
    public void updatePatient_existingIdPatientGiven_patientUpdated(){

        //GIVEN
        Mockito.when(patientDao.findById(0)).thenReturn(patient);
        Mockito.when(patientDao.save(patient)).thenReturn(patient);

        //WHEN
        Patient result =  patientService.updatePatient(patient);
        //THEN
        assertThat(result).isNotNull();
    }

    @Test
    @DirtiesContext(methodMode = DirtiesContext.MethodMode.AFTER_METHOD)
    public void updatePatient_inexistingIdPatientGiven_errorIsReturn(){

        //GIVEN
        Mockito.when(patientDao.findById(0)).thenReturn(null);

        //WHEN
        Patient result =  patientService.updatePatient(patient);
        //THEN
        assertThat(result).isNull();
    }
    /*------------------------ delete Patient ---------------------------------*/
    @Test
    @DirtiesContext(methodMode = DirtiesContext.MethodMode.AFTER_METHOD)
    public void deletePatient_existingIdAndCorrectPatientGiven_patientDeleted(){

        //GIVEN
        Mockito.when(patientDao.findById(0)).thenReturn(patient);
        Mockito.doNothing().when(patientDao).delete(patient);

        //WHEN
        Patient result =  patientService.deletePatient(0,patient);
        //THEN
        assertThat(result).isNotNull();
    }
    @Test
    @DirtiesContext(methodMode = DirtiesContext.MethodMode.AFTER_METHOD)
    public void deletePatient_existingIdAndIncorrectPatientGiven_errorIsReturn(){

        //GIVEN
        Patient incorrectPatient = new Patient();
        incorrectPatient.setFirstName(firstNameConst);
        incorrectPatient.setLastName(incorrectlastNameConst);
        incorrectPatient.setBirthdate(patient.getBirthdate());
        incorrectPatient.setAddress(addressConst);
        incorrectPatient.setSex(sexConst);
        incorrectPatient.setPhone(phoneConst);
        incorrectPatient.setId(0);

        Mockito.when(patientDao.findById(0)).thenReturn(patient);
        Mockito.doNothing().when(patientDao).delete(patient);

        //WHEN
        Patient result =  patientService.deletePatient(0,incorrectPatient);
        //THEN
        assertThat(result).isNull();
    }
    @Test
    @DirtiesContext(methodMode = DirtiesContext.MethodMode.AFTER_METHOD)
    public void deletePatient_inexistingIdAndCorrectPatientGiven_errorIsReturn(){

        //GIVEN
        Mockito.when(patientDao.findById(0)).thenReturn(null);
        Mockito.doNothing().when(patientDao).delete(patient);

        //WHEN
        Patient result =  patientService.deletePatient(0,patient);
        //THEN
        assertThat(result).isNull();
    }
}
