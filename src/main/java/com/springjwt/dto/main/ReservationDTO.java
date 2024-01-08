package com.springjwt.dto.main;


import lombok.Data;

import java.util.Date;

@Data
public class ReservationDTO {

    private long id;
    private String name;
    private String email;
    private String number;
    private String date;
    private String time;
    private int personCount;
}
