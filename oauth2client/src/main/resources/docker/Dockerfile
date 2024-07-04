FROM maven:3.9.7-eclipse-temurin-21-alpine as builder

COPY pom.xml .

COPY src/ ./src/

RUN mvn clean package -DskipTests=true

# Use a base image with eclipse-temurin
FROM eclipse-temurin:21-jdk-alpine

RUN mkdir /app

COPY --from=builder /app/target/*.jar /app/app.jar

ENV SERVER_PORT=8080

WORKDIR /app

# Expose the port that the application will be running on
EXPOSE 8080

# Set the entry point command to run the application when the container starts
ENTRYPOINT ["java","-jar","app.jar"]
