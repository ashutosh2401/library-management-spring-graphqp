package com.bansira.assignment.service;

import com.bansira.assignment.model.Book;
import com.bansira.assignment.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LibraryService {

    @Autowired
    private BookRepository bookRepository;

    public Book addBook(Book book) {
        return bookRepository.save(book);
    }

    public void removeBook(String ISBN) {
        Book book = bookRepository.findByISBN(ISBN);
        if (book != null) {
            bookRepository.delete(book);
        }
    }

    public List<Book> findBookByTitle(String title) {
        return bookRepository.findByTitleIgnoreCase(title);
    }

    public List<Book> findBookByAuthor(String author) {
        return bookRepository.findByAuthorIgnoreCase(author);
    }

    public List<Book> listAllBooks() {
        return bookRepository.findAll();
    }

    public List<Book> listAvailableBooks() {
        return bookRepository.findByAvailability(true);
    }
}
