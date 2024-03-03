package com.bookish.bs.dtos.message;

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
public class RequestMessageDto {
    private UUID userId;
    private UUID conversationId;
    @NotNull(message = "The content cannot be null!")
    @NotEmpty(message = "The content cannot be empty!")
    private String content;
}
