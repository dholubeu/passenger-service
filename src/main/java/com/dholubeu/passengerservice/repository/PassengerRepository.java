package com.dholubeu.passengerservice.repository;

import com.dholubeu.passengerservice.domain.Passenger;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PassengerRepository extends JpaRepository<Passenger, Long> {

    boolean existsByEmail(String email);

    Optional<Passenger> findByEmail(String email);

}
