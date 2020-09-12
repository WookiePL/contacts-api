#Contacts API
Simple REST API written in Java with SpringBoot to manage contacts. Every contact can have skills assigned.
This app was written mainly to demonstrate and exercise.

##Build & run
Build the app:
`mvnw spring-boot:build-image`.

Run the image:
`docker run --rm -p 8080:8080 docker.io/library/contacts:0.0.1-SNAPSHOT`.

Access documentation: 
open `http://localhost:8080/swagger-ui.html`.
##Technologies used
- Java 11
- Spring Boot 2
- Spring Security
- Swagger
