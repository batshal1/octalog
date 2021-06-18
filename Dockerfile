# Start with a base image containing Java runtime
FROM java:8

COPY target/restful-web-services.jar restful-web-services.jar

# Run the jar file
CMD ["java","-jar","restful-web-services.jar"]
