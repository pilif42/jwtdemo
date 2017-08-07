# To build
mvn clean install


# To run
mvn spring-boot:run


# To test
curl http://localhost:8080/ -v -X GET
200 hello world

curl http://localhost:8080/users -v -X GET
200 {"users":[{"firstname":"Richard", "lastname":"Feynman"},{"firstname":"Marie","lastname":"Curie"}]}
