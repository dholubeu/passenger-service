package com.dholubeu.passengerservice.web.dto;

import com.dholubeu.passengerservice.web.validator.ValidAge;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Max;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
public class PassengerDto {

    private Long id;

    @NotEmpty(message = "Email is required")
    @Email(message = "Email is not valid")
    private String email;

    @NotEmpty(message = "Password is required")
    @Length(min = 8, message = "Password must be longer than 8 characters")
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String password;

    @NotEmpty(message = "Name is required")
    @Length(min = 3, max = 60, message = "Name must be between 3 and 60 characters")
    private String name;

    @NotEmpty(message = "Surname is required")
    @Length(min = 3, max = 60, message = "Surname must be between 3 and 60 characters")
    private String surname;

    @ValidAge
    private LocalDate dateOfBirth;

    @Pattern(regexp = "^375(29|44)\\d{7}$", message = "Invalid phone number")
    private String phoneNumber;

    @Max(value = 5, message = "Rating must be less than 5")
    @Min(value = 1, message = "Rating must be more than 1")
    private BigDecimal rating;

}