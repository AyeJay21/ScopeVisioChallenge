FROM gradle:8.5.0-jdk17 AS build

COPY --chown=gradle:gradle . /home/gradle/src
WORKDIR /home/gradle/src/CodingChallenge
RUN chmod +x gradlew
RUN ./gradlew build --no-daemon -x test

FROM eclipse-temurin:17-jre
WORKDIR /app
EXPOSE 9024
COPY --from=build /home/gradle/src/CodingChallenge/build/libs/*.jar /app/spring-boot-application.jar
COPY --from=build /home/gradle/src/CodingChallenge/postcodes.csv /app/resources/postcodes.csv
CMD ["java", "-jar", "/app/spring-boot-application.jar"]