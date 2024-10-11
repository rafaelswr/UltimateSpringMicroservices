FROM openjdk:17

WORKDIR /app

COPY target/product-0.0.1-SNAPSHOT.jar /app/product.jar

EXPOSE 8989

CMD ["java", "-jar", "product.jar"]


