package com.springjwt.controllers.main;


import com.springjwt.dto.main.ContactDTO;
import com.springjwt.services.main.ContactService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping("/api")
@Slf4j
public class ContactController {

    @Autowired
    ContactService contactService;

    @PostMapping("/sendmail")
    public ResponseEntity<String> sendEmail(@RequestBody ContactDTO contactDTO) {
        contactService.sendEmailWithAttachment(contactDTO);
        return new ResponseEntity<>(HttpStatus.OK);
    }



}
