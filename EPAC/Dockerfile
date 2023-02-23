FROM openjdk:17-jdk-alpine
ARG JAR_FILE=target/*.jar
COPY ./target/epac-0.0.1-SNAPSHOT.jar app.jar
EXPOSE 9090
ENTRYPOINT ["java","-jar","/app.jar"]