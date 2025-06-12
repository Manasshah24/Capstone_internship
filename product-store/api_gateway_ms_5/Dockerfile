# Use an official OpenJDK runtime as a base image
FROM openjdk:17-jdk-slim

# Set the working directory inside the container
WORKDIR /app

# Copy the JAR file from the target directory (built using Maven/Gradle)
COPY target/api_gateway_ms_5.jar app.jar

# Expose the port the API Gateway runs on (change if needed)
EXPOSE 8085

# Command to run the application
ENTRYPOINT ["java", "-jar", "app.jar"]
