# To build
mvn clean install


# To run
mvn spring-boot:run


# To test
curl http://localhost:8080/ -v -X GET
200 hello world

curl http://localhost:8080/users -v -X GET
403 {"timestamp":1502103855226,"status":403,"error":"Forbidden","message":"Access Denied","path":"/users"}

curl  -H "Accept: application/json" -H "Content-Type: application/json" http://localhost:8080/login -v -X POST  -d "{\"username\":\"admin\",\"password\":\"password\"}"
200 Authorization: Bearer eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJhZG1pbiIsImV4cCI6MTUwMjk2ODEzNn0.Ad-nSxg9VMhoWr5MWof3QhFS-fP1Otx5gbAAyDg89uXqgCJcZMqTkX6jS1ivuzUt8XvU2AGS_99Ot5p-_8pZmg

curl -H "Authorization: Bearer eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJhZG1pbiIsImV4cCI6MTUwMjk2ODEzNn0.Ad-nSxg9VMhoWr5MWof3QhFS-fP1Otx5gbAAyDg89uXqgCJcZMqTkX6jS1ivuzUt8XvU2AGS_99Ot5p-_8pZmg" http://localhost:8080/users -v -X GET
200 {"users":[{"firstname":"Richard", "lastname":"Feynman"},{"firstname":"Marie","lastname":"Curie"}]}
