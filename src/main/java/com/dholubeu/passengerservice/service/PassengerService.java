package com.dholubeu.passengerservice.service;

import com.dholubeu.passengerservice.domain.Passenger;
import com.dholubeu.passengerservice.domain.Password;

import java.math.BigDecimal;

public interface PassengerService {

    Passenger create(Passenger passenger);

    Passenger findById(Long id);

    Passenger findByEmail(String email);

    Passenger update(Passenger passenger);

    Passenger updateRating(Long id, BigDecimal rating);

    Passenger updatePassword(Long id, Password password);

    Passenger resetPassword(Long id, String newPassword);

}
