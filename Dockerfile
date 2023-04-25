# Use the official OpenJDK image as the base image
FROM openjdk:17-jdk-alpine

# Set the working directory
WORKDIR /app

COPY . /app

# Create directories
RUN mkdir -p build

# Copy the built JAR files to the working directory
COPY build/libs/*.jar build/libs/

# Copy the built JAR files to the working directory
COPY . /app

# Expose the port that the application listens on
EXPOSE 8080

# Define the command to run the application
CMD ["java", "-jar", "build/libs/quizzes-0.0.1-SNAPSHOT.jar"]