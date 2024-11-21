FROM maven:3.8.5-openjdk-11 AS builder

WORKDIR /app

COPY . .

RUN mvn clean install -DskipTests

FROM openjdk:11-jre-slim

WORKDIR /app

COPY --from=builder /app/target/grpc-pet-service-1.0-SNAPSHOT.jar /app/app.jar

EXPOSE 50051

CMD ["java", "-jar", "app.jar"]
