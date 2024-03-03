package com.bookish.bs.dtos.notification;

import com.bookish.bs.dtos.UserDto;
import com.bookish.bs.enums.NotificationType;
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
public class NotificationDto {
    private UUID id;
    @NotNull(message = "The content cannot be null!")
    @NotEmpty(message = "The content cannot be empty!")
    private String content;
    private NotificationType type;
    private UserDto user;
}
