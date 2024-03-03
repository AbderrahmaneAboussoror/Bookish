package com.bookish.bs.dtos.review;

import com.bookish.bs.dtos.UserDto;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

// Getters, Setters, Constructors, toString
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ReviewDto {
    private UUID id;
    @NotNull(message = "The comment cannot be null!")
    @NotEmpty(message = "The comment cannot be empty!")
    private String comment;
    @NotNull(message = "The note cannot be null!")
    @Min(value = 0, message = "The note cannot be negative!")
    @Max(value = 5, message = "The note cannot be higher than 5!")
    private int note;
    private UserDto user;
}
