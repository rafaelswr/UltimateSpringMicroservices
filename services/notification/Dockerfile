
FROM openjdk:17

WORKDIR /app

COPY target/notification-0.0.1-SNAPSHOT.jar /app/notification.jar

EXPOSE 8060

CMD ["java", "-jar" , "notification.jar"]