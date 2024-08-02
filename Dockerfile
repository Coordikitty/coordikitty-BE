FROM openjdk:21-alpine AS builder
COPY gradlew .
COPY gradle gradle
COPY build.gradle .
COPY settings.gradle .
COPY src src
RUN chmod +x ./gradlew
RUN apk add --no-cache findutils
RUN ./gradlew build -x test

FROM openjdk:21-alpine
RUN mkdir /opt/app
COPY --from=builder build/libs/*.jar /opt/app/spring-boot-application.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "/opt/app/spring-boot-application.jar"]