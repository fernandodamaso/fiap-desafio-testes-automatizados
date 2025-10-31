FROM maven:3.9-eclipse-temurin-21 AS builder
WORKDIR /app
COPY pom.xml .
RUN mvn dependency:resolve
COPY src ./src
RUN mvn package -DskipTests

FROM eclipse-temurin:21-jre-jammy
WORKDIR /app
COPY --from=builder /app/target/projeto-esg-fiap-0.0.1-SNAPSHOT.jar app.jar
EXPOSE 8080
ENV SPRING_PROFILES_ACTIVE=docker
ENV SPRING_DATASOURCE_URL=jdbc:oracle:thin:@oracle-db-service:1521/ORCLPDB1
ENV SPRING_DATASOURCE_USERNAME=app_user_mock
ENV SPRING_DATASOURCE_PASSWORD=app_password_mock
ENV SPRING_JPA_HIBERNATE_DDL_AUTO=validate
ENV SPRING_FLYWAY_BASELINE_ON_MIGRATE=true
ENTRYPOINT ["java", "-jar", "app.jar"]