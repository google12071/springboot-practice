FROM openjdk:8-jdk-alpine
VOLUME /tmp
ADD springboot-practice-1.0.0-SNAPSHOT.jar springboot-practice.jar
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/springboot-practice.jar"]