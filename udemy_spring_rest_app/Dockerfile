FROM amazoncorretto:17.0.8-alpine3.18
ARG JAR_FILE=build/libs/*.jar
COPY ${JAR_FILE} example_api_gateway.jar
ENTRYPOINT ["java", "-jar", "example_api_gateway.jar"]