package com.bookish.bs.repositories;

import com.bookish.bs.entities.Message;
import com.bookish.bs.keys.MessageId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface MessageRepository extends JpaRepository<Message, MessageId> {
    List<Message> findMessagesById_UserId(UUID uuid);
    List<Message> findMessagesById_ConversationId(UUID uuid);
}
