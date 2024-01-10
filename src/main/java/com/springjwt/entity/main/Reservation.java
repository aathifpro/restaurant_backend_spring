package com.springjwt.entity.main;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity
@Table(name = "reservation")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Reservation {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private long id;
    @Column(name = "name")
    private String name;
    @Column(name = "email")
    private String email;
    @Column(name = "number")
    private String number;
    @Column(name = "date")
    private String date;
    @Column(name = "time")
    private String time;
    @Column(name = "person_count")
    private int personCount;

    public Reservation(String name, String email, String number, String date, String time, int personCount) {
        this.name = name;
        this.email = email;
        this.number = number;
        this.date = date;
        this.time = time;
        this.personCount = personCount;
    }
}
