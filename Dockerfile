

FROM openjdk:17
EXPOSE 8081
COPY target/demo-1.0.0.jar demo-1.0.0.jar
ENTRYPOINT ["java","-jar","/demo-1.0.0.jar"]
