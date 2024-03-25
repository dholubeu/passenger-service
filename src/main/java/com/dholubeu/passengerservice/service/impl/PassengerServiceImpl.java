package com.dholubeu.passengerservice.service.impl;

import com.dholubeu.passengerservice.domain.Passenger;
import com.dholubeu.passengerservice.domain.exception.ResourceAlreadyExistsException;
import com.dholubeu.passengerservice.domain.exception.ResourceDoesNotExistException;
import com.dholubeu.passengerservice.repository.PassengerRepository;
import com.dholubeu.passengerservice.service.PassengerService;
import com.dholubeu.passengerservice.web.dto.PassengerDto;
import com.dholubeu.passengerservice.web.mapper.PassengerMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;


import static com.dholubeu.passengerservice.util.Constants.RESOURCE_ALREADY_EXISTS_MESSAGE;
import static com.dholubeu.passengerservice.util.Constants.RESOURCE_DOES_NOT_EXIST_BY_ID_MESSAGE;
import static com.dholubeu.passengerservice.util.Constants.RESOURCE_DOES_NOT_EXIST_BY_EMAIL_MESSAGE;

@Service
@RequiredArgsConstructor
public class PassengerServiceImpl implements PassengerService {

    private final PassengerRepository passengerRepository;
    private final PassengerMapper passengerMapper;

    @Override
    public PassengerDto create(PassengerDto passengerDto) {

        if (passengerRepository.existsByEmail(passengerDto.getEmail())) {
            throw new ResourceAlreadyExistsException(String.format(
                    RESOURCE_ALREADY_EXISTS_MESSAGE, passengerDto.getEmail()));
        }
        Passenger passenger = passengerMapper.toEntity(passengerDto);
        passenger.setRating(BigDecimal.valueOf(0.0));
        return passengerMapper.toDto(passenger);
    }

    @Override
    public PassengerDto findById(Long id) {
        var passenger = passengerRepository.findById(id).orElseThrow(
                () -> new ResourceDoesNotExistException(String.format(
                        RESOURCE_DOES_NOT_EXIST_BY_ID_MESSAGE, id)));
        return passengerMapper.toDto(passenger);
    }

    @Override
    public PassengerDto findByEmail(String email) {
        var passenger = passengerRepository.findByEmail(email).orElseThrow(
                () -> new ResourceDoesNotExistException(String.format(
                        RESOURCE_DOES_NOT_EXIST_BY_EMAIL_MESSAGE, email)));
        return passengerMapper.toDto(passenger);
    }

    @Override
    public PassengerDto update(Long id, PassengerDto passengerDto) {
        Passenger passenger = passengerMapper.toEntity(passengerDto);
        Passenger passengerUpdated = passengerRepository.findById(id)
                .orElseThrow(() -> new ResourceDoesNotExistException(""));
        ;

        passengerUpdated = Passenger.builder()
                .id(passenger.getId())
                .name(passenger.getName())
                .email(passengerUpdated.getEmail())
                .surname(passenger.getSurname())
                .dateOfBirth(passenger.getDateOfBirth())
                .phoneNumber(passenger.getPhoneNumber())
                .rating(passengerUpdated.getRating())
                .build();
        passengerRepository.save(passengerUpdated);
        return passengerMapper.toDto(passenger);

    }

    @Override
    public PassengerDto updateRating(Long id, BigDecimal rating) {
        Passenger passenger = passengerRepository.findById(id)
                .orElseThrow(() -> new ResourceDoesNotExistException(""));
        passenger.setRating(rating);
        passengerRepository.save(passenger);
        return passengerMapper.toDto(passenger);
    }

}