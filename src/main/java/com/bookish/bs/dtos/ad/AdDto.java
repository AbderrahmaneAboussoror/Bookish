package com.bookish.bs.dtos.ad;

import com.bookish.bs.dtos.UserDto;
import com.bookish.bs.dtos.bookAd.RequestBookAdDto;
import com.bookish.bs.enums.AdState;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.UUID;

// Getters, Setters, Constructors, toString
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AdDto {
    private UUID id;
    private AdState state;
    private UserDto user;
    private List<RequestBookAdDto> bookAds;
}
