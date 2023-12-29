package sk.matusturjak.price_sender.service;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import sk.matusturjak.price_sender.config.MailProperties;
import sk.matusturjak.price_sender.model.mail.MailDetails;
import sk.matusturjak.price_sender.repository.ItemRepository;
import sk.matusturjak.price_sender.repository.UserRepository;

@Slf4j
@AllArgsConstructor
@Service
public class MailService {

    private final MailProperties mailProperties;
    private final JavaMailSender javaMailSender;

    private void sendEmail(MailDetails details) {
        SimpleMailMessage mailMessage = new SimpleMailMessage();

        // Setting up necessary details
        mailMessage.setFrom(mailProperties.getUsername());
        mailMessage.setTo(details.getRecipient());
        mailMessage.setText(details.getMsgBody());
        mailMessage.setSubject(details.getSubject());

        // Sending the mail
        javaMailSender.send(mailMessage);
    }

    public void sendEmails() {

    }
}
