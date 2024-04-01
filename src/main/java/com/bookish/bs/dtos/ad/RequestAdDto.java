package com.bookish.bs.dtos.ad;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

// Getters, Setters, Constructors, toString
@Data
@NoArgsConstructor
@AllArgsConstructor
public class RequestAdDto {
    private UUID id;
    private String state;
    private UUID userId;
}
