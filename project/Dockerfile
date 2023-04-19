# Use an official OpenJDK runtime as a parent image
FROM openjdk:11-jre-slim

# Set the working directory to /app
WORKDIR /app

# Copy the built JAR file to the working directory
COPY target/project-0.0.1-SNAPSHOT.jar .

# Specify the command to run the application
CMD ["java", "-jar", "project-0.0.1-SNAPSHOT.jar"]