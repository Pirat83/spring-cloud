FROM openjdk:8
MAINTAINER rafael.kansy@blue-sharp.de

ADD ["./target/appointment-service*.jar", "/opt/appointment-service/appointment-service.jar"]

EXPOSE 8080
ENTRYPOINT ["java", "-jar","/opt/appointment-service/appointment-service.jar"]