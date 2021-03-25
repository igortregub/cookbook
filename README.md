# Cookbook

Implementation sample: http://cookbook.tregub.dev/ 

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

## Technical specification
You need to write a simple Cookbook application in Java / Spring Boot. The application is basically a hierarchical tree of recipes.
Required features
* Display a list of recipes (date created, description)
* Add a new recipe
* Add a new recipe based on another one - culinary “fork” of a recipe (e.g. Fried Chicken -> Fried Chicken with Mayo -> Fried Chicken with Mayo and Mustard). Child recipes do not inherit anything from a parent recipe - they are just shown as children of a parent one. User can add child recipes to recipes on any depth.
* Modify an existing recipe (on any depth)
* View all previous recipe versions (on any depth)
* Recipes should be sorted alphabetically (on any depth).
Server-side must be programmed in Java / Spring Boot. Building the client-side, feel free to choose any popular front end framework you like - it can be Angular, React or Vue.
