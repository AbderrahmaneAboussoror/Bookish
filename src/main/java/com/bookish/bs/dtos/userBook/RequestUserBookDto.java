package com.bookish.bs.dtos.userBook;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

// Getters, Setters, Constructors, toString
@Data
@NoArgsConstructor
@AllArgsConstructor
public class RequestUserBookDto {
    private UUID userId;
    private UUID bookId;
    private String state;
}
