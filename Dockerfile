# Dockerfile (именно так называется файл, БЕЗ расширения)
FROM openjdk:21-slim AS build
WORKDIR /app
COPY . .
RUN ./gradlew build -x test --no-daemon

FROM openjdk:21-slim
WORKDIR /app
COPY --from=build /app/build/libs/subscription-service-*.jar app.jar
EXPOSE 8082
ENTRYPOINT ["java", "-jar", "app.jar"]