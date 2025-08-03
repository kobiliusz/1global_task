# Base image with JDK 17
FROM eclipse-temurin:17-jdk

# App directory inside container
WORKDIR /app

# Copy the built JAR to container
COPY target/devices-0.0.1-SNAPSHOT.jar app.jar

# Expose default Spring Boot port
EXPOSE 8080

# Run the application
ENTRYPOINT ["java", "-jar", "app.jar"]