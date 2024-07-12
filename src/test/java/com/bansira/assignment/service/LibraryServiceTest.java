package com.bansira.assignment.service;

import com.bansira.assignment.model.Book;
import com.bansira.assignment.model.Department;
import com.bansira.assignment.repository.BookRepository;
import com.bansira.assignment.repository.DepartmentRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

public class LibraryServiceTest {
    @Mock
    private BookRepository bookRepository;

    @Mock
    private DepartmentRepository departmentRepository;

    @InjectMocks
    private LibraryService libraryService;

    private Department department;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);

        department = new Department();
        department.setId(1L);
        department.setName("Fiction");
    }

    @Test
    public void testAddBook() {
        when(departmentRepository.findById(1L)).thenReturn(Optional.of(department));
        when(bookRepository.save(any(Book.class))).thenAnswer(invocation -> invocation.getArgument(0));

        Book book = libraryService.addBook("Book Title", "Author Name", "1234567890", "Fiction", 2021, true, 1L);

        assertThat(book).isNotNull();
        assertThat(book.getTitle()).isEqualTo("Book Title");
        assertThat(book.getDepartment().getName()).isEqualTo("Fiction");
    }

    @Test
    public void testFindBookByTitle() {
        when(bookRepository.findByTitleIgnoreCase("Book Title")).thenReturn(List.of(new Book()));
        List<Book> books = libraryService.findBookByTitle("Book Title");
        assertThat(books).hasSize(1);
    }

    @Test
    public void testFindBookByAuthor() {
        when(bookRepository.findByAuthorIgnoreCase("Author Name")).thenReturn(List.of(new Book()));
        List<Book> books = libraryService.findBookByAuthor("Author Name");
        assertThat(books).hasSize(1);
    }

    @Test
    public void testListAvailableBooks() {
        when(bookRepository.findByAvailability(true)).thenReturn(List.of(new Book()));
        List<Book> books = libraryService.listAvailableBooks();
        assertThat(books).hasSize(1);
    }

    @Test
    public void testRemoveBook() {
        libraryService.removeBook("1234567890");
    }
}