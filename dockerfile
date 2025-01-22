FROM openjdk:17-jdk-alpine
WORKDIR /app
COPY build/libs/Tafdatastorems.jar datastorems.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "datastorems.jar"]
