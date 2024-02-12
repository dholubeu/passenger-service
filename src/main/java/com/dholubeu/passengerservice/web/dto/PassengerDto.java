package com.dholubeu.passengerservice.web.dto;

import com.dholubeu.passengerservice.web.dto.validation.OnCreate;
import com.dholubeu.passengerservice.web.dto.validation.OnUpdate;
import com.dholubeu.passengerservice.web.validator.ValidAge;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
@RequiredArgsConstructor
public class PassengerDto {

    @NotNull(groups = OnUpdate.class)
    private Long id;

    @NotEmpty(message = "Email is required", groups = OnCreate.class)
    @Email(message = "Email is not valid", groups = OnCreate.class)
    private String email;

    @NotEmpty(message = "Name is required", groups = {OnCreate.class, OnUpdate.class})
    @Length(min = 3, max = 60, message = "Name must be between 3 and 60 characters",
            groups = {OnCreate.class, OnUpdate.class})
    private String name;

    @NotEmpty(message = "Surname is required", groups = {OnCreate.class, OnUpdate.class})
    @Length(min = 3, max = 60, message = "Surname must be between 3 and 60 characters",
            groups = {OnCreate.class, OnUpdate.class})
    private String surname;

    @ValidAge(groups = {OnCreate.class, OnUpdate.class})
    private LocalDate dateOfBirth;

    @Pattern(regexp = "^375(29|44)\\d{7}$", message = "Invalid phone number",
            groups = {OnCreate.class, OnUpdate.class})
    private String phoneNumber;

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private BigDecimal rating;

}