package com.springjwt.repository.main;

import com.springjwt.entity.main.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

@Repository
@EnableJpaRepositories
public interface ReservationRepository extends JpaRepository<Reservation, Long> {
}
