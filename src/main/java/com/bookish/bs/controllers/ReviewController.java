package com.bookish.bs.controllers;

import com.bookish.bs.dtos.Response;
import com.bookish.bs.dtos.review.RequestReviewDto;
import com.bookish.bs.dtos.review.ReviewDto;
import com.bookish.bs.exceptions.InvalidDataException;
import com.bookish.bs.exceptions.NotFoundException;
import com.bookish.bs.keys.UserBookId;
import com.bookish.bs.services.impl.ReviewService;
import com.bookish.bs.services.interfaces.IReviewService;
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
@RequestMapping("/api/review")
@CrossOrigin
public class ReviewController {
    private final IReviewService iReviewService;

    @GetMapping
    public ResponseEntity<?> getAll(Pageable pageable) {
        if(iReviewService.findAll(pageable).isEmpty()) {
            return new ResponseEntity<>(of("message", "No reviews books found"), OK);
        }
        return new ResponseEntity<>(iReviewService.findAll(pageable), OK);
    }
    @GetMapping("/{id}")
    public ResponseEntity<ReviewDto> get(@PathVariable UUID id) throws NotFoundException {
        return new ResponseEntity<>(iReviewService.findById(id), OK);
    }
    @PostMapping
    public ResponseEntity<ReviewDto> create(@Valid @RequestBody RequestReviewDto review) throws InvalidDataException {
        return new ResponseEntity<>(iReviewService.save(review), CREATED);
    }
    @PutMapping("/{id}")
    public ResponseEntity<ReviewDto> update(@Valid @RequestBody RequestReviewDto review, @PathVariable UUID id) throws NotFoundException {
        return new ResponseEntity<>(iReviewService.update(id, review), OK);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable UUID id) throws NotFoundException {
        if (iReviewService.delete(id)) return new ResponseEntity<>("Review with id "+ id +" deleted successfully", OK);
        throw new NotFoundException("Oops! something went wrong");
    }
}
