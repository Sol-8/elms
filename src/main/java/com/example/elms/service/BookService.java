package com.example.elms.service;

import com.example.elms.model.Book;
import com.example.elms.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookService {

    @Autowired
    private BookRepository bookRepository;

    public List<Book> findAll() {
        return bookRepository.findAll();
    }

    public List<Book> searchBooks(String query) {
        return bookRepository.findByTitleContainingIgnoreCaseOrAuthorContainingIgnoreCaseOrGenreContainingIgnoreCaseOrBookIdContainingIgnoreCase(query, query, query, query);
    }

    public List<Book> findByAuthor(String author) {
        return bookRepository.findByAuthor(author);
    }

    public List<Book> findByTitle(String title) {
        return bookRepository.findByTitle(title);
    }

    public Optional<Book> findBybookId(String bookId) {
        return bookRepository.findBybookId(bookId);
    }

    public Book save(Book book) {
        return bookRepository.save(book);
    }

    public Book updateBook(String bookId, Book bookDetails) {
        Optional<Book> optionalBook = bookRepository.findBybookId(bookId);
        if (optionalBook.isPresent()) {
            Book book = optionalBook.get();
            book.setTitle(bookDetails.getTitle());
            book.setAuthor(bookDetails.getAuthor());
            book.setGenre(bookDetails.getGenre());
            book.setPublicationDate(bookDetails.getPublicationDate());
            // Add other fields as necessary
            return bookRepository.save(book);
        } else {
            return null; // Return null if book is not found
        }
    }

    public void deleteBook(String bookId) {
        bookRepository.deleteBybookId(bookId);
    }

    // Method for adding a new book
    public Book addBook(Book newBook) {
        return bookRepository.save(newBook);
    }

    // Method for editing an existing book
    public Book editBook(String bookId, Book bookDetails) {
        Optional<Book> optionalBook = bookRepository.findBybookId(bookId);
        if (optionalBook.isPresent()) {
            Book book = optionalBook.get();
            book.setTitle(bookDetails.getTitle());
            book.setAuthor(bookDetails.getAuthor());
            book.setGenre(bookDetails.getGenre());
            book.setPublicationDate(bookDetails.getPublicationDate());
            // Add other fields as necessary
            return bookRepository.save(book);
        } else {
            return null; // Return null if book is not found
        }
    }
}
