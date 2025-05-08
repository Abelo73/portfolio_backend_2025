package org.act.portfolio_backend_2025.service;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.act.portfolio_backend_2025.dto.ContactFormDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;



@Service
public class EmailService {

    @Autowired
    private JavaMailSender mailSender;

    public void sendContactEmail(ContactFormDTO contactForm) throws MessagingException {
        MimeMessage mimeMessage = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);

        helper.setTo("your-receiving-email@example.com"); // Replace with your email
        helper.setSubject("New Contact Form Submission");
        helper.setFrom(contactForm.getEmail());

        String emailContent = "<h3>New Message from " + contactForm.getName() + "</h3>" +
                "<p><strong>Email:</strong> " + contactForm.getEmail() + "</p>" +
                "<p><strong>Message:</strong> " + contactForm.getMessage() + "</p>";
        helper.setText(emailContent, true);

        mailSender.send(mimeMessage);
    }
}