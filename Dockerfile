# Use Maven image to build the app
FROM maven:3.9.6-eclipse-temurin-17 AS build

# Set working directory
WORKDIR /app

# Copy pom.xml and download dependencies
COPY pom.xml .
RUN mvn dependency:go-offline

# Copy all source code
COPY src ./src

# Package the Spring Boot app
RUN mvn package -DskipTests

# ---- Second Stage: Run the app ----

FROM eclipse-temurin:17-jdk-alpine

WORKDIR /app

# Copy the jar from build stage
COPY --from=build /app/target/*.jar app.jar

# Expose port 8080
EXPOSE 8080

# Run the app
CMD ["java", "-jar", "app.jar"]
