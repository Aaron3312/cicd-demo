# Java CI/CD Demo

This is a simple Java web application with CI/CD pipeline using GitHub Actions and DockerHub.

## Prerequisites

- JDK 17
- Maven
- Docker

## Running Locally

```bash
# Compile the application
javac Main.java

# Run the application
java Main
```

The application will be available at http://localhost:8080

## Running with Docker

```bash
# Build the Docker image
docker build -t java-cicd-demo .

# Run the Docker container
docker run -p 8080:8080 java-cicd-demo
```

## CI/CD Pipeline

The GitHub Actions workflow will:
1. Build the Java application using Maven
2. Run the tests
3. Build a Docker image
4. Push the image to DockerHub