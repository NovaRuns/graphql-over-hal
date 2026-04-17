FROM eclipse-temurin:25-jre-alpine-3.21

WORKDIR /app

# Copy your already-built jar
COPY build/libs/*.jar app.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "app.jar"]