package com.flowcent.accountservice.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/accounts")
public class AccountsController {
    @PostMapping
    public ResponseEntity<String> createAccount() {
        return ResponseEntity.ok("Account created!");
    }

    @GetMapping("/{id}")  // Corrected: Parameterized the ID in the path
    public ResponseEntity<String> getAllAccounts(@PathVariable int id) {
        if (id == 1) {
            return ResponseEntity.ok("List of accounts");
        } else {
            return ResponseEntity.ok("List of no accounts");
        }
    }
    }
