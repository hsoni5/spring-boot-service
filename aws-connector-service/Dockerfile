FROM azul/zulu-openjdk-alpine:11
VOLUME /tmp
EXPOSE 8080
COPY target/aws-connector-service-1.0-SNAPSHOT.jar app.jar
ENTRYPOINT java -Djava.security.egd=file:/dev/./urandom -jar /app.jar
