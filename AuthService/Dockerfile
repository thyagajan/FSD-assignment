FROM java:8-jre
WORKDIR /usr/lib
ENV MYSQL_DATABASE=userDb
ENV MYSQL_URL=jdbc:mysql://localhost:3306/userDb
ENV MYSQL_USER=approot
ENV MYSQL_PASSWORD=root123
ADD ./target/authservice-0.0.1-SNAPSHOT.jar /usr/lib/authservice-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java","-jar","authservice-0.0.1-SNAPSHOT.jar"]
