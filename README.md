# SoftwareAcademy-P9-Patient
Micro-service Patient manage patients informations on MEDISCREEN Application.

This microservice use SPRINT BOOT and MySQL

# Getting Started
EndPoint for global application  : 
* http://localhost:4200

# Prerequis
For USER microservice
* Java 1.8 or later
* Spring Boot 2.2.6
* MySQL
* Docker 2.5.0.0 or later (optional)

For Global application
* Java 1.8 or later
* MySQL
* MongoDB
* Spring Boot 2.2.6
* Docker 2.5.0.0 or later (optional)
* Angular
* Zipkin
* Eureka
* Config server

## Installation
### Modifie host file : 
 To OCR-P9
* 127.0.0.1 config
* 127.0.0.1 eureka
* 127.0.0.1 springadmin
* 127.0.0.1 zipkin
* 127.0.0.1 zipkin1
* 127.0.0.1 zuul
* 127.0.0.1 patient
* 127.0.0.1 patientv2
* 127.0.0.1 notes
* 127.0.0.1 p9-user
* 127.0.0.1 p9-patient
* 127.0.0.1 p9-patientv2
* 127.0.0.1 p9-notes
* 127.0.0.1 p9-user
* 127.0.0.1 mysql
* 127.0.0.1 mongodb


###Docker image construction in project directory :
docker build --build-arg JAR_FILE=target/*.jar -t p9-patient .

### Docker execution if docker-compose is not use
docker run -p 8084:8084 --name Patient p9-patient

### If  docker-compose is use
#### Zipkin
Install zipkin image : 
* docker pull openzipkin/zipkin

#### Mongodb
Install Mongodb image : 
* docker pull mongo

rename mongo :
* docker image tag mongo:latest mongodb:latest

#### MySQL
Install MySQL image : 
* docker pull mysql

#### Eureka
Install Eureka image : 
* docker build --build-arg JAR_FILE=target/*.jar -t p9-eureka .

#### Config Server
Install Config Server image : 
* docker build --build-arg JAR_FILE=target/*.jar -t p9-config .

#### SpringAdmin (Actuator)
Install SpringAdmin image : 
* docker build --build-arg JAR_FILE=target/*.jar -t p9-spring-admin .

#### Zuul
Install Zuul image : 
* docker build --build-arg JAR_FILE=target/*.jar -t p9-zuul .

#### User
Install User image : 
* docker build --build-arg JAR_FILE=target/*.jar -t p9-user .

#### Patient
Install Patient image : 
* docker build --build-arg JAR_FILE=target/*.jar -t p9-patient .

#### Notes
Install Notes image : 
* docker build --build-arg JAR_FILE=target/*.jar -t p9-notes .

#### Assess
Install Assess image : 
* docker build --build-arg JAR_FILE=target/*.jar -t p9-assess .

#### IHM
Install IHM image from pject directory
* docker build -t exteam .

#### Start application in Docker 
from user directory, execute: 
* docker-compose up -d

### Database installation
#### On MySQL
* execute script CreateDatabase.sql to create database « mediscreen_prod » and create user « mediscreen »
* with user mediscreen_prod, exécute script create.sql to create tables patient and user
* execute script data.sql present in ressource if PatientV2 pour initiate user table

#### On MongoDB
* create the database "mydatabase"
* create the user "mediscreen" with readWrite option
* create the collection "note"

## URI
### list all patient in data base
* directly : GET http://localhost:8084/patients
* With zuul : GET http://zuul:9004//microservice-patientsv2/patients
### Get patient by id patient
* directly : GET http://localhost:8084/patient/id=1
* With zuul : GET http://zuul:9004//microservice-patientsv2/patient/id=1
### Add patient
* directly : POST http://localhost:8084/patient/
* With zuul : POST http://zuul:9004//microservice-patientsv2/patient/

Body example :
{“firstName": "test Update","lastName": "Hubert1","birthdate": "1966-12-30",sex": "F",”address": "1 Brookside St","phone": "100-222-3333"}
### Update patient
* directly : PUT http://localhost:8084/patient/
* With zuul : PUT http://zuul:9004//microservice-patientsv2/patient/

Body example :
{
    "id": 22,
    "firstName": "test Update22",
    "lastName": "Hubert",
    "birthdate": "1966-12-31",
    "sex": "F",
    "address": "1 Brookside St",
    "phone": "100-222-3344"
}

### Delete patient
* directly : PUT http://localhost:8084/patient/id_to_delete
* With zuul : PUT http://zuul:9004//microservice-patientsv2/patient/id_to_delete

Body example :
{
    "id": 22,
    "firstName": "test Update22",
    "lastName": "Hubert",
    "birthdate": "1966-12-31",
    "sex": "F",
    "address": "1 Brookside St",
    "phone": "100-222-3344"
}

### Get patient by id patient
* directly : GET http://localhost:8084/patientFamilyName/nom_de_famille 
* With zuul : GET http://zuul:9004//microservice-patientsv2/patientFamilyName/nom_de_famille 

## Divers
Global architecture : 
![alt text](Architecture.png)