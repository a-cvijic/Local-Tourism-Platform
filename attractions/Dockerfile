# Start with a base image containing Java runtime
FROM amazoncorretto:17.0.8-alpine3.18

# The application's jar file
ARG JAR_FILE=target/*.jar

# Add the application's jar to the container
COPY ${JAR_FILE} app.jar

# Run the jar file
ENTRYPOINT ["java","-jar","/app.jar"]
