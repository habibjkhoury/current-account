FROM openjdk:8-jdk-alpine
COPY target/current-account-0.0.1-SNAPSHOT.jar current-account-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java","-jar","/current-account-0.0.1-SNAPSHOT.jar"]