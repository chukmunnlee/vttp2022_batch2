FROM maven:3.9.0-eclipse-temurin-19 AS builder

WORKDIR /app

## build
COPY mvnw .
COPY mvnw.cmd .
COPY pom.xml .
COPY src src

RUN mvn package -Dmaven.test.skip=true

###############
FROM eclipse-temurin:19-jre

WORKDIR /app

COPY --from=builder /app/target/dovbear-0.0.1-SNAPSHOT.jar dovbear.jar

## run
ENV PORT=3000

EXPOSE ${PORT}

ENTRYPOINT java -Dserver.port=${PORT} -jar dovbear.jar