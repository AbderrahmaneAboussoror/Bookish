package com.bookish.bs.dtos;

import com.bookish.bs.dtos.message.RequestMessageDto;
import com.bookish.bs.dtos.notification.RequestNotificationDto;
import com.bookish.bs.dtos.review.RequestReviewDto;
import com.bookish.bs.dtos.userBook.RequestUserBookDto;
import com.bookish.bs.enums.Role;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.UUID;

// Getters, Setters, Constructors, toString
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {
    private UUID id;
    @NotNull(message = "The first name cannot be null!")
    @NotEmpty(message = "The first name cannot be empty!")
    private String firstName;
    @NotNull(message = "The last name cannot be null!")
    @NotEmpty(message = "The last name cannot be empty!")
    private String lastName;
    @NotNull(message = "The email cannot be null!")
    @NotEmpty(message = "The email cannot be empty!")
    private String email;
    @NotNull(message = "The password cannot be null!")
    @NotEmpty(message = "The password cannot be empty!")
    private String password;
    private Role role;
    private List<RequestUserBookDto> userBooks;
    private List<RequestMessageDto> messages;
    private List<RequestReviewDto> reviews;
    private List<RequestNotificationDto> notifications;
}
