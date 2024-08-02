package com.example.elms.model;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.sql.Date;

@Entity
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String   bookId;
    private String title;
    private String author;
    private String genre;
    private Date publicationDate;




    // Default constructor
    public Book() {
        this.publicationDate = publicationDate;
    }

    // Constructor with parameters
    public Book(String title, String author, String genre, String bookId) {
       this.bookId=bookId;
       this.title = title;
       this.author = author;
       this.genre = genre;
    }

    // Getters and setters
    public String getbookId() {
        return bookId;
    }

    public void setbookId(String bookId) {
        this.bookId = bookId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }
    public Date getPublicationDate() {
        return publicationDate;
    }
    public void setPublicationDate(Date publicationDate) {
        this.publicationDate = publicationDate;
    }

    // Property methods for JavaFX bindings
    public StringProperty bookIdProperty() {
        return new SimpleStringProperty(bookId);
    }
    public StringProperty titleProperty() {
        return new SimpleStringProperty(title);
    }

    public StringProperty authorProperty() {
        return new SimpleStringProperty(author);
    }

    public StringProperty genreProperty() {
        return new SimpleStringProperty(genre);
    }
}
