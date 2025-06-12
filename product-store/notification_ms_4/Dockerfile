# Use an official OpenJDK runtime as a base image
FROM openjdk:17-jdk-slim

# Set the working directory inside the container
WORKDIR /app

# Copy the JAR file from the target directory (built using Maven/Gradle)
COPY target/notification_ms_4.jar app.jar

# Expose the port on which the microservice runs (Change if needed)
EXPOSE 8084

# Command to run the application
ENTRYPOINT ["java", "-jar", "app.jar"]
