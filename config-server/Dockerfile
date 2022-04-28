FROM openjdk:17-alpine
ARG JAR_FILE=/target/spring-boot-web.jar
COPY ${JAR_FILE} app.jar
RUN apk update
RUN apk add curl
ENTRYPOINT ["java","-jar","app.jar"]
EXPOSE 8080