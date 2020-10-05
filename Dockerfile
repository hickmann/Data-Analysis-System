FROM java:8
FROM maven:alpine

WORKDIR /app
ADD pom.xml /app
RUN mvn verify clean --fail-never

COPY . /app
RUN mvn -v
RUN mvn clean install -DskipTests
EXPOSE 8080
ADD ./target/DataAnalyzeSystem-1.0-SNAPSHOT.jar /developments/
ENTRYPOINT ["java","-jar","/developments/DataAnalyzeSystem-1.0-SNAPSHOT.jar"]