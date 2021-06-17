# Start with a base image containing Java runtime
FROM java:8

# Make port 8080 available to the world outside this container
EXPOSE 8080

COPY ./target/restful-web-services.jar
WORKDIR ./restful-web-services

# Run the jar file
ENTRYPOINT ["java","-jar","restful-web-services.jar"]
