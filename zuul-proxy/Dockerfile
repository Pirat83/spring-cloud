FROM openjdk:8
MAINTAINER rafael.kansy@blue-sharp.de

ADD ["./target/zuul-proxy*.jar", "/opt/zuul-proxy/zuul-proxy.jar"]
EXPOSE 8080
CMD ["java","-jar", "/opt/zuul-proxy/zuul-proxy.jar"]
