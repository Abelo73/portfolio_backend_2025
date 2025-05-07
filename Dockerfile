# Use OpenJDK 17 (Render supports 17 and 21)
FROM eclipse-temurin:17-jdk-alpine

# Set working directory
WORKDIR /app

# Copy Maven wrapper and pom.xml
COPY .mvn .mvn
COPY mvnw pom.xml ./

# Download dependencies
RUN ./mvnw dependency:go-offline -B

# Copy all source code
COPY src src

# Package the Spring Boot app (creates target/*.jar)
RUN ./mvnw package -DskipTests

# Expose port 8081 (Render default)
EXPOSE 8081

# Run the app
CMD ["java", "-jar", "target/*.jar"]
