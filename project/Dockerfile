# Use an official Maven runtime as a parent image
FROM maven:3.8-jdk-11-slim AS build

# Set the working directory to /app
WORKDIR /app

# Copy the pom.xml file to the working directory
COPY pom.xml .

# Download the Maven dependencies for the project
RUN mvn dependency:go-offline -B

# Copy the application source code to the working directory
COPY src ./src

# Build the application using Maven
RUN mvn package -DskipTests

# Use an official OpenJDK runtime as a parent image
FROM openjdk:11-jre-slim

# Set the working directory to /app
WORKDIR /app

# Copy the built JAR file to the working directory
COPY --from=build /app/target/project-0.0.1-SNAPSHOT.jar .

# Specify the command to run the application
CMD ["java", "-jar", "project-0.0.1-SNAPSHOT.jar"]