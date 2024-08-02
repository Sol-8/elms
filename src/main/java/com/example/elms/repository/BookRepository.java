package com.example.elms.repository;

import com.example.elms.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BookRepository extends JpaRepository<Book, String> {
    List<Book> findByTitleContainingIgnoreCaseOrAuthorContainingIgnoreCaseOrGenreContainingIgnoreCaseOrBookIdContainingIgnoreCase(
            String title, String author, String genre, String bookId);

    List<Book> findByAuthor(String author);
    List<Book> findByTitle(String title);
    Optional<Book> findBybookId(String bookId); // Changed to return Optional<Book>
    void deleteBybookId(String bookId);
}
