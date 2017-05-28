FROM openjdk:8
MAINTAINER rafael.kansy@blue-sharp.de

ADD ["./target/data-collection-service*.jar", "/opt/data-collection-service/data-collection-service.jar"]

EXPOSE 8080
ENTRYPOINT ["java", "-jar","/opt/data-collection-service/data-collection-service.jar"]