# Start with a base image containing Java runtime
FROM java:8

ADD ./target/restful-web-services.jar //

# Run the jar file
ENTRYPOINT ["java","-jar","restful-web-services.jar"]
