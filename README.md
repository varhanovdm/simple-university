# simple-university
- Simple test **Spring Boot application** with the console interface for university, which consists of departments and lectors.
- The lectors could work in more than one department.
- Lector could have one degree (`ASSISTANT, ASSOCIATE_PROFESSOR, PROFESSOR`).

## Installation

- Clone this repo to your local machine and run application with your IDEA
- Test data will be automatically loaded using Liquibase.

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
## Scope
### Commands:
The application implements the following commands:

        User Input: Who is head of department {department_name}
        
        Answer: Head of {department_name} department is {head_of_department_name}
---
        User Input: Show {department_name} statistics
        
        Answer: assistans - {assistams_count}
                associate professors - {associate_professors_count}
                professors -{professors_count}
---
        User Input: Show the average salary for the department {department_name}
        
        Answer: The average salary of {department_name} is {average_salary}
---
        User Input: Show count of employee for {department_name}
        
	    Answer: {employee_count}
---
        User Input: Global search by {template}  
         
        Example: Global search by van
	    Answer: Ivan Petrenko, Petro Ivanov

### Example:

- `Who is head of department Marketing`

     _result:_
    `Head of Marketing department is Rick Novak`

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
