FROM openjdk:17-jdk-alpine
COPY target/config-microservice-0.0.1-SNAPSHOT.jar config-microservice.jar
ENTRYPOINT ["java", "-jar", "config-microservice.jar"]