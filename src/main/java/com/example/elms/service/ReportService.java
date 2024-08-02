package com.example.elms.service;

import com.example.elms.model.Book;
import com.example.elms.model.Transaction;
import com.example.elms.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class ReportService {

    @Autowired
    private TransactionRepository transactionRepository;

    /**
     * Get a list of overdue transactions.
     *
     * @return List of Transactions
     */
    public List<Transaction> getOverdueBooks() {
        return transactionRepository.findOverdueTransactions();
    }

    /**
     * Get the borrowing frequency of each book.
     *
     * @return Map of book titles and their borrowing frequency
     */
    public Map<String, Long> getBookBorrowingFrequency() {
        List<Object[]> results = transactionRepository.findBookBorrowingFrequency();
        return results.stream()
                .collect(Collectors.toMap(
                        result -> ((Book) result[0]).getTitle(), // Adjusted to use Books
                        result -> (Long) result[1]
                ));
    }
}
