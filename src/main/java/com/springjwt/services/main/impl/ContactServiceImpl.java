package com.springjwt.services.main.impl;

import com.springjwt.dto.main.ContactDTO;
import com.springjwt.services.main.ContactService;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import java.io.File;

@Service
@Slf4j
public class ContactServiceImpl implements ContactService {

    @Autowired
    private JavaMailSender javaMailSender;

    @Override
    public void sendEmailWithAttachment(ContactDTO contactDTO) {
        log.info("sendEmailWithAttachment -> start");
        try {
            MimeMessage mimeMessage = javaMailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);

            helper.setTo(contactDTO.getTo());
            helper.setSubject((contactDTO.getSubject()));
            helper.setText(contactDTO.getText());

            FileSystemResource file = new FileSystemResource(new File(contactDTO.getFilePath()));
            helper.addAttachment("attachment.jpg", file);

            javaMailSender.send(mimeMessage);
        } catch (MessagingException e) {
            log.error("Error: {} and {}", e.getMessage(), e.getCause());
        }
    }
}
