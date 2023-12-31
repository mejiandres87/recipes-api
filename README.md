# RECIPE API
This repository creates a REST API made with `java 1.8` using `spring boot 3.1.4` 

## ASSUPMTIONS AND RESTRICTIONS

### SECURITY
As established by the requirements all API calls will be authenticated using the `Authorization` header with a token provided by the same app.

### RELIABIITY AND PERFORMANCE
There's a direct dependency on a 3rd party app which can add up to response times due to an unavailable server or a delayed response. Doir the development of this app there will be a 30 second timeout limit for the 3rd party. If a request exceeds this limit the connection will be ended and a 500 error will be sent to the client.

No retry policies will be implemented.

### ABOUT RATING SYSTEM
As there is no explicit restrictions on the requirements, a `rating` or a `recipe` will be considered as a Many to Many relation with a logged user.

### VERSION CONTROL
Following the requirements, the code should be provided on a public repository where all the work in progress will be committed on the `develop` branch while all the tested code will be promoted to the `master` branch.

### ARCHITECTURE
The app depends on a  `MySQL` instance for storing data and a `Apache Kafka` broker for processing async tasks (Fetching the sourceURL contents of each recipe)

### DEPLOYMENT AND EXECUTION
A `docker-compose.yml` file is provided with the dependencies of the app.
Under the `deployment` folder you can run to start the dependencies:

``` bash
  docker-compose up -d
```

Once the dependencies are up, you will need to add some roles to the database. So, connect to the `MySQL` instance and run the following statements:

``` SQL
  INSERT INTO roles(name) VALUES('ROLE_USER');
  INSERT INTO roles(name) VALUES('ROLE_ADMIN');
```

Then you can start the app by building it first. Navigate into the recipe-api folder and run:

``` bash
  mvn package
```

Afterwards you can run the app by using the java command under the same folder:

``` bash
  java -jar target/recipe-api-0.0.1-SNAPSHOT.jar
```
