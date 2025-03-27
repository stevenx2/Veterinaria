FROM openjdk:17-slim
LABEL authors="Johan Steven Mendoza Ruiz"
ARG APP_JAR=target/Veterinaria-0.0.1-SNAPSHOT.jar
WORKDIR app/
EXPOSE 8080
COPY ${APP_JAR} app.jar
ENTRYPOINT ["java", "-jar","app.jar"]