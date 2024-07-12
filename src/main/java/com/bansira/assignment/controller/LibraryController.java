package com.bansira.assignment.controller;

import com.bansira.assignment.model.Book;
import com.bansira.assignment.service.LibraryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
public class LibraryController {

    @Autowired
    private LibraryService libraryService;

    @QueryMapping("books")
    public List<Book> listAllBooks() {
        return libraryService.listAllBooks();
    }

    @QueryMapping("bookByTitle")
    public List<Book> findBookByTitle(@Argument String title) {
        return libraryService.findBookByTitle(title);
    }

    @QueryMapping("bookByAuthor")
    public List<Book> findBookByAuthor(@Argument String author) {
        return libraryService.findBookByAuthor(author);
    }

    @QueryMapping("availableBooks")
    public List<Book> listAvailableBooks() {
        return libraryService.listAvailableBooks();
    }

    @MutationMapping("addBook")
    public Book addBook(@Argument String title, @Argument String author, @Argument String ISBN, @Argument String genre, @Argument int publicationYear, @Argument boolean availability, @Argument Long departmentId) {
        return libraryService.addBook(title, author, ISBN, genre, publicationYear, availability, departmentId);
    }

    @MutationMapping("removeBook")
    public void removeBook(@Argument String ISBN) {
        try {
            libraryService.removeBook(ISBN);
            System.out.println("Book record deleted successfully.");
        } catch (Exception e) {
            System.out.println("Exception occurred during deleting book. " + e.getMessage());
        }
    }

}
