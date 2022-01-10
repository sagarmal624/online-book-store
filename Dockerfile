FROM adoptopenjdk/openjdk11
ADD target/book-store-0.0.1-SNAPSHOT.jar  book-store-0.0.1-SNAPSHOT.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","book-store-0.0.1-SNAPSHOT.jar", "-web -webAllowOthers -tcp -tcpAllowOthers -browser"]