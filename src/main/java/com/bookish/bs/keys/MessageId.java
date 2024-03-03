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
public class MessageId implements Serializable {
    @Column(name = "user_id", nullable = false)
    private UUID userId;
    @Column(name = "conversation_id", nullable = false)
    private UUID conversationId;
}
