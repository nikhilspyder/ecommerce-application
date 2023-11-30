# Stage 1: Build stage using Maven
FROM maven:3.8.4-openjdk-17-slim AS build
WORKDIR /app
COPY pom.xml .
COPY src ./src
RUN mvn clean install

# Stage 2: Runtime stage using OpenJDK and MySQL
FROM openjdk:17-slim
WORKDIR /

# Set environment variables for MySQL
ENV MYSQL_ROOT_PASSWORD=Root@123
ENV MYSQL_DATABASE=ecommerce
ENV MYSQL_USER=root
ENV MYSQL_PASSWORD=Root@123

# Expose the port on which the application will run
EXPOSE 8080

# Copy the JAR file built in the first stage
COPY --from=build /app/target/ecommerce-application-1.0.0.jar .

# Command to run the application
CMD ["java", "-jar", "ecommerce-application-1.0.0.jar"]