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

### Author:

* Id
* Complete Name
* Birth Date
* Death Date
* Country

## Endpoints

### GET

* */api/books*  : **It should return all books**
* */api/books/{id}* : **It should return a book by id**
* */api/authors* : **It should return all authors**
* */api/authors/{id}* : **It should return an author by id**

### POST

* */api/books* : **It should create a book and save in the DB**
* */api/authors* : **It should create an author and save in the DB**

### PUT

* */api/books/{id}* : **It should update a book by id**
* */api/authors/{id}* : **It should update an author by id**

### DELETE

* */api/books/{id}* : **It should delete a book by id**
* */api/books* : **It should delete all books**

