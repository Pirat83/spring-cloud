FROM openjdk:8
MAINTAINER rafael.kansy@blue-sharp.de

ADD ["./target/maintenance-service*.jar", "/opt/maintenance-service/maintenance-service.jar"]

EXPOSE 8080
ENTRYPOINT ["java", "-jar","/opt/maintenance-service/maintenance-service.jar"]