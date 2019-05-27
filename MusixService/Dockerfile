FROM java:8-jre
WORKDIR /usr/src
ENV MONGO_DB_NAME=musixdb
ENV MONGO_DB_URL=mongodb://localhost:27017/musixdb
ADD ./target/musixservice-0.0.1-SNAPSHOT.jar /usr/src/musixservice-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java","-jar","musixservice-0.0.1-SNAPSHOT.jar"]
