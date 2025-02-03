package com.flowcent.commonservice.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/common")
public class CommonController {
    @PostMapping
    public ResponseEntity<String> createCommonService() {
        return ResponseEntity.ok("Common Accounts created!");
    }

    @GetMapping
    public ResponseEntity<String> getAllCommonAccounts() {
        return ResponseEntity.ok("List of common accounts");
    }
}
