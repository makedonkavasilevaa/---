FROM openjdk:17-jdk-slim
WORKDIR /discovery-service

COPY target/discovery-service-0.0.1-SNAPSHOT.jar /discovery-service

EXPOSE 8761

CMD ["java", "-jar", "discovery-service-0.0.1-SNAPSHOT.jar"]
