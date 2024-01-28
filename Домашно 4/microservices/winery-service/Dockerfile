FROM openjdk:17-jdk-slim
WORKDIR /winery-service

COPY target/winery-service-0.0.1-SNAPSHOT.jar /winery-service

EXPOSE 4002

CMD ["java", "-jar", "winery-service-0.0.1-SNAPSHOT.jar"]
