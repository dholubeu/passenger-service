package com.dholubeu.passengerservice.web.controller;


import com.dholubeu.passengerservice.domain.Passenger;
import com.dholubeu.passengerservice.service.PassengerService;
import com.dholubeu.passengerservice.web.dto.PassengerDto;
import com.dholubeu.passengerservice.web.dto.validation.OnCreate;
import com.dholubeu.passengerservice.web.dto.validation.OnUpdate;
import com.dholubeu.passengerservice.web.mapper.PassengerMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.math.BigDecimal;

@RestController
@RequestMapping("/api/v1/passengers")
@RequiredArgsConstructor
public class PassengerController {

    private final PassengerService passengerService;
    private final PassengerMapper passengerMapper;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public PassengerDto create(@RequestBody @Validated(OnCreate.class) PassengerDto passengerDto) {
        Passenger passenger = passengerMapper.toEntity(passengerDto);
        passenger = passengerService.create(passenger);
        return passengerMapper.toDto(passenger);
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public PassengerDto findById(@PathVariable Long id) {
        Passenger passenger = passengerService.findById(id);
        return passengerMapper.toDto(passenger);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public PassengerDto update(@PathVariable Long id,
                               @RequestBody @Validated(OnUpdate.class) PassengerDto passengerDto) {
        Passenger passenger = passengerMapper.toEntity(passengerDto);
        passenger = passengerService.update(id, passenger);
        return passengerMapper.toDto(passenger);
    }

    @PutMapping("{id}/ratings")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public PassengerDto updateRating(@PathVariable Long id, @RequestParam BigDecimal rating) {
        Passenger passenger = passengerService.updateRating(id, rating);
        return passengerMapper.toDto(passenger);
    }


}