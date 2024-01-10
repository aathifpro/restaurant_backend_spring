package com.springjwt.controllers.main;


import com.springjwt.common.CommonResponse;
import com.springjwt.common.StatusCode;
import com.springjwt.dto.main.ReservationDTO;
import com.springjwt.dto.main.responseDto.ReservationResponseDTO;
import com.springjwt.entity.main.Reservation;
import com.springjwt.services.main.ReservationService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/api")
@Slf4j
public class ReservationController {

    @Autowired
    ReservationService reservationService;


    /**
     * Create reservation request
     *
     * @param reservationDTO - required dto to create an reservation
     * @return success or failed response from reservation creation and reservation details
     * @author aathif
     */
    @PostMapping("/reservation")
    public ResponseEntity<CommonResponse> createReservation(@RequestBody ReservationDTO reservationDTO) {
        CommonResponse commonResponse = new CommonResponse();
        ReservationResponseDTO responseDTO = reservationService.createReservation(reservationDTO);
        if (responseDTO.getStatus() == 200 || responseDTO.getStatus() == 201) {
            commonResponse.setStatus(HttpStatus.OK);
            commonResponse.setData(responseDTO.getData());
            commonResponse.setMessage(responseDTO.getMessage());
            commonResponse.setTimestamp(responseDTO.getTimestamp());
            return new ResponseEntity<>(commonResponse, HttpStatus.OK);
        } else {
            commonResponse.setStatus(HttpStatus.INTERNAL_SERVER_ERROR);
            commonResponse.setData(responseDTO.getData());
            commonResponse.setMessage(responseDTO.getMessage());
            commonResponse.setTimestamp(responseDTO.getTimestamp());
            return new ResponseEntity<>(commonResponse, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * Get reservations request
     *
     * @return success or failed response from reservation and all reservations details
     * @author aathif
     */
    @GetMapping("/reservations")
    public ResponseEntity<List<Reservation>> getReservations() {
        return reservationService.getReservations();
    }

    /**
     * Get reservation request
     *
     * @param id - required variable to get reservation
     * @return success or failed response from reservation and reservation details
     * @author aathif
     */
    @GetMapping("/reservation/{id}")
    public ResponseEntity<Reservation> getReservation(@PathVariable("id") long id){
        return reservationService.getReservation(id);
    }


    /**
     * Update reservation request
     *
     * @param id - required variable to update an reservation
     * @param reservationDTO - required dto to update an reservation
     * @return success or failed response from reservation update and return updated reservation details
     * @author aathif
     */
    @PutMapping("/reservation/{id}")
    public ResponseEntity<ReservationResponseDTO> updateReservation(@PathVariable("id") long id, @RequestBody ReservationDTO reservationDTO) {
        ReservationResponseDTO reservationResponse = reservationService.updateReservation(id, reservationDTO);
        if (reservationResponse != null) {
            return new ResponseEntity<>(reservationResponse, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * Delete reservation request
     *
     * @param id - required variable to delete reservation
     * @return success or failed response from reservation
     * @author aathif
     */
    @DeleteMapping("/reservation/{id}")
    public ResponseEntity<ReservationResponseDTO> deleteReservation(@PathVariable("id") long id) {
        ReservationResponseDTO reservationResponse = reservationService.deleteReservation(id);
        if(reservationResponse.getStatus() == StatusCode.OK) {
            return new ResponseEntity<>(reservationResponse, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
