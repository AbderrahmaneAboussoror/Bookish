package com.bookish.bs.keys;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.UUID;

// Getters, Setters, Constructors, toString
@Data
@NoArgsConstructor
@AllArgsConstructor

@Embeddable
public class BookAdId implements Serializable {
    @Column(name = "book_id", nullable = false)
    private UUID bookId;
    @Column(name = "ad_id", nullable = false)
    private UUID adId;
}
