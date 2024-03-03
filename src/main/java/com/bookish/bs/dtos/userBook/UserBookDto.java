package com.bookish.bs.dtos.userBook;

import com.bookish.bs.dtos.BookDto;
import com.bookish.bs.dtos.UserDto;
import com.bookish.bs.enums.BookState;
import com.bookish.bs.keys.UserBookId;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

// Getters, Setters, Constructors, toString
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserBookDto {
    private UserBookId id;
    private BookState state;
    private UserDto user;
    private BookDto book;
}
