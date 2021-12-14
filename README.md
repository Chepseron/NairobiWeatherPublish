# NairobiWeatherPublish


The application runs using the following environment


- JDK 1.8 
- Rabbit MQ version 3.9.11 and erlang version 24.1.7
- Libraries including (amqp-client-5.14.0.jar, json-20210307.jar)

To Execute the project proceed with the following 

# Rabbit MQ installation 
- Use the following link to install Rabbit MQ https://www.rabbitmq.com/download.html
- Specify the server running Rabbit MQ on your application on the source line # 60 or store the path in the config.properties file then source from the application
- Navigate to NairobiWeatherPublish\dist on your project and execute java -jar NairobiWeatherPublish.jar

For any additions please clone the project and commit your changes 
