FROM openjdk:8
MAINTAINER rafael.kansy@blue-sharp.de

ADD ["./target/eureka-server*.jar", "/opt/eureka-server/eureka-server.jar"]

EXPOSE 8761
CMD ["java","-jar","/opt/eureka-server/eureka-server.jar"]