FROM openjdk:17-jdk-alpine
COPY target/microservice-gateway-1.0-SNAPSHOT.jar api-gateway.jar
ENTRYPOINT ["java", "-jar", "microservice-gateway.jar"]