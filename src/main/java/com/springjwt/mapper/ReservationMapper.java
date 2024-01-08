package com.springjwt.mapper;

import com.springjwt.dto.main.ReservationDTO;
import com.springjwt.entity.main.Reservation;

import java.util.Optional;

public class ReservationMapper {

    private static ReservationMapper instance = null;

    private ReservationMapper() {}

    public static ReservationMapper getInstance() {
        if(instance == null) {
            instance = new ReservationMapper();
        }
        return instance;
    }

    public Reservation getReservationMapper(ReservationDTO reservationDTO, Optional<Reservation> reservationDetails) {
        Reservation reservation = new Reservation();
        if (reservationDetails.isPresent()) {
            reservation = reservationDetails.get();
            reservation.setName(reservationDTO.getName());
            reservation.setEmail(reservationDTO.getEmail());
            reservation.setNumber(reservationDTO.getNumber());
            reservation.setDate(reservationDTO.getDate());
            reservation.setTime(reservationDTO.getTime());
            reservation.setPersonCount(reservationDTO.getPersonCount());
        }
        return reservation;
    }

}
