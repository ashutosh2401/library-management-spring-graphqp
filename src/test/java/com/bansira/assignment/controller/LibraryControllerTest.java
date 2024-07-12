package com.bansira.assignment.controller;

import com.bansira.assignment.model.Book;
import com.bansira.assignment.model.Department;
import com.bansira.assignment.service.LibraryService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

public class LibraryControllerTest {
    @Mock
    private LibraryService libraryService;

    @InjectMocks
    private LibraryController libraryController;

    private Book book;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
        book = new Book();
        book.setTitle("Book Title");
        book.setAuthor("Author Name");
        book.setISBN("1234567890");
        book.setGenre("Fiction");
        book.setPublicationYear(2021);
        book.setAvailability(true);
        Department department = new Department();
        department.setName("Fiction");
        book.setDepartment(department);
    }

    @Test
    public void testListAllBooks() {
        when(libraryService.listAllBooks()).thenReturn(List.of(book));
        List<Book> books = libraryController.listAllBooks();
        assertThat(books).hasSize(1);
        assertThat(books.get(0).getTitle()).isEqualTo("Book Title");
    }

    @Test
    public void testFindBookByTitle() {
        when(libraryService.findBookByTitle("Book Title")).thenReturn(List.of(book));
        List<Book> books = libraryController.findBookByTitle("Book Title");
        assertThat(books).hasSize(1);
        assertThat(books.get(0).getTitle()).isEqualTo("Book Title");
    }

    @Test
    public void testFindBookByAuthor() {
        when(libraryService.findBookByAuthor("Author Name")).thenReturn(List.of(book));
        List<Book> books = libraryController.findBookByAuthor("Author Name");
        assertThat(books).hasSize(1);
        assertThat(books.get(0).getAuthor()).isEqualTo("Author Name");
    }

    @Test
    public void testListAvailableBooks() {
        when(libraryService.listAvailableBooks()).thenReturn(List.of(book));
        List<Book> books = libraryController.listAvailableBooks();
        assertThat(books).hasSize(1);
        assertThat(books.get(0).isAvailability()).isTrue();
    }

    @Test
    public void testAddBook() {
        when(libraryService.addBook("Book Title", "Author Name", "1234567890", "Fiction", 2021, true, 1L)).thenReturn(book);
        Book addedBook = libraryController.addBook("Book Title", "Author Name", "1234567890", "Fiction", 2021, true, 1L);
        assertThat(addedBook).isNotNull();
        assertThat(addedBook.getTitle()).isEqualTo("Book Title");
    }

    @Test
    public void testRemoveBook() {
        libraryController.removeBook("1234567890");
    }
}
