package br.com.brendaStefany.aluraTech.service;

import org.springframework.stereotype.Service;

@Service
public class EmailService {

    public static void send(String recipientEmail, String subject, String body) {
        System.out.println("\nSending email to: %s".formatted(recipientEmail));
        System.out.println("Subject: %s".formatted(subject));
        System.out.println("Body: %s \n".formatted(body));
    }

}
