# Use official Maven image to build the project
FROM maven:3.8.4-openjdk-17 AS build
WORKDIR /app

# Copy pom.xml and download dependencies
COPY pom.xml .
RUN mvn dependency:go-offline

# Copy project source and build
COPY src ./src
RUN mvn clean package -DskipTests

# Use an OpenJDK runtime image to run the app
FROM openjdk:17-jdk-slim
WORKDIR /app
COPY --from=build /app/target/CRUD_Employee_Backend-0.0.1-SNAPSHOT.jar CRUD_Employee_Backend-0.0.1-SNAPSHOT.jar

# Expose port (this should match the backend server's port, e.g., 8080)
EXPOSE 8080

# Command to run the app
ENTRYPOINT ["java", "-jar", "CRUD_Employee_Backend-0.0.1-SNAPSHOT.jar"]
