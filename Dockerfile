FROM openjdk:8-jre-alpine
LABEL maintainer="Kiana Almonte"
EXPOSE 8080
ADD /build/libs/dockercomposeP4-1.0-SNAPSHOT.jar app.jar
VOLUME /tmp
CMD ["java", "-jar", "app.jar"]