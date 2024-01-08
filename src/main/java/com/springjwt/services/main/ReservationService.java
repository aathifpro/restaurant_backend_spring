package com.springjwt.services.main;

import com.springjwt.dto.main.ReservationDTO;
import com.springjwt.dto.main.responseDto.ReservationResponseDTO;
import com.springjwt.entity.main.Product;
import com.springjwt.entity.main.Reservation;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface ReservationService {
    ReservationResponseDTO createReservation(ReservationDTO reservationDTO);

    ResponseEntity<List<Reservation>> getReservations();

    ResponseEntity<Reservation> getReservation(long id);

    ReservationResponseDTO updateReservation(long id, ReservationDTO reservationDTO);

    ReservationResponseDTO deleteReservation(long id);
}
