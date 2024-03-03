package com.bookish.bs.dtos.bookAd;

import com.bookish.bs.dtos.BookDto;
import com.bookish.bs.dtos.ad.AdDto;
import com.bookish.bs.keys.BookAdId;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

// Getters, Setters, Constructors, toString
@Data
@NoArgsConstructor
@AllArgsConstructor
public class BookAdDto {
    private BookAdId id;
    private BookDto book;
    private AdDto ad;
}
