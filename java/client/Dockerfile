FROM maven:3.8.5-openjdk-17 AS builder

WORKDIR /app

COPY . .

RUN mvn clean install -DskipTests

FROM openjdk:17-slim

WORKDIR /app

COPY --from=builder /app/target/my-docker-app-1.0-SNAPSHOT.jar /app/app.jar

EXPOSE 8080

CMD ["java", "-jar", "app.jar"]
