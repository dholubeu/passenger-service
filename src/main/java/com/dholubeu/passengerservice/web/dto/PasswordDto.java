package com.dholubeu.passengerservice.web.dto;

import jakarta.validation.constraints.NotEmpty;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

@Data
public class PasswordDto {

    @NotEmpty(message = "Old password is required")
    private String oldPassword;

    @NotEmpty(message = "New password is required")
    @Length(min = 8, message = "Password must be at least 8 characters long")
    private String newPassword;

}