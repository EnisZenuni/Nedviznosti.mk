Microservice architecture using Spring Cloud Distributed System patterns.
- JWT Token prefilter and redirection the authentication microservice before hitting the API gateway
- API Gateway to redirect request to the corresponding microservice
- Spring Cloud Eureka to allow services to register themselves and discover other services
- Open Feign for communication between microservices
- Config server to handle all the common properties of the microservices is one place
