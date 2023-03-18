# SpringProjectWithHSQLDB

## run database
- on Windows by entering ```./db/runHSQLDBServer.bat``` command in the console.
- on Linux by entering ```java -cp ./db/hsqldb.jar org.hsqldb.server.Server --database.0 mem:mydb --dbname.0 workdb``` command in the console

## run Spring Boot Application with InteliJ

## API endpoints

### Address endpoints
- GET localhost:8080/address
- GET localhost:8080/address/:id
- POST localhost:8080/address
- PUT localhost:8080/address/:id
- DELETE localhost:8080/address/:id

### District endpoints
- GET localhost:8080/district
- GET localhost:8080/district/:id
- POST localhost:8080/district
- PUT localhost:8080/district/:id
- DELETE localhost:8080/district/:id

### Person endpoints
- GET localhost:8080/person
- GET localhost:8080/person/:id
- POST localhost:8080/person
- PUT localhost:8080/person/:id
- DELETE localhost:8080/person/:id

- GET localhost:8080/personStream
