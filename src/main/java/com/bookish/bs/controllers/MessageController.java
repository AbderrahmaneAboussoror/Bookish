package com.bookish.bs.controllers;

import com.bookish.bs.dtos.Response;
import com.bookish.bs.dtos.message.MessageDto;
import com.bookish.bs.dtos.message.RequestMessageDto;
import com.bookish.bs.exceptions.InvalidDataException;
import com.bookish.bs.exceptions.NotFoundException;
import com.bookish.bs.keys.MessageId;
import com.bookish.bs.services.impl.MessageService;
import com.bookish.bs.services.interfaces.IMessageService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

import static java.time.LocalDateTime.now;
import static java.util.Map.of;
import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.OK;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/message")
@CrossOrigin
public class MessageController {
    private final IMessageService iMessageService;
    @GetMapping
    public ResponseEntity<?> getAll(Pageable pageable) {
        if(iMessageService.findAll(pageable).isEmpty()) {
            return new ResponseEntity<>(of("message", "No messages found"), OK);
        }
        return new ResponseEntity<>(iMessageService.findAll(pageable), OK);
    }
    @GetMapping("/{userId}/{conversationId}")
    public ResponseEntity<MessageDto> get(@PathVariable UUID userId, @PathVariable UUID conversationId) throws NotFoundException {
        MessageId messageId = new MessageId(userId, conversationId);
        return new ResponseEntity<>(iMessageService.findById(messageId), OK);
    }
    @GetMapping("/{conversationId}")
    public ResponseEntity<?> getByConversation(@PathVariable UUID conversationId) throws NotFoundException {
        return new ResponseEntity<>(iMessageService.findMessagesByConversation(conversationId), OK);
    }
    @PostMapping
    public ResponseEntity<MessageDto> create(@Valid @RequestBody RequestMessageDto message) throws InvalidDataException {
        return new ResponseEntity<>(iMessageService.save(message), CREATED);
    }
    @PutMapping("/{userId}/{conversationId}")
    public ResponseEntity<MessageDto> update(@PathVariable UUID userId, @PathVariable UUID conversationId, @Valid @RequestBody RequestMessageDto message) throws NotFoundException {
        MessageId messageId = new MessageId(userId, conversationId);
        return new ResponseEntity<>(iMessageService.update(messageId, message), OK);
    }
    @DeleteMapping("/{userId}/{conversationId}")
    public ResponseEntity<String> delete(@PathVariable UUID userId, @PathVariable UUID conversationId) throws NotFoundException {
        MessageId messageId = new MessageId(userId, conversationId);
        if (iMessageService.delete(messageId)) return new ResponseEntity<>("Message with id "+ messageId +" deleted successfully", OK);
        throw new NotFoundException("Oops! something went wrong");
    }
    @GetMapping("/{userId}")
    public ResponseEntity<?> getByUser(@PathVariable UUID userId) throws NotFoundException {
        return new ResponseEntity<>(iMessageService.findMessagesByUser(userId), OK);
    }
}
