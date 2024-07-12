package com.bansira.assignment.service;

import com.bansira.assignment.model.Book;
import com.bansira.assignment.model.Department;
import com.bansira.assignment.repository.BookRepository;
import com.bansira.assignment.repository.DepartmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class LibraryService {

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private DepartmentRepository departmentRepository;

    public List<Book> findBookByTitle(String title) {
        return bookRepository.findByTitleIgnoreCase(title);
    }

    public List<Book> findBookByAuthor(String author) {
        return bookRepository.findByAuthorIgnoreCase(author);
    }

    public List<Book> listAllBooks() {
        return bookRepository.findAll();
    }

//    private List<BookDto> getDtoListFromEntityList(List<Book> books) {
//        List<BookDto> bookDtos = new ArrayList<>();
//        books.forEach(book -> {
//            BookDto bookDto = new BookDto();
//            bookDto.setTitle(book.getTitle());
//            bookDto.setAuthor(bookDto.getAuthor());
//            bookDto.setISBN(bookDto.getISBN());
//            bookDto.setGenre(bookDto.getGenre());
//            bookDto.setAvailability(book.isAvailability());
//            bookDto.setPublicationYear(book.getPublicationYear());
//
//            DepartmentDto departmentDto = new DepartmentDto();
//            departmentDto.setName(book.getDepartment().getName());
//            departmentDto.setBooks(book.getDepartment().getBooks());
//
//            bookDto.setDepartment(departmentDto);
//        });
//        return bookDtos;
//    }

    public List<Book> listAvailableBooks() {
        return bookRepository.findByAvailability(true);
    }

    public Department findDepartmentById(Long id) {
        return departmentRepository.findById(id).orElse(null);
    }

    public Book addBook(String title, String author, String ISBN, String genre, int publicationYear, boolean availability, Long departmentId) {
        if (!bookRepository.existsByISBN(ISBN)) {
            Department department = departmentRepository.findById(departmentId).orElse(null);
            if (department == null) {
                throw new IllegalArgumentException("Department not found");
            }

            Book book = new Book();
            book.setTitle(title);
            book.setAuthor(author);
            book.setISBN(ISBN);
            book.setGenre(genre);
            book.setPublicationYear(publicationYear);
            book.setAvailability(availability);
            book.setDepartment(department);
            return bookRepository.save(book);
        }
        return null;
    }

    public void removeBook(String ISBN) {
        Book book = bookRepository.findByISBN(ISBN);
        if (book != null) {
            bookRepository.delete(book);
        }
    }
}
