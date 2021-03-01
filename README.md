# Cookbook

## Build
To build the final jar, run:

```
 docker run -it --rm  -v "$(pwd)":/app -w /app maven:3.6.3-jdk-11 mvn clean install
```

## Run tests
To test the project, run:
```
 docker run -it --rm  -v "$(pwd)":/app -w /app maven:3.6.3-jdk-11 mvn test
```


## Run

To start the project, run:

```
docker-compose build && docker-compose up   
```