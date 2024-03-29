package com.dholubeu.passengerservice.web.controller;

import com.dholubeu.passengerservice.service.PassengerService;
import com.dholubeu.passengerservice.web.dto.PassengerDto;
import com.dholubeu.passengerservice.web.dto.validation.OnCreate;
import com.dholubeu.passengerservice.web.dto.validation.OnUpdate;
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

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public PassengerDto create(@RequestBody @Validated(OnCreate.class) PassengerDto passengerDto) {
        return passengerService.create(passengerDto);
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public PassengerDto findById(@PathVariable Long id) {
        return passengerService.findById(id);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public PassengerDto update(@PathVariable Long id,
                               @RequestBody @Validated(OnUpdate.class) PassengerDto passengerDto) {
        return passengerService.update(id, passengerDto);
    }

    @PutMapping("/{id}/ratings")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public PassengerDto updateRating(@PathVariable Long id, @RequestParam BigDecimal rating) {
        return passengerService.updateRating(id, rating);
    }


}