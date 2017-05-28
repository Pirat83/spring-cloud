FROM openjdk:8
MAINTAINER rafael.kansy@blue-sharp.de

ADD ["./target/car-repair-service*.jar", "/opt/car-repair-service/car-repair-service.jar"]

EXPOSE 8080
ENTRYPOINT ["java", "-jar","/opt/car-repair-service/car-repair-service.jar"]