## Build Angular
FROM node:19 as angular

WORKDIR /app

# Copy files
COPY client/angular.json .
COPY client/package.json .
COPY client/package-lock.json .
COPY client/tsconfig.app.json .
COPY client/tsconfig.json .
COPY client/tsconfig.spec.json .
COPY client/src ./src
#
# Install Angular
RUN npm install -g @angular/cli

# Install packages and build
RUN npm i
RUN ng build

## Build Spring Boot
FROM maven:3.9.0-eclipse-temurin-19 AS springboot

WORKDIR /app

## build
COPY server/mvnw .
COPY server/mvnw.cmd .
COPY server/pom.xml .
COPY server/src ./src

# Copy compiled angular app to static directory
COPY --from=angular /app/dist/client ./src/main/resources/static

RUN mvn package -Dmaven.test.skip=true

# Copy the final Jar file
FROM eclipse-temurin:19-jre

WORKDIR /app

COPY --from=springboot /app/target/server-0.0.1-SNAPSHOT.jar server.jar

## run
ENV PORT=8080
ENV DB_SERVER=localhost
ENV DB_PORT=3306
ENV SPRING_DATASOURCE_USERNAME=root
ENV SPRING_DATASOURCE_PASSWORD=rootroot
ENV SPRING_DATASOURCE_URL=jdbc:mysql://$(DB_SERVER):$(DB_PORT)/goodreads

EXPOSE ${PORT}

ENTRYPOINT java -Dserver.port=${PORT} -jar server.jar
