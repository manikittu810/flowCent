package com.flowcent.accountservice.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/accounts")
public class AccountsController {
        @PostMapping
        public ResponseEntity<String> createAccount() {
            return ResponseEntity.ok("Account created!");
        }

        @GetMapping
        public ResponseEntity<String> getAllAccounts() {
            return ResponseEntity.ok("List of accounts");
        }
    }
