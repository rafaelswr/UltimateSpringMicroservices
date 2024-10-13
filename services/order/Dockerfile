FROM openjdk:17

WORKDIR /app

COPY target/order-0.0.1-SNAPSHOT.jar /app/order.jar

EXPOSE 8070

CMD ["java", "-jar", "order.jar"]