FROM openjdk:17

WORKDIR /app

# Copy the jar file built by Maven
COPY target/java-cicd-demo-1.0-SNAPSHOT.jar /app/app.jar

EXPOSE 8080

CMD ["java", "-jar", "app.jar"]