FROM maven:3.6.0-jdk-8-alpine as builder
COPY  . /root/app/
WORKDIR /root/app
RUN mvn install -DskipTests

FROM openjdk:8-jdk-alpine as jdk8
EXPOSE 8081
COPY --from=builder /root/app/ /home/app/
WORKDIR /home/app
ENTRYPOINT ["java","-jar", "-Xmx15m", "./target/spring-service-0.0.1-SNAPSHOT.jar"]