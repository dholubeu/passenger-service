package com.dholubeu.passengerservice.web.mapper;

import com.dholubeu.passengerservice.domain.Password;
import com.dholubeu.passengerservice.web.dto.PasswordDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PasswordMapper {

    PasswordDto toDto(Password password);

    Password toEntity(PasswordDto passwordDto);

}