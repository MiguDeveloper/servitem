FROM openjdk:11
VOLUME /tmp
EXPOSE 8002
ADD ./target/servitem-0.0.1-SNAPSHOT.jar servicio-item.jar
ENTRYPOINT ["java","-jar","/servicio-item.jar"]