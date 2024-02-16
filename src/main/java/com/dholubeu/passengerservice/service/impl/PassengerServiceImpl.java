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

    public static final String RESOURCE_ALREADY_EXISTS_MESSAGE = "Passenger with email %s already exists";
    public static final String RESOURCE_DOES_NOT_EXIST_BY_ID_MESSAGE = "Passenger with id %d does not exist";
    public static final String RESOURCE_DOES_NOT_EXIST_BY_EMAIL_MESSAGE = "Passenger with email %s does not exist";
    private final PassengerRepository passengerRepository;

    @Override
    public Passenger create(Passenger passenger) {
        if (passengerRepository.existsByEmail(passenger.getEmail())) {
            throw new ResourceAlreadyExistsException(String.format(
                    RESOURCE_ALREADY_EXISTS_MESSAGE, passenger.getEmail()));
        }
        passenger.setRating(BigDecimal.valueOf(0.0));
        return passengerRepository.save(passenger);
    }

    @Override
    public Passenger findById(Long id) {
        return passengerRepository.findById(id).orElseThrow(
                () -> new ResourceDoesNotExistException(String.format(
                        RESOURCE_DOES_NOT_EXIST_BY_ID_MESSAGE, id)));
    }

    @Override
    public Passenger findByEmail(String email) {
        return passengerRepository.findByEmail(email).orElseThrow(
                () -> new ResourceDoesNotExistException(String.format(
                        RESOURCE_DOES_NOT_EXIST_BY_EMAIL_MESSAGE, email)));
    }

    @Override
    public Passenger update(Long id, Passenger passenger) {
        Passenger passengerUpdated = findById(id);
        passengerUpdated = Passenger.builder()
                .id(passenger.getId())
                .name(passenger.getName())
                .email(passengerUpdated.getEmail())
                .surname(passenger.getSurname())
                .dateOfBirth(passenger.getDateOfBirth())
                .phoneNumber(passenger.getPhoneNumber())
                .rating(passengerUpdated.getRating())
                .build();
        return passengerRepository.save(passengerUpdated);
    }

    @Override
    public Passenger updateRating(Long id, BigDecimal rating) {
        Passenger passenger = findById(id);
        passenger.setRating(rating);
        return passengerRepository.save(passenger);
    }

}