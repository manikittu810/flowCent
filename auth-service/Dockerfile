# Use the official OpenJDK 21 image as a base
FROM openjdk:21-jdk-slim

# Set the working directory inside the container
WORKDIR /app

# Copy the JAR file from the target folder into the container
COPY target/*.jar app.jar

# Expose the service's port (adjust as needed)
EXPOSE 8086
 # For Eureka Server, change to 8085, 8082, etc., for other services

# Command to run the JAR file
ENTRYPOINT ["java", "-jar", "app.jar"]
