FROM java:8-jre
WORKDIR /usr/lib
ENV MONGO_DB_NAME=userTrackDb
ENV MONGO_DB_URL=mongodb://localhost:27017/userTrackDb
ADD ./target/usertrackservice-0.0.1-SNAPSHOT.jar /usr/lib/usertrackservice-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java","-jar","usertrackservice-0.0.1-SNAPSHOT.jar"]
