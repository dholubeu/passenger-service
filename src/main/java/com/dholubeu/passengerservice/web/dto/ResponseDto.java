package com.dholubeu.passengerservice.web.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class ResponseDto {

    private List<String> errors;

}
