package com.dholubeu.passengerservice.service.impl;


import com.dholubeu.passengerservice.domain.Passenger;
import com.dholubeu.passengerservice.domain.exception.ResourceAlreadyExistsException;
import com.dholubeu.passengerservice.domain.exception.ResourceDoesNotExistException;
import com.dholubeu.passengerservice.repository.PassengerRepository;
import com.dholubeu.passengerservice.service.PassengerService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
@RequiredArgsConstructor
public class PassengerServiceImpl implements PassengerService {

    private final PassengerRepository passengerRepository;

    @Override
    public Passenger create(Passenger passenger) {
        if (passengerRepository.existsByEmail(passenger.getEmail())) {
            throw new ResourceAlreadyExistsException("Passenger with email " +
                    passenger.getEmail() + " already exists");
        }
        return passengerRepository.save(passenger);
    }

    @Override
    public Passenger findById(Long id) {
        return passengerRepository.findById(id).orElseThrow(
                () -> new ResourceDoesNotExistException(
                        "Passenger with id " + id + " does not exist"));
    }

    @Override
    public Passenger findByEmail(String email) {
        return passengerRepository.findByEmail(email).orElseThrow(
                () -> new ResourceDoesNotExistException(
                        "Passenger with email " + email + " does not exist"));
    }

    @Override
    public Passenger update(Passenger passenger) {
        return passengerRepository.save(passenger);
    }

    @Override
    public Passenger updateRating(Long id, BigDecimal rating) {
        Passenger passenger = findById(id);
        passenger.setRating(rating);
        return passengerRepository.save(passenger);
    }

}