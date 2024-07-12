# Library Management System

This project is a simple Library Management System implemented in Java using Spring Boot and GraphQL. The application allows you to manage books and departments in a library, including adding, removing, and searching for books. The system also initializes with a default department when started.

## Features

- Manage a collection of books in multiple departments.
- Add new books to the library.
- Remove books from the library.
- Find books by title or author.
- List all books in the library.
- List only the available books.

## Technologies Used

- Java
- Spring Boot
- GraphQL
- H2 Database

## Starting Application

### Prerequisites

- Java 17 or later
- Maven

### Installing

1. **Clone the repository:**

   ```bash
   git clone https://github.com/ashutosh2401/library-management-spring-graphqp.git
   cd library-management-system

2. **Build the project:**

   ```bash
   mvn clean install

3. **Run the application:**

   ```bash
   mvn spring-boot:run

## Usage

The application exposes GraphQL endpoints for interacting with the library system. You can use a GraphQL client (e.g., Postman) to perform the following operations:

### Add a Book

   ```bash
   mutation {
      addBook(
       title: "Your Title Here",
       author: "Author Info",
       ISBN: "12345",
       genre: "Fiction",
       publicationYear: 2000,
       availability: true,
       departmentId: 1
      ) {
       id
       title
       author
       ISBN
       genre
       publicationYear
       availability
       department {
         name
       }
     }
   }
   ```

### List All Books

   ```bash
   query {
     books {
       id
       title
       author
       ISBN
       genre
       publicationYear
       availability
       department {
         name
       }
     }
   }
   ```

### Find Book by Title

   ```bash
   query {
     bookByTitle(title: "Your Title Here") {
       id
       title
       author
       ISBN
       genre
       publicationYear
       availability
       department {
         name
       }
     }
   }
   ```

### Find Book by Author

   ```bash
   query {
     bookByAuthor(author: "Author Info") {
       id
       title
       author
       ISBN
       genre
       publicationYear
       availability
       department {
         name
       }
     }
   }
   ```

### List Available Books

   ```bash
   query {
     availableBooks {
       id
       title
       author
       ISBN
       genre
       publicationYear
       availability
       department {
         name
       }
     }
   }
   ```


### Remove a Book

   ```bash
   mutation {
     removeBook(ISBN: "12345")
   }
   ```

## Running Tests

To run the tests, use the following command on terminal:

   ```bash
   ./mvnw test
   ```

## Authors

Ashutosh Mishra