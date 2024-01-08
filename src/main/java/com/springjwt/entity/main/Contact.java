package com.springjwt.entity.main;


import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "Contact_table")
@Data
public class Contact {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "email_id")
    private long id;

    @Column(name = "email_to")
    private String to;

    @Column(name = "email_subject")
    private String subject;

    @Column(name = "email_text")
    private String text;

    @Column(name = "email_file")
    private String filePath;
}
