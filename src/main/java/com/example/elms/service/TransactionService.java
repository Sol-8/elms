package com.example.elms.service;

import com.example.elms.model.Transaction;
import com.example.elms.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.stream.Collectors;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class TransactionService {
    @Autowired
    private TransactionRepository transactionRepository;

    public List findByStudentId(String studentId) {
        return StreamSupport.stream(transactionRepository.findByStudentId(studentId).spliterator(), false).collect(Collectors.toList());
    }

    public List findAll() {
        return StreamSupport.stream(transactionRepository.findAll().spliterator(), false).collect(Collectors.toList());
    }

    public Transaction findById(Long id) {
        return transactionRepository.findById(id.intValue()).orElse(null);
    }

    public Transaction save(Transaction transaction) {
        return transactionRepository.save(transaction);
    }

    public void delete(Long id) {
        transactionRepository.deleteById(id.intValue());
    }
}