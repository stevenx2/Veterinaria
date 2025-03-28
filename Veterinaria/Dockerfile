FROM maven:3.8.3-openjdk-17 AS build
WORKDIR app/
COPY . .
RUN mvn package -DskipTests

FROM openjdk:17-slim
LABEL authors="Johan Steven Mendoza Ruiz"
WORKDIR app/
EXPOSE 8080
COPY --from=build /app/target/Veterinaria-0.0.1-SNAPSHOT.jar app.jar
ENTRYPOINT ["java", "-jar","app.jar"]