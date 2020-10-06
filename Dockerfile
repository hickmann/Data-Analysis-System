FROM java:8
FROM maven:alpine

WORKDIR /app
COPY . /app
RUN mvn package

EXPOSE 8080
ENTRYPOINT ["java", "-jar", "/app/target/DataAnalyzeSystem-1.0-SNAPSHOT.jar"]