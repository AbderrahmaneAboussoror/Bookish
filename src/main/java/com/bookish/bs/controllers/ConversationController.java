package com.bookish.bs.controllers;

import com.bookish.bs.dtos.ConversationDto;
import com.bookish.bs.dtos.Response;
import com.bookish.bs.exceptions.InvalidDataException;
import com.bookish.bs.exceptions.NotFoundException;
import com.bookish.bs.services.impl.ConversationService;
import com.bookish.bs.services.interfaces.IConversationService;
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
@RequestMapping("/api/conversation")
@CrossOrigin
public class ConversationController {
    private final IConversationService iConversationService;

    @GetMapping
    public ResponseEntity<?> getAll(Pageable pageable) {
        if(iConversationService.findAll(pageable).isEmpty()) {
            return new ResponseEntity<>(of("message", "No conversations found"), OK);
        }
        return new ResponseEntity<>(iConversationService.findAll(pageable), OK);
    }
    @GetMapping("/{id}")
    public ResponseEntity<ConversationDto> get(@PathVariable UUID id) throws NotFoundException {
        return new ResponseEntity<>(iConversationService.findById(id), OK);
    }
    @PostMapping
    public ResponseEntity<ConversationDto> create(@Valid @RequestBody ConversationDto conversation) throws InvalidDataException {
        return new ResponseEntity<>(iConversationService.save(conversation), CREATED);
    }
    @PutMapping("/{id}")
    public ResponseEntity<ConversationDto> update(@Valid @RequestBody ConversationDto conversation, @PathVariable UUID id) throws NotFoundException {
        return new ResponseEntity<>(iConversationService.update(id, conversation), OK);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable UUID id) throws NotFoundException {
        if (iConversationService.delete(id)) return new ResponseEntity<>("Conversation with id "+ id +" deleted successfully", OK);
        throw new NotFoundException("Oops! something went wrong");
    }
}
