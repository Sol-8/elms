package com.example.elms.model;

import javafx.beans.property.*;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.time.LocalDate;

@Entity
public class Transaction {

    private final ObjectProperty<Long> id = new SimpleObjectProperty<>();
    private final ObjectProperty<LocalDate> borrowDate = new SimpleObjectProperty<>();
    private final ObjectProperty<LocalDate> returnDate = new SimpleObjectProperty<>();
    private final ObjectProperty<LocalDate> dueDate = new SimpleObjectProperty<>(); // New property
    private final StringProperty studentId = new SimpleStringProperty();
    private final StringProperty bookId = new SimpleStringProperty();

    @Id
    public Long getId() {
        return id.get();
    }

    public void setId(Long id) {
        this.id.set(id);
    }

    public ObjectProperty<Long> idProperty() {
        return id;
    }

    public LocalDate getBorrowDate() {
        return borrowDate.get();
    }

    public void setBorrowDate(LocalDate borrowDate) {
        this.borrowDate.set(borrowDate);
    }

    public ObjectProperty<LocalDate> borrowDateProperty() {
        return borrowDate;
    }

    public LocalDate getReturnDate() {
        return returnDate.get();
    }

    public void setReturnDate(LocalDate returnDate) {
        this.returnDate.set(returnDate);
    }

    public ObjectProperty<LocalDate> returnDateProperty() {
        return returnDate;
    }

    public LocalDate getDueDate() {
        return dueDate.get();
    }

    public void setDueDate(LocalDate dueDate) {
        this.dueDate.set(dueDate);
    }

    public ObjectProperty<LocalDate> dueDateProperty() {
        return dueDate;
    }

    public String getStudentId() {
        return studentId.get();
    }

    public void setStudentId(String studentId) {
        this.studentId.set(studentId);
    }

    public StringProperty studentIdProperty() {
        return studentId;
    }

    public String getBookId() {
        return bookId.get();
    }

    public void setBookId(String bookId) {
        this.bookId.set(bookId);
    }

    public StringProperty bookTitleProperty() {
        String bookTitle = "";
        return new SimpleStringProperty(bookTitle);
    }

    public SimpleStringProperty studentNameProperty() {
        String studentName = "";
        return new SimpleStringProperty(studentName);
    }
}
