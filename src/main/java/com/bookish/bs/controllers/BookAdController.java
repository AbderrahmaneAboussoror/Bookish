package com.bookish.bs.controllers;

import com.bookish.bs.dtos.Response;
import com.bookish.bs.dtos.bookAd.BookAdDto;
import com.bookish.bs.dtos.bookAd.RequestBookAdDto;
import com.bookish.bs.exceptions.InvalidDataException;
import com.bookish.bs.exceptions.NotFoundException;
import com.bookish.bs.keys.BookAdId;
import com.bookish.bs.services.impl.BookAdService;
import com.bookish.bs.services.interfaces.IBookAdService;
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
@RequestMapping("/api/book_ad")
@CrossOrigin
public class BookAdController {
    private final IBookAdService iBookAdService;

    @GetMapping
    public ResponseEntity<?> getAll(Pageable pageable) {
        if(iBookAdService.findAll(pageable).isEmpty()) {
            return new ResponseEntity<>(of("message", "No book ads found"), OK);
        }
        return new ResponseEntity<>(iBookAdService.findAll(pageable), OK);
    }
    @GetMapping("/{bookId}/{adId}")
    public ResponseEntity<BookAdDto> get(@PathVariable UUID bookId, @PathVariable UUID adId) throws NotFoundException {
        BookAdId bookAdId = new BookAdId(bookId, adId);
        return new ResponseEntity<>(iBookAdService.findById(bookAdId), OK);
    }
    @PostMapping
    public ResponseEntity<BookAdDto> create(@Valid @RequestBody RequestBookAdDto bookAd) throws InvalidDataException {
        return new ResponseEntity<>(iBookAdService.save(bookAd), CREATED);
    }
    @PutMapping("/{bookId}/{adId}")
    public ResponseEntity<BookAdDto> update(@PathVariable UUID bookId, @PathVariable UUID adId, @Valid @RequestBody RequestBookAdDto bookAd) throws NotFoundException {
        BookAdId bookAdId = new BookAdId(bookId, adId);
        return new ResponseEntity<>(iBookAdService.update(bookAdId, bookAd), OK);
    }
    @DeleteMapping("/{bookId}/{adId}")
    public ResponseEntity<String> delete(@PathVariable UUID bookId, @PathVariable UUID adId) throws NotFoundException {
        BookAdId bookAdId = new BookAdId(bookId, adId);
        if (iBookAdService.delete(bookAdId)) return new ResponseEntity<>("Book ad with id "+ bookAdId +" deleted successfully", OK);
        throw new NotFoundException("Oops! something went wrong");
    }
}
