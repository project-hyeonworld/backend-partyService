#!/bin/bash

#Define variables
PROJECT_NAME="party-service"
DOCKER_IMAGE_NAME="party-service"
DOCKER_CONTAINER_NAME="spring-boot"
PROJECT_PORT="10003"

echo "Building the Spring Boot application..."
./gradlew clean build

if [ $? -ne 0 ]; then
    echo "build failed. Exiting."
    exit 1
fi

# Create Dockerfile
if [ ! -f Dockerfile ]; then
    echo "Creating Dockerfile..."
    cat <<EOF > Dockerfile
FROM eclipse-temurin:21-jdk-alpine

# Copy the JAR file into the container
COPY ./target/*.jar app.jar

# Set the command to run the application
ENTRYPOINT ["java", "-jar", "app.jar"]
EOF
fi

# Build the Docker image
echo "Building Docker image..."
docker build -t $DOCKER_IMAGE_NAME .
if [ $? -ne 0 ]; then
    echo "Docker build failed. Exiting."
    exit 1
fi

# Run the Docker container
echo "Running Docker container..."
docker run --name $DOCKER_CONTAINER_NAME -p $PROJECT_PORT:$PROJECT_PORT $DOCKER_IMAGE_NAME

# Check if the Docker run was successful
if [ $? -ne 0 ]; then
    echo "Failed to run Docker container. Exiting."
    exit 1
fi

echo "Spring Boot application is running at http://localhost:$PROJECT_PORT"