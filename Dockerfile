FROM maven:3.9.6-eclipse-temurin-21-alpine
LABEL authors="brendaStefany"

WORKDIR /app

COPY . /app

RUN mvn clean install package -DskipTests
RUN mv target/*.jar ./app.jar

FROM openjdk:21-jdk-oracle

COPY --from=0 /app/app.jar /app.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "app.jar"]