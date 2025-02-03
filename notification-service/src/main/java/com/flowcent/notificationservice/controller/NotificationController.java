package com.flowcent.notificationservice.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/notification")
public class NotificationController {
    @PostMapping
    public ResponseEntity<String> createNotification() {
        return ResponseEntity.ok("Nitification created!");
    }

    @GetMapping
    public ResponseEntity<String> getAllNotifications() {
        return ResponseEntity.ok("List of Notifications");
    }
}
