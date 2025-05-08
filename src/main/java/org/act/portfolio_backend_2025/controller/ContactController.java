package org.act.portfolio_backend_2025.controller;

import org.act.portfolio_backend_2025.dto.ContactFormDTO;
import org.act.portfolio_backend_2025.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/contact")
//@CrossOrigin(origins = "*") // Allow CORS for React frontend
public class ContactController {

    @Autowired
    private EmailService emailService;

    @PostMapping
    public ResponseEntity<String> submitContactForm(@RequestBody ContactFormDTO contactForm) {
        try {
            emailService.sendContactEmail(contactForm);
            return ResponseEntity.ok("Message sent successfully!");
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Failed to send message: " + e.getMessage());
        }
    }
}