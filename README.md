Build the app:
`mvnw spring-boot:build-image`

Run the image:
`docker run --rm -p 8080:8080 docker.io/library/contacts:0.0.1-SNAPSHOT`
