package com.springjwt.services.main.impl;

import com.springjwt.common.StatusCode;
import com.springjwt.dto.main.ReservationDTO;
import com.springjwt.dto.main.responseDto.ProductResponseDTO;
import com.springjwt.dto.main.responseDto.ReservationResponseDTO;
import com.springjwt.entity.main.Product;
import com.springjwt.entity.main.Reservation;
import com.springjwt.mapper.ProductMapper;
import com.springjwt.mapper.ReservationMapper;
import com.springjwt.repository.main.ReservationRepository;
import com.springjwt.services.main.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class ReservationServiceImpl implements ReservationService {

    @Autowired
    ReservationRepository reservationRepository;


    /**
     * Create reservation request
     *
     * @param reservationDTO - required dto to create an reservation
     * @return success or failed response from reservation creation and reservation details
     * @author aathif
     */
    @Override
    public ReservationResponseDTO createReservation(ReservationDTO reservationDTO) {
        try {
            Reservation reservation = new Reservation(
                    reservationDTO.getName(),
                    reservationDTO.getEmail(),
                    reservationDTO.getNumber(),
                    reservationDTO.getDate(),
                    reservationDTO.getTime(),
                    reservationDTO.getPersonCount()
            );
            reservationRepository.save(reservation);
            return new ReservationResponseDTO(StatusCode.CREATED, reservation, "Reservation creation success!", new Date());
        } catch (Exception e) {
            return new ReservationResponseDTO(StatusCode.INTERNAL_SERVER_ERROR, null, "Reservation creation failed", new Date());
        }
    }

    /**
     * Get reservations request
     *
     * @return success or failed response from reservation and all reservations details
     * @author aathif
     */
    @Override
    public ResponseEntity<List<Reservation>> getReservations() {
        try {
            List<Reservation> reservationList = new ArrayList<>(reservationRepository.findAll());

            if (reservationList.isEmpty())
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            return new ResponseEntity<>(reservationList, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    /**
     * Get reservation request
     *
     * @param id - required variable to get reservation
     * @return success or failed response from reservation and reservation details
     * @author aathif
     */
    @Override
    public ResponseEntity<Reservation> getReservation(long id) {
        try {
            Optional<Reservation> reservationData = reservationRepository.findById(id);
            return reservationData.map(reservation -> new ResponseEntity<>(reservation, HttpStatus.OK)).orElseGet(() -> new ResponseEntity<>(HttpStatus.NO_CONTENT));
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * Update reservation request
     *
     * @param id - required variable to update an reservation
     * @param reservationDTO - required dto to update an reservation
     * @return success or failed response from reservation update and return updated reservation details
     * @author aathif
     */
    @Override
    public ReservationResponseDTO updateReservation(long id, ReservationDTO reservationDTO) {
        try {
            Optional<Reservation> reservationDetails = reservationRepository.findById(id);
            if (reservationDetails.isPresent()) {
                Reservation reservation = ReservationMapper.getInstance().getReservationMapper(reservationDTO, reservationDetails);
                return new ReservationResponseDTO(StatusCode.OK, reservationRepository.save(reservation), "Reservation Update success!", new Date());
            }
            return new ReservationResponseDTO(StatusCode.NO_CONTENT, null, "Reservation Update Failed!", new Date());
        } catch (Exception e) {
            return new ReservationResponseDTO(StatusCode.INTERNAL_SERVER_ERROR, null, e.getMessage(), new Date());
        }
    }

    /**
     * Delete reservation request
     *
     * @param id - required variable to delete reservation
     * @return success or failed response from reservation
     * @author aathif
     */
    @Override
    public ReservationResponseDTO deleteReservation(long id) {
        try {
            reservationRepository.deleteById(id);
            return new ReservationResponseDTO(StatusCode.OK, null, "Reservation is Deleted!", new Date());
        } catch (Exception e) {
            return new ReservationResponseDTO(StatusCode.INTERNAL_SERVER_ERROR, null, "Exception occurred while deleting the reservation", new Date());
        }
    }


}
