package com.bookish.bs.dtos;

import com.bookish.bs.dtos.bookAd.RequestBookAdDto;
import com.bookish.bs.dtos.userBook.RequestUserBookDto;
import com.bookish.bs.enums.BookGenre;
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
public class BookDto {
    private UUID id;
    @NotNull(message = "The title cannot be null!")
    @NotEmpty(message = "The title cannot be empty!")
    private String title;
    @NotNull(message = "The author cannot be null!")
    @NotEmpty(message = "The author cannot be empty!")
    private String author;
    private BookGenre genre;
    private List<RequestBookAdDto> bookAds;
    private List<RequestUserBookDto> userBooks;
}
