package com.springjwt.dto.main;

import lombok.Data;

@Data
public class ContactDTO {

    private long id;
    private String to;
    private String subject;
    private String text;
    private String filePath;
}
