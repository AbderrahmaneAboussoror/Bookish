package com.bookish.bs.dtos;

import com.bookish.bs.dtos.message.RequestMessageDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.UUID;

// Getters, Setters, Constructors, toString
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ConversationDto {
    private UUID id;
    private List<RequestMessageDto> messages;
}
