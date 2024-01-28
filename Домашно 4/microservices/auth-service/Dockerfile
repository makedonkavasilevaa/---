FROM openjdk:17-jdk-slim
WORKDIR /auth-service

COPY target/auth-service-0.0.1-SNAPSHOT.jar /auth-service

EXPOSE 4003

CMD ["java", "-jar", "auth-service-0.0.1-SNAPSHOT.jar"]
