
FROM maven:3.9.9-eclipse-temurin-23 AS build
WORKDIR /app

COPY . .
# Construimos el JAR
RUN ./mvnw clean package -DskipTests
#imagen
FROM eclipse-temurin:23-jre
WORKDIR /app
# Copiamos el JAR
COPY --from=build /app/target/*.jar app.jar

EXPOSE 8080

ENTRYPOINT ["java","-jar","app.jar"]
