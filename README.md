#MusixApplication
A web application to keep track of your favorite tracks. This app is developed using Angular with the backend support of 
few microservices developed in spring boot. All the images are dockerized and docker compose file can be used to get this app up and running.

for details please check the docker-compose.yml file.

After running the command "docker-compose up", verify if all the services are healthy by checking "docker ps -a".

Then the aplication can be accessed at http://localhost:8080

#Microservices
Our application is using the following microservices.

    #AuthenticationService: Port  9001
        To authenticate and provide JWT token to the authorized users.
    
        For API documentation refer:
            http://localhost:9001/swagger-ui.html
        
        It is using MYSQL DB to store the user name and password. MySql DB can be accessed by
        mysql -u approot -p 
        (provide the password as per the docker file)
    
        
    #UserTrackService : port 9002
        To manage the tracks for a particular user.
        
        For API documentation refer
            http://localhost:9002/swagger-ui.html
            
        This service is using MongoDB to store the tracks.
        
    #EurekaServer: port 9003
        Netflix Eureka Discovery server and currently authentication service and usertrackservice are registered as its clients.
        
    #ZuulService: port 9005
        Netflix proxy server for the frontend to communicate with the services.
        
    #RabbitMq : port 15672
        For the interservice communication we are using rabbit MQ with the exchange "user_exchange" and two queues
        "user_queue" - to pass the user data.
        "track_queue" - to pass the track data.

    #MySQL runs on the port 3306
    
    #MongoDB runs on the port 27017

