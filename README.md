## Spring boot project

### Dependencies:

* H2
* Spring Data JPA
* Spring Web
* Spring Boot Dev tools

API rest with database access

## Entity

### Book:

* Id
* Title
* Pages
* Price
* Release Date

## Endpoints

### GET

* */api/books*  : **It should return all books**
* */api/books/{id}* : **It should return a book by id**

### POST

* */api/books* : **It should create a book and save in the DB**

### PUT

* */api/books/{id}* : **It should update a book by id**

### DELETE

* */api/books/{id}* : **It should delete a book by id**
* */api/books* : **It should delete all books**

