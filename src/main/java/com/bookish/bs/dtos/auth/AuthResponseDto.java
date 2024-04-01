package com.bookish.bs.dtos.auth;

import com.bookish.bs.dtos.UserDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

// Getters, Setters, Constructors, toString
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AuthResponseDto {
    private String Token;
    private String ExpirationTime;
    private String RefreshToken;
    private UserDto userDto;
}
