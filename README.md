# spring-cloud

## Prerequisites
You can do either an `local` deployment or an `docker` deployment. 
Those two options are implemented by Spring profiles so you have to choose either the `local` or the `docker` profile. 
See [Spring Profiles documentation](https://docs.spring.io/spring-boot/docs/current/reference/html/boot-features-profiles.html) for further information. 
You can activate Spring profiles by specifying on the command line the switch `--spring.profiles.active=local` or `--spring.profiles.active=docker`. 
If you choose the `docker` deployment this setup is already done for you. 
* The docker setup is very easy to setup, if you already set up a Docker host. (Use `local` profile)
* The local setup is often more appropriate if you want to debug the source code. (Use `docker` profile)
* If you want to create more complex deployments you can also override `JDBC` and `Monog` connection URLs and the Eureka registry URL. 

### Set up Java8 JDK
* Set up Java developer kit (JDK) not Java runtime environment (JRE)
* Set `JAVA_HOME` and `PATH` environment variables.
* See [Oracle JDK 8 installation overview](https://docs.oracle.com/javase/8/docs/technotes/guides/install/install_overview.html)

### Set up GIT client
* See [GitHub.com documentation](https://help.github.com/articles/set-up-git/)
* Set up proxy server (if required)
Command to use :
```bash
git config --global http.proxy http://proxyuser:proxypwd@proxy.server.com:8080
git config --global https.proxy https://proxyuser:proxypwd@proxy.server.com:8080
```
* change proxyuser to your proxy user
* change proxypwd to your proxy password
* change proxy.server.com to the URL of your proxy server
* change 8080 to the proxy port configured on your proxy server

* If you decide at any time to reset this proxy and work without (no proxy):
Commands to use:
```bash
git config --global --unset http.proxy
git config --global --unset https.proxy
```

* Finally, to check the currently set proxy;
```bash
git config --global --get http.proxy
git config --global --get https.proxy
```

### Set up Apache Maven
* See [Apache Maven documentation](https://maven.apache.org/install.html)
* Set M2_HOME, M2 and PATH variables.
* Set up proxy server (if required) 
Put into your ${user.home}/.m2/settings.xml the correct configuration
```xml
<settings>
  <proxies>
   <proxy>
      <id>example-proxy</id>
      <active>true</active>
      <protocol>http</protocol>
      <host>proxy.example.com</host>
      <port>8080</port>
      <username>proxyuser</username>
      <password>somepassword</password>
      <nonProxyHosts>www.google.com|*.example.com</nonProxyHosts>
    </proxy>
  </proxies>
</settings>
```
* change proxyuser to your proxy user
* change somepassword to your proxy password
* change proxy.example.com to the URL of your proxy server
* change 8080 to the proxy port configured on your proxy server

### Set up Docker and docker-compose
* See [Docker documentation](https://docs.docker.com/engine/installation/)
* This step is not required if you are doing a `local` deployment. 

### Set up MongoDB 
* See [MongoDB documentation](https://docs.mongodb.org/manual/installation/)
* This step is not required if you are doing a `docker` deployment. 
* Create database schemas
  * car-repair-service
  * data-collection-serviceF
  * maintenance-service

### Set up PostgreSQL 
* See [PostgreSQL documentation](https://wiki.postgresql.org/wiki/Detailed_installation_guides)
* This step is not required if you are doing a `docker` deployment. 
* Create database schemas
  * appointment-service
  * master-data-service
  * car-repair-service

### Set up your favorite IDE
* [IntelliJ IDEA](https://www.jetbrains.com/idea/)
* [NetBeans IDE](https://netbeans.org/)
* [Eclipse IDE for Java EE Developers](https://eclipse.org/)
* [Spring Eclipse IDE](https://spring.io/tools/eclipse)
* Set up proxy server in your IDE (if required)

#### Install Lombok plugin
* See [Lombok documentation](https://projectlombok.org/download.html)
* NetBeans: Just put lombok.jar on the classpath and enable annotation processing. More…
* Eclipse and variants: Run lombok.jar as a java app (i.e. double click it, usually) to install. Also add lombok.jar to your project. Supported variants: Springsource Tool Suite, JBoss Developer Studio. Update Maven Projects. 
* IDEA IntelliJ	A plugin developed by Michael Plushnikov adds support for most features.

## Check out the code
* If you use `{user.home}/workspace/` as your workspace directory this GIT repository will be cloned to `{user.home}/workspace/spring-cloud` 
* Either use your IDE
* Or clone the repository 
```bash
git clone https://github.com/Pirat83/spring-cloud.git
```

## Getting started
As mentioned earlier you can use 2 deployment modes. Either `local` or `docker`. We will start with the `docker` deployment since it is easier, as long your Docker host is running. 
Use the location where you cloned the repository i.e. `{user.home}/workspace/spring-cloud` to execute the following commands. 

### Docker deployment
#### Build all modules 
```bash
mvn clean install
```

#### Build docker images and start the containers
```bash
docker-compose up --build
```
* You have to replace the IP address if the Docker host with the ip of your Docker host. In my case the IP of my Docker host is `192.168.99.100`. 
* Use `docker-machine env default` to get the IP address of your Docker host. See [Docker machine documentation](https://docs.docker.com/machine/reference/ip/)
* You can access the Eureka dashboard now. Simply click `http://192.168.99.100:8761/` Remember to replace the IP address of the Docker host if required. 

#### Important Zuul proxy URLs: 
| Endpoint / Service      | URL                                                                          |
| ----------------------- | ---------------------------------------------------------------------------- |
| data-collection-service | http://192.168.99.100:8080/api/sensor                                        | 
| maintenance-service     | http://192.168.99.100:8080/api/maintenance                                   | 
| car-repair-service      | http://192.168.99.100:8080/api/car                                           |
| master-data-service     | http://192.168.99.100:8080/api/master-data                                   |
| appointment-service     | http://192.168.99.100:8080/api/appointment                                   |
Every external request should be routed through the Zuul proxy. 

### local deployment
#### Build all modules 
```bash
mvn clean install
```

#### Start Eureka-Server 
```bash
java -jar eureka-server/target/eureka-server-0.0.1-SNAPSHOT.jar
```
to verify if your eureka-server has started correctly on port 8761 open with your browser the [Eureka Dashboard](http://localhost:8761/). 

#### Start data-collection-service
```bash
java -jar data-collection-service/target/data-collection-service-0.0.1-SNAPSHOT.jar --server.port=8000 --spring.profiles.active=local
```
verify the [data-collection-service endpoint](http://localhost:8000/)

#### Start maintenance-service
```bash
java -jar maintenance-service/target/maintenance-service-0.0.1-SNAPSHOT.jar --server.port=8010 --spring.profiles.active=local
```
verify the [maintenance-service endpoint](http://localhost:8010/) 

#### Start car-repair-service
```bash
java -jar car-repair-service/target/car-repair-service-0.0.1-SNAPSHOT.jar --server.port=8020 --spring.profiles.active=local
```
verify the [car-repair-service endpoint](http://localhost:8020/) 

#### Start master-data-service
```bash
java -jar master-data-service/target/master-data-service-0.0.1-SNAPSHOT.jar --server.port=8030 --spring.profiles.active=local
```
verify the [master-data-service endpoint](http://localhost:8030/)     

#### Start appointment-service
```bash
java -jar appointment-service/target/appointment-service-0.0.1-SNAPSHOT.jar --server.port=8040 --spring.profiles.active=local
```
verify the [appointment-service endpoint](http://localhost:8040)     

#### Putting everything together
Now you got everything running. Verify that all 5 running spring-cloud are running. [Eureka application inventory](http://localhost:8761/eureka/apps/) 

#### Start Zuul-Proxy
```bash
java -jar zuul-proxy/target/zuul-proxy-0.0.1-SNAPSHOT.jar --spring.profiles.active=local
```
to verify if your zuul-proxy has started correctly on port `8080` access one service via the API endpoint [http://localhost:8080/api/sensor](http://localhost:8080/api/sensor). 

#### Hystrix Dashboard
For now Hystrix streams are not aggregated by turbine aggregator. 

#### Important endpoint URLs: 
| Endpoint / Service      | URL                                                                          | Port range |
| ----------------------- | ---------------------------------------------------------------------------- | ---------- |
| Eureka Dashboard        | http://localhost:8761/                                                       | 8761-8761  |
| Hystrix Dashboard       | http://localhost:9000/                                                       | 9000-9000  |
| data-collection-service | http://localhost:8000/sensorValues                                           | 8000-8009  | 
| maintenance-service     | http://localhost:8010/sensorValues                                           | 8010-8019  | 
| maintenance-service     | http://localhost:8010/maintenanceEvents                                      | 8010-8019  |
| car-repair-service      | http://localhost:8020/customers                                              | 8020-8029  |
| car-repair-service      | http://localhost:8020/sensorEvents                                           | 8020-8029  |
| car-repair-service      | http://localhost:8020/serviceCenters                                         | 8020-8029  |
| car-repair-service      | http://localhost:8020/maintenanceEvents                                      | 8020-8029  |
| master-data-service     | http://localhost:8030/serviceCenters                                         | 8030-8039  |
| master-data-service     | http://localhost:8030/customers                                              | 8030-8039  |
| appointment-service     | http://localhost:8040/serviceCenters                                         | 8040-8049  |
| appointment-service     | http://localhost:8040/customers                                              | 8040-8049  |
| appointment-service     | http://localhost:8040/appointments                                           | 8040-8049  |

In a local development environment you are limited to only one machine. So you have to share all resources. On modern hardware CPU, Memory, I/O are available to abound. 
However you have to share the ports of your network adapter. For this reason you have to start every module with the `--server.port` parameter. 
You can start i.e 10 instances of the data-collection-service on different ports by defining an empty port with the `--server.port` parameter.  

```bash
java -jar data-collection-service/target/data-collection-service-0.0.1-SNAPSHOT.jar --server.port=8000
java -jar data-collection-service/target/data-collection-service-0.0.1-SNAPSHOT.jar --server.port=8001
java -jar data-collection-service/target/data-collection-service-0.0.1-SNAPSHOT.jar --server.port=8002
java -jar data-collection-service/target/data-collection-service-0.0.1-SNAPSHOT.jar --server.port=8003
java -jar data-collection-service/target/data-collection-service-0.0.1-SNAPSHOT.jar --server.port=8004
java -jar data-collection-service/target/data-collection-service-0.0.1-SNAPSHOT.jar --server.port=8005
java -jar data-collection-service/target/data-collection-service-0.0.1-SNAPSHOT.jar --server.port=8006
java -jar data-collection-service/target/data-collection-service-0.0.1-SNAPSHOT.jar --server.port=8007
java -jar data-collection-service/target/data-collection-service-0.0.1-SNAPSHOT.jar --server.port=8008
java -jar data-collection-service/target/data-collection-service-0.0.1-SNAPSHOT.jar --server.port=8009
```
On the target platform every service will get its own virtual machine environment i.e an virtual machine or a container and it will run on Tomcat's native port `8080`. 
Using Spring profiles is not advisable. In this case you would hard code the port in the spring configuration. This approach would interfere dynamic auto scaling. 
And putting hard coded configuration in the built artifact is really, really bad practice - not even in cloud-native environments.  

#### Important Zuul proxy URLs: 
| Endpoint / Service      | URL                                                                          |
| ----------------------- | ---------------------------------------------------------------------------- |
| data-collection-service | http://localhost:8080/api/sensor                                             | 
| maintenance-service     | http://localhost:8080/api/maintenance                                        | 
| car-repair-service      | http://localhost:8080/api/car                                                |
| master-data-service     | http://localhost:8080/api/master-data                                        |
| appointment-service     | http://localhost:8080/api/appointment                                        |
Every external request should be routed through the Zuul proxy. 

## Send Dummy data to an HTTP endpoint
You can use your browser to generate example data. 
* `http://localhost:8080/api/sensor/dummy-data` - if you are using a `local` deployment. 
* `http://192.168.99.100:8080/api/sensor/dummy-data` -  if you are using a `docker` deployment. 

Please wait a little bit. In distributed system environments information needs some time to be replicated. 

## Useful links: 
* [How to set up environment variables in Linux, Unix, Mac and Windows environments](http://www.tutorialspoint.com/maven/maven_environment_setup.htm)
* [How to set up a Maven HTTP / HTTPS proxy](https://maven.apache.org/guides/mini/guide-proxies.html) 
* [Spring engineering blog about micro services](https://spring.io/blog/2015/07/14/microservices-with-spring)
* [Spring Profiles documentation](https://docs.spring.io/spring-boot/docs/current/reference/html/boot-features-profiles.html)
* [Overview of cloud native applications with spring-cloud](http://projects.spring.io/spring-cloud/spring-cloud.html)
* [Very interesting overview of spring-cloud components by Kenny Bastani](http://www.kennybastani.com/2015/07/spring-cloud-docker-microservices.html)
* [Simple overview of spring-cloud components](http://de.slideshare.net/mstine/dist-sys-wspringclouddeckset)
* [Overview of spring-cloud-netflix components](http://cloud.spring.io/spring-cloud-netflix/spring-cloud-netflix.html)
* [Magnus Larson's Blog Series - building micro services](http://callistaenterprise.se/blogg/teknik/2015/05/20/blog-series-building-microservices/)
* [Configuration of Hystrix](https://github.com/Netflix/Hystrix/wiki/Configuration)