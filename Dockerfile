FROM adoptopenjdk/openjdk11:latest
ENV JAR_FILE=proposta-0.0.1-SNAPSHOT.jar
ENV PROFILE=${PROFILE}
COPY target/${JAR_FILE} proposta.jar
ENTRYPOINT java -jar proposta.jar --spring.profiles.active=${PROFILE}
EXPOSE 8081