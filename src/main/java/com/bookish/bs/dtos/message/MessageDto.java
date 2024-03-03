package com.bookish.bs.dtos.message;

import com.bookish.bs.dtos.ConversationDto;
import com.bookish.bs.dtos.UserDto;
import com.bookish.bs.keys.MessageId;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

// Getters, Setters, Constructors, toString
@Data
@NoArgsConstructor
@AllArgsConstructor
public class MessageDto {
    private MessageId id;
    @NotNull(message = "The content cannot be null!")
    @NotEmpty(message = "The content cannot be empty!")
    private String content;
    private UserDto user;
    private ConversationDto conversation;
}
