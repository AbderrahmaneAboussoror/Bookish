package com.bookish.bs.entities;

import com.bookish.bs.enums.BookState;
import com.bookish.bs.keys.UserBookId;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

// Getters, Setters, Constructors, toString
@Data
@NoArgsConstructor
@AllArgsConstructor

@Entity
public class UserBook implements Serializable {
    @EmbeddedId
    private UserBookId id;
    @Enumerated(EnumType.STRING)
    private BookState state;
    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("userId")
    private User user;
    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("bookId")
    private Book book;
}
