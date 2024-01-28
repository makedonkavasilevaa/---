FROM openjdk:17-jdk-slim
WORKDIR /api-gateway

COPY target/api-gateway-0.0.1-SNAPSHOT.jar /api-gateway
EXPOSE 3005
CMD ["java", "-jar", "api-gateway-0.0.1-SNAPSHOT.jar"]
