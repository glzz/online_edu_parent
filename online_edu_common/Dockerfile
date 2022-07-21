FROM openjdk:8-jdk-alpine
VOLUME /tmp
COPY ./target/online_edu_common.jar online_edu_common.jar
ENTRYPOINT ["java","-jar","/online_edu_common.jar", "&"]