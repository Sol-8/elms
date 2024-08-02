package com.example.elms.repository;

import com.example.elms.model.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Integer> {

    List<Transaction> findByStudentId(String studentId);

    @Query("SELECT t FROM Transaction t WHERE t.dueDate < CURRENT_DATE AND t.returnDate IS NULL")
    List<Transaction> findOverdueTransactions();

    @Query("SELECT t.bookId, COUNT(t) as borrowCount FROM Transaction t GROUP BY t.bookId ORDER BY borrowCount DESC")
    List<Object[]> findBookBorrowingFrequency();
}
