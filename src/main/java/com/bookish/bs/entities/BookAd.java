package com.bookish.bs.entities;

import com.bookish.bs.keys.BookAdId;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

// Getters, Setters, Constructors, toString
@Data
@NoArgsConstructor
@AllArgsConstructor

@Entity
public class BookAd {
    @EmbeddedId
    private BookAdId id;
    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("bookId")
    private Book book;
    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("adId")
    private Ad ad;
}
