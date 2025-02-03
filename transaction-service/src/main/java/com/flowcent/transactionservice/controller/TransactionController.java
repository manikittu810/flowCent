package com.flowcent.transactionservice.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/transaction")
public class TransactionController {
    @PostMapping
    public ResponseEntity<String> createTransaction() {
        return ResponseEntity.ok("Transaction Happened !");
    }

    @GetMapping
    public ResponseEntity<String> getAllTransactions() {
        return ResponseEntity.ok("List of transactions");
    }
}
