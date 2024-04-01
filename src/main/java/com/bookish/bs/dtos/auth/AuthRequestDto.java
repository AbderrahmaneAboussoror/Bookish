package com.bookish.bs.dtos.auth;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

// Getters, Setters, Constructors, toString
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AuthRequestDto {
    @NotEmpty(message = "email can't be empty")
    @NotNull(message = "email is required")
    @Email(message = "email must be valid")
    private String email;
    @NotEmpty(message = "password can't be empty")
    @NotNull(message = "password is required")
    @Size(message = "password must be at least 8 characters and less than 16", min = 8, max = 15)
    private String password;

}
