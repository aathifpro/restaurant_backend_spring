package com.springjwt.services.main;

import com.springjwt.dto.main.ContactDTO;

public interface ContactService {
    void sendEmailWithAttachment(ContactDTO contactDTO);
}
