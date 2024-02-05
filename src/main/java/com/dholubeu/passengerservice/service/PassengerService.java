package com.dholubeu.passengerservice.service;

import com.dholubeu.passengerservice.domain.Passenger;

import java.math.BigDecimal;

public interface PassengerService {

    Passenger create(Passenger passenger);

    Passenger findById(Long id);

    Passenger findByEmail(String email);

    Passenger update(Passenger passenger);

    Passenger updateRating(Long id, BigDecimal rating);

}
