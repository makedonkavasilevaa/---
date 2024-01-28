FROM openjdk:17-jdk-slim
WORKDIR /reviews-service

COPY target/reviews-service-0.0.1-SNAPSHOT.jar /reviews-service

EXPOSE 4001

CMD ["java", "-jar", "reviews-service-0.0.1-SNAPSHOT.jar"]
