FROM openjdk:17
LABEL maintainer="Harish"
ADD target/demo-0.0.1-SNAPSHOT.jar cricket-docker.jar
ENTRYPOINT ["java","-jar","cricket-docker.jar"]