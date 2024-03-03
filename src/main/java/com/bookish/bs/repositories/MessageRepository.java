package com.bookish.bs.repositories;

import com.bookish.bs.entities.Message;
import com.bookish.bs.keys.MessageId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MessageRepository extends JpaRepository<Message, MessageId> {
}
