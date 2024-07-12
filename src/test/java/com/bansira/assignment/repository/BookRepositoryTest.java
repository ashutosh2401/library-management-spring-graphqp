package com.bansira.assignment.repository;


import com.bansira.assignment.model.Book;
import com.bansira.assignment.model.Department;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
public class BookRepositoryTest {
    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private DepartmentRepository departmentRepository;

    private Department department;

    @BeforeEach
    public void setup() {
        department = new Department();
        department.setName("Fiction");
        departmentRepository.save(department);
    }

    @Test
    public void testAddBook() {
        Book book = new Book();
        book.setTitle("Book Title");
        book.setAuthor("Author Name");
        book.setISBN("1234567890");
        book.setGenre("Fiction");
        book.setPublicationYear(2021);
        book.setAvailability(true);
        book.setDepartment(department);

        bookRepository.save(book);

        List<Book> books = bookRepository.findAll();
        assertThat(books).hasSize(1);
    }

    @Test
    public void testFindBookByTitle() {
        Book book = new Book();
        book.setTitle("Book Title");
        book.setAuthor("Author Name");
        book.setISBN("1234567890");
        book.setGenre("Fiction");
        book.setPublicationYear(2021);
        book.setAvailability(true);
        book.setDepartment(department);

        bookRepository.save(book);

        List<Book> books = bookRepository.findByTitleIgnoreCase("book title");
        assertThat(books).hasSize(1);
    }

    @Test
    public void testFindBookByAuthor() {
        Book book = new Book();
        book.setTitle("Book Title");
        book.setAuthor("Author Name");
        book.setISBN("1234567890");
        book.setGenre("Fiction");
        book.setPublicationYear(2021);
        book.setAvailability(true);
        book.setDepartment(department);

        bookRepository.save(book);

        List<Book> books = bookRepository.findByAuthorIgnoreCase("author name");
        assertThat(books).hasSize(1);
    }

    @Test
    public void testListAvailableBooks() {
        Book book = new Book();
        book.setTitle("Book Title");
        book.setAuthor("Author Name");
        book.setISBN("1234567890");
        book.setGenre("Fiction");
        book.setPublicationYear(2021);
        book.setAvailability(true);
        book.setDepartment(department);

        bookRepository.save(book);

        List<Book> books = bookRepository.findByAvailability(true);
        assertThat(books).hasSize(1);
    }

    @Test
    public void testRemoveBook() {
        Book book = new Book();
        book.setTitle("Book Title");
        book.setAuthor("Author Name");
        book.setISBN("1234567890");
        book.setGenre("Fiction");
        book.setPublicationYear(2021);
        book.setAvailability(true);
        book.setDepartment(department);

        bookRepository.save(book);
        bookRepository.deleteByISBN("1234567890");

        List<Book> books = bookRepository.findAll();
        assertThat(books).isEmpty();
    }
}