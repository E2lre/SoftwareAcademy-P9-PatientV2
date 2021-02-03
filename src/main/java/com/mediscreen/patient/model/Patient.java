package com.mediscreen.patient.model;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.mediscreen.patient.config.DateSerializerNumberTwo;
import com.sun.istack.NotNull;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.Date;

@Entity
@Table(name="patient")
public class Patient implements Serializable {


    @Id
    @GeneratedValue
    private long id;

    @Column(name="firstname",length=100)
    @NotNull
    private String firstName;

    @Column(name="lastname",length=100)
    @NotNull
    private String lastName;

    @NotNull
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    @JsonSerialize(using= DateSerializerNumberTwo.class)
    private LocalDate birthdate;

    @Column(name="sex",length=1)
    @NotNull
    private String sex;

    @Column(name="address",length=200)
    private String address;

    @Column(name="phone",length=12)
    private String phone;

    public Patient() {

    }

    public Patient(long id, String firstName, String lastName, LocalDate birthdate, String sex, String address, String phone) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthdate = birthdate;
        this.sex = sex;
        this.address = address;
        this.phone = phone;
    }
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public LocalDate getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(LocalDate birthdate) {
        this.birthdate = birthdate;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }




}
