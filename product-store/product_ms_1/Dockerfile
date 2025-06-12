# Use an official OpenJDK runtime as a parent image
FROM openjdk:17-jdk-slim

# Set the working directory in the container
WORKDIR /app

# Copy the JAR file from the target folder (Assuming you have built it using Maven/Gradle)
COPY target/product_ms_1.jar app.jar

# Expose the port your service runs on (Change if necessary)
EXPOSE 8081

# Command to run the application
ENTRYPOINT ["java", "-jar", "app.jar"]
