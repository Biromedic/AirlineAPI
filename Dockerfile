FROM openjdk:17
WORKDIR /app
COPY AirlineAPI-0.0.1-SNAPSHOT.jar AirlineAPI.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "AirlineAPI.jar"]
