# SoftwareAcademy-P9-Patient

## Installation

### Database installation
* execute CreateDatabase.sql file


###Docker image construction in project directory :

docker build --build-arg JAR_FILE=target/*.jar -t p9-patient .

### Docker execution :

docker run -p 9102:9102 --name Patient p9-patient

execute command line to start all components: docker-compose up -d

### divers
* paramétrage du proxy pour node js

### lancement de zipkin 
* depuis le répertoire de zipkin : java -jar zipkin-server-2.6.1-exec.jar
* lancer : http://localhost:9411 
