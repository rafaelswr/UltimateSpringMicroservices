FROM openjdk:17

WORKDIR /app

COPY target/customer-0.0.1-SNAPSHOT.jar /app/customer.jar

EXPOSE 8099

CMD ["java", "-jar", "customer.jar" ]