FROM eclipse-temurin:17
WORKDIR workspace
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} catalog.jar
ENTRYPOINT ["java", "-jar", "catalog.jar"]
