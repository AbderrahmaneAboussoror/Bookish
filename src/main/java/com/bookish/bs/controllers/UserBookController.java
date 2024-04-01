package com.bookish.bs.controllers;

import com.bookish.bs.dtos.Response;
import com.bookish.bs.dtos.userBook.RequestUserBookDto;
import com.bookish.bs.dtos.userBook.UserBookDto;
import com.bookish.bs.exceptions.InvalidDataException;
import com.bookish.bs.exceptions.NotFoundException;
import com.bookish.bs.keys.UserBookId;
import com.bookish.bs.services.impl.UserBookService;
import com.bookish.bs.services.interfaces.IUserBookService;
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
@RequestMapping("/api/user_book")
@CrossOrigin
public class UserBookController {
    private final IUserBookService iUserBookService;

    @GetMapping
    public ResponseEntity<?> getAll(Pageable pageable) {
        if(iUserBookService.findAll(pageable).isEmpty()) {
            return new ResponseEntity<>(of("message", "No user books found"), OK);
        }
        return new ResponseEntity<>(iUserBookService.findAll(pageable), OK);
    }
    @GetMapping("/{userId}/{bookId}")
    public ResponseEntity<UserBookDto> get(@PathVariable UUID userId, @PathVariable UUID bookId) throws NotFoundException {
        UserBookId userBookId = new UserBookId(userId, bookId);
        return new ResponseEntity<>(iUserBookService.findById(userBookId), OK);
    }
    @PostMapping
    public ResponseEntity<UserBookDto> create(@Valid @RequestBody RequestUserBookDto userBook) throws InvalidDataException {
        return new ResponseEntity<>(iUserBookService.save(userBook), CREATED);
    }
    @PutMapping("/{userId}/{bookId}")
    public ResponseEntity<UserBookDto> update(@PathVariable UUID userId, @PathVariable UUID bookId, @Valid @RequestBody RequestUserBookDto userBook) throws NotFoundException {
        UserBookId userBookId = new UserBookId(userId, bookId);
        return new ResponseEntity<>(iUserBookService.update(userBookId, userBook), OK);
    }
    @DeleteMapping("/{userId}/{bookId}")
    public ResponseEntity<String> delete(@PathVariable UUID userId, @PathVariable UUID bookId) throws NotFoundException {
        UserBookId userBookId = new UserBookId(userId, bookId);
        if (iUserBookService.delete(userBookId)) return new ResponseEntity<>("User book with id "+ userBookId +" deleted successfully", OK);
        throw new NotFoundException("Oops! something went wrong");
    }
}
