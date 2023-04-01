FROM openjdk:17
ADD target/flights.jar app.jar
ENTRYPOINT ["java","-Dspring.data.mongodb.uri=mongodb://db:27017/flights","-jar","app.jar"]