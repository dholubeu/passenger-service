package com.dholubeu.passengerservice.service;

import com.dholubeu.passengerservice.domain.Passenger;
import com.dholubeu.passengerservice.web.dto.PassengerDto;

import java.math.BigDecimal;

public interface PassengerService {

    PassengerDto create(PassengerDto passengerDto);

    PassengerDto findById(Long id);

    PassengerDto findByEmail(String email);

    PassengerDto update(Long id, PassengerDto passengerDto);


    PassengerDto updateRating(Long id, BigDecimal rating);

}
