package com.springjwt.dto.main.responseDto;

import com.springjwt.entity.main.Reservation;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReservationResponseDTO {

    private Integer status;
    private Reservation data;
    private String message;
    private Date timestamp;

}
