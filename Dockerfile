# Use a valid Maven image with OpenJDK 17
FROM maven:3.8.8-eclipse-temurin-17 AS build

# Set the working directory
WORKDIR /server

# Copy the Maven wrapper and project files
COPY . .

# Grant execute permission to the Maven wrapper
RUN chmod +x ./mvnw

# Run Maven to build the application
RUN ./mvnw -DoutputFile=target/mvn-dependency-list.log -B -DskipTests clean dependency:list install

# Use an official OpenJDK image as the base for the runtime
FROM eclipse-temurin:17-jdk

# Set the working directory
WORKDIR /server

# Copy the JAR file from the build stage
COPY --from=build /server/target/*.jar app.jar

# Expose the application port
EXPOSE 8080

# Run the application
ENTRYPOINT ["java", "-jar", "app.jar"]
