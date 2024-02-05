package com.dholubeu.passengerservice.web.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.AllArgsConstructor;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor

public class ResponseDto {

    private List<String> errors;

}
