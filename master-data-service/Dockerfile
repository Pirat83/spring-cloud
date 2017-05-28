FROM openjdk:8
MAINTAINER rafael.kansy@blue-sharp.de

ADD ["./target/master-data-service*.jar", "/opt/master-data-service/master-data-service.jar"]

EXPOSE 8080
ENTRYPOINT ["java", "-jar","/opt/master-data-service/master-data-service.jar"]