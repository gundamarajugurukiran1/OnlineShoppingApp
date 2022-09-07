FROM adoptopenjdk:11-jre-hotspot

EXPOSE 8082

COPY ./target/userservice-0.0.1-SNAPSHOT.jar userservice.jar

ENTRYPOINT ["sh", "-c"]
CMD ["exec java -jar ./userservice.jar"]