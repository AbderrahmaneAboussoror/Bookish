package com.bookish.bs.dtos.bookAd;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

// Getters, Setters, Constructors, toString
@Data
@NoArgsConstructor
@AllArgsConstructor
public class RequestBookAdDto {
    private UUID bookId;
    private UUID adId;
}
