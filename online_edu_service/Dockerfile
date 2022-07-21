FROM openjdk:8-jdk-alpine
VOLUME /tmp
COPY ./target/online_edu_service.jar online_edu_service.jar
ENTRYPOINT ["java","-jar","/online_edu_service.jar", "&"]