# Cookbook

## Build
To build the final jar, run (tests are skipped to speed up the build):

```
 docker run -it --rm  -v "$(pwd)":/app -w /app maven:3.6.3-jdk-11 mvn clean install -DskipTests
```

## Run tests
To test the project, run:

```
 docker run -it --rm --privileged=true -v /var/run/docker.sock:/var/run/docker.sock -v "$(pwd)":/app -w /app maven:3.6.3-jdk-11 mvn test
```

## Run
To start the project, run:

```
docker-compose build && docker-compose up   
```
