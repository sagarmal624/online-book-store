# Online Book Store

[![Build Status](https://travis-ci.org/joemccann/dillinger.svg?branch=master)](https://travis-ci.org/joemccann/dillinger)

ReadingIsGood is an online books retail firm which operates only on the Internet. Main target of ReadingIsGood is to deliver books from its one centralized warehouse to their customers within the same day. That is why stock consistency is the first priority for their vision operations.

## FEATURES

- Registering New Customer
- List of Customers with pagination
- Adding books in store
- Fetch all books with pagination
- Placing a new order
- Tracking the stock of all books
- Get Details of Stock
- List all orders of the login customer with Pagination
- Viewing the order details

## Technology stack


- **Java-11**:  Java™ is the world's leading programming language and platform
- **Spring Boot** - Latest version 2.6.2
- **Spring Security With JWT**: https://jwt.io/introduction
- **Lombook** - Project Lombok is a java library that automatically plugs into your editor and build tools, spicing up  your java.Never write another getter or equals method again, with one annotation your class has a fully featured builder, Automate your logging variables, and much more. https://projectlombok.org/
- **H2 Database** - H2 is one of the popular in-memory databases written in Java. H2 can be embedded in Java applications or run in the client-server mode
- **Swagger Open API Specification** - Swagger is an Interface Description Language for describing RESTful APIs expressed using JSON. Swagger is used together with a set of open-source software tools to design, build, document, and use RESTful web services. Swagger includes automated documentation, code generation, and test-case generation. https://swagger.io/
- **Docker** - Docker is the containerization platform which is used to package your application and all its dependencies together in the form of containers so to make sure that your application works seamlessly in any environment which can be development or test or production. Docker is a tool designed to make it easier to create, deploy, run applications by using containers.
- **Spring Data JPA(Hibernate/JPA)**:Spring Data JPA, part of the larger Spring Data family, makes it easy to easily implement JPA based repositories. This module deals with enhanced support for JPA based data access layers. It makes it easier to build Spring-powered applications that use data access technologies.https://spring.io/projects/spring-data-jpa
- **Maven Build Tool**: Maven is a build automation tool used primarily for Java projects.https://maven.apache.org/

## Plugins

BookStore is currently using the following plugins.
Instructions on how to use them in your own application are linked below.

| Plugin           | Documentation URL                |
|------------------|----------------------------------|
| Sonar            | https://www.sonarqube.org/       |
| JaCoCo           | https://github.com/jacoco/jacoco |

## Current Sonar Code Quality Coverage For Online Book Store
![alt text](https://raw.githubusercontent.com/sagarmal624/online-book-store/master/images/sonar-coverage.png)
## JACOCO Coverage for Controllers

![alt text](https://raw.githubusercontent.com/sagarmal624/online-book-store/master/images/jacco-controller.png)

## JACOCO Coverage for Services and its implementation

![alt text](https://raw.githubusercontent.com/sagarmal624/online-book-store/master/images/jacco-service.png)


## Installation

Project requires Java11 ,Maven ,IDE to run.

Install the dependencies.

```sh
cd online-book-store
mvn clean install
```

For Test Case Exceution

```sh
mvn clean test
```
For packaging or build Project

```sh
mvn clean package
```

Maven Command to run Project in Local System

```sh
mvn spring-boot:run
```

### Swagger URL

http://localhost:8080/swagger-ui/index.html?configUrl=/api-docs/swagger-config#/

## Postman collection

 https://github.com/sagarmal624/online-book-store/blob/master/Book%20Store.postman_collection.json

## Sample Data:

 you can find sample book data.

  https://github.com/sagarmal624/online-book-store/blob/master/src/main/resources/books.json


## API End Point

Server is running at 8080 port by default. check out POSTMAN Collection for request and response for following API.

This application manages stocks for books. It exposes REST endpoints to create and update stock of books in the warehouse. The application can handle multiple concurrent transactions and is resilient to transaction failures. In case of a failed transaction, the application can rollback the transaction, maintaining the consistency of underlying database.


```sh
/api/v1/customer:   Its used to register a customer into book store.check out POSTMAN Collection for request and response of this API.
```

```sh
api/v1/oauth/token:   Its used to get JWT token for registered customer with username/email and password
```


Following API's needs Authorization header so need to pas JWT Bearer token in it.

```sh
/api/v1/book:   This API is used to create or add book in our store with zero stock.
```
```sh
/api/v1/stock/book/{bookId}: This is PUT type API and  With this API we can add/update stock of specific book in our store.
```

```sh
/api/v1/book?page=0&size=10: Its used to fetch all books details with pagination.
```

```sh
api/v1/stock/books?page=0&size=10: Fetch all books Stock details with pagination.
```

```sh
api/v1/stock/book/{bookId}: Fetch Stock details for specific book.
```

```sh
/api/v1/order:  It's used to create or palce order for list of books and internalty it will check stock is exist or not. 
if its exist then proceed further and lock required quantity of stock till order completion.it can reverback stock if anything
failure at runtume and it also internaly system maintaining version field to handle multiple updates by multile userin same 
record at same time. whenever version field value mismatch then system will throw runtime exception OptimisticLockException
which means record is updated by someone else. if everyhng is saved successfully then order is created and will notify with success message.

```

```sh
/api/v1/order/{orderId}: its used to fetch order details for specific order.
```
```sh
api/v1/order?page=0&size=10: fetch list of all order for login customer with pagination.
```
```sh
api/v1/statistic/customer/report/monthly/: its used to fetch login customer monthly report for all orders. 
```
## H2 Database access
You can access H2 Database with below URL in Local System. H2-Console is by default disable in Docker Env due to Security.

http://localhost:8080/h2-console

![alt text](https://raw.githubusercontent.com/sagarmal624/online-book-store/master/images/h2.png)

## Docker


By default, the Docker will expose port 8080, so change this within the
Dockerfile if necessary. When ready, simply use the Dockerfile to
build the image.

### Build Docker Image

```sh
 docker build -f Dockerfile -t book-store .
```

Once done, run the Docker image and map the port to whatever you wish on
your host. In this example, we simply map port 8000 of the host to
port 8080 of the Docker (or whatever port was exposed in the Dockerfile):

### Run Docker image in local system
```sh

docker run -p 8080:8080 -d book-store

```

### Pull Image

I already push image of book-store so you can directly use it for your purpose.

```sh
docker pull sagarandcompany/book-store
```

