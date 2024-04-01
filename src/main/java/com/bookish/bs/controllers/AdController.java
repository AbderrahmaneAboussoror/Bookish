package com.bookish.bs.controllers;

import com.bookish.bs.dtos.Response;
import com.bookish.bs.dtos.ad.AdDto;
import com.bookish.bs.dtos.ad.RequestAdDto;
import com.bookish.bs.exceptions.InvalidDataException;
import com.bookish.bs.exceptions.NotFoundException;
import com.bookish.bs.services.impl.AdService;
import com.bookish.bs.services.interfaces.IAdService;
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
@RequestMapping("/api/ad")
@CrossOrigin
public class AdController {
    private final IAdService iAdService;

    @GetMapping
    public ResponseEntity<?> getAll(Pageable pageable) {
        if(iAdService.findAll(pageable).isEmpty()) {
            return new ResponseEntity<>(of("message", "No ads found"), OK);
        }
        return new ResponseEntity<>(iAdService.findAll(pageable), OK);
    }
    @GetMapping("/{id}")
    public ResponseEntity<AdDto> get(@PathVariable UUID id) throws NotFoundException {
        return new ResponseEntity<>(iAdService.findById(id), OK);
    }
    @PostMapping
    public ResponseEntity<AdDto> create(@Valid @RequestBody RequestAdDto ad) throws InvalidDataException {
        return new ResponseEntity<>(iAdService.save(ad), CREATED);
    }
    @PutMapping("/{id}")
    public ResponseEntity<AdDto> update(@Valid @RequestBody RequestAdDto ad, @PathVariable UUID id) throws NotFoundException {
        return new ResponseEntity<>(iAdService.update(id, ad), OK);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable UUID id) throws NotFoundException {
        if (iAdService.delete(id)) return new ResponseEntity<>("Ad with id "+ id +" deleted successfully", OK);
        throw new NotFoundException("Oops! something went wrong");
    }
}
