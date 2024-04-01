package com.bookish.bs.controllers;

import com.bookish.bs.dtos.BookDto;
import com.bookish.bs.dtos.Response;
import com.bookish.bs.exceptions.InvalidDataException;
import com.bookish.bs.exceptions.NotFoundException;
import com.bookish.bs.services.impl.BookService;
import com.bookish.bs.services.interfaces.IBookService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.UUID;

import static java.time.LocalDateTime.now;
import static java.util.Map.of;
import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.OK;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/book")
@CrossOrigin
public class BookController {
    private final IBookService iBookService;

    @GetMapping
    public ResponseEntity<?> getAll(Pageable pageable) {
        if(iBookService.findAll(pageable).isEmpty()) {
            return new ResponseEntity<>(of("message", "No books found"), OK);
        }
        return new ResponseEntity<>(iBookService.findAll(pageable), OK);
    }
    @GetMapping("/{id}")
    public ResponseEntity<BookDto> get(@PathVariable UUID id) throws NotFoundException {
        return new ResponseEntity<>(iBookService.findById(id), OK);
    }
    @PostMapping
    public ResponseEntity<BookDto> create(@Valid @RequestBody BookDto book) throws InvalidDataException {
        return new ResponseEntity<>(iBookService.save(book), CREATED);
    }
    @PutMapping("/{id}")
    public ResponseEntity<BookDto> update(@Valid @RequestBody BookDto book, @PathVariable UUID id) throws NotFoundException {
        return new ResponseEntity<>(iBookService.update(id, book), OK);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Object> delete(@PathVariable UUID id) throws NotFoundException {
        if (iBookService.delete(id)) {
            return ResponseEntity.ok().body(Map.of("message", "Book with id " + id + " deleted successfully"));
        }
        throw new NotFoundException("Oops! something went wrong");
    }
}
