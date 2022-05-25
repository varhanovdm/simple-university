# simple-university
- Simple test **Spring Boot application** with the console interface for university, which consists of departments and lectors.
- The lectors could work in more than one department.
- Lector could have one degree (`ASSISTANT, ASSOCIATE_PROFESSOR, PROFESSOR`).

## Installation
### Required to install
- Java 11
- Maven
- PostgreSQL
- IntelliJ IDEA (optional)

### Environmental variables

First of all, you need to check and if it would any necessary to set environment variables
at `application.properties` which contains in `simple-university/src/main/resources` module.

```properties
spring.datasource.url=${DATASOURCE_URL}
spring.datasource.username=${DATASOURCE_USER}
spring.datasource.password=${DATASOURCE_PASSWORD}
```

### Example comands:
- `Show Finance statistics`

   _result:_
    `assistans - 0
     associate professors - 2
     professors - 1`
     
- `Show the average salary for the department Marketing`

     _result:_
    -`The average salary of Marketing is 1472.0`
      
- `Show count of employee for Marketing`

    _result:_ 
    `5`

- `Global search by ke`

    _result:_ 
    `Jake Taylor, Jake Thomas, Mike Johnson, Mike Clark`
