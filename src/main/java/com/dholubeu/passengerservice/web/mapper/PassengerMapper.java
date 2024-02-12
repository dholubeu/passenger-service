package com.dholubeu.passengerservice.web.mapper;

import com.dholubeu.passengerservice.domain.Passenger;
import com.dholubeu.passengerservice.web.dto.PassengerDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PassengerMapper {

    PassengerDto toDto(Passenger passenger);

    Passenger toEntity(PassengerDto passengerDto);

}