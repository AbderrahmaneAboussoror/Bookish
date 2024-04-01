package com.bookish.bs.controllers;

import com.bookish.bs.dtos.Response;
import com.bookish.bs.dtos.notification.NotificationDto;
import com.bookish.bs.dtos.notification.RequestNotificationDto;
import com.bookish.bs.exceptions.InvalidDataException;
import com.bookish.bs.exceptions.NotFoundException;
import com.bookish.bs.services.impl.NotificationService;
import com.bookish.bs.services.interfaces.INotificationService;
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
@RequestMapping("/api/notification")
@CrossOrigin
public class NotificationController {
    private final INotificationService iNotificationService;

    @GetMapping
    public ResponseEntity<?> getAll(Pageable pageable) {
        if(iNotificationService.findAll(pageable).isEmpty()) {
            return new ResponseEntity<>(of("message", "No notifications found"), OK);
        }
        return new ResponseEntity<>(iNotificationService.findAll(pageable), OK);
    }
    @GetMapping("/{id}")
    public ResponseEntity<NotificationDto> get(@PathVariable UUID id) throws NotFoundException {
        return new ResponseEntity<>(iNotificationService.findById(id), OK);
    }
    @PostMapping
    public ResponseEntity<NotificationDto> create(@Valid @RequestBody RequestNotificationDto notification) throws InvalidDataException {
        return new ResponseEntity<>(iNotificationService.save(notification), CREATED);
    }
    @PutMapping("/{id}")
    public ResponseEntity<NotificationDto> update(@Valid @RequestBody RequestNotificationDto notification, @PathVariable UUID id) throws NotFoundException {
        return new ResponseEntity<>(iNotificationService.update(id, notification), OK);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable UUID id) throws NotFoundException {
        if (iNotificationService.delete(id)) return new ResponseEntity<>("Notification with id "+ id +" deleted successfully", OK);
        throw new NotFoundException("Oops! something went wrong");
    }
}
