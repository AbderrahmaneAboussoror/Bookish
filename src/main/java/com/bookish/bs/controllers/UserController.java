package com.bookish.bs.controllers;

import com.bookish.bs.dtos.Response;
import com.bookish.bs.dtos.UserDto;
import com.bookish.bs.exceptions.InvalidDataException;
import com.bookish.bs.exceptions.NotFoundException;
import com.bookish.bs.services.impl.UserService;
import com.bookish.bs.services.interfaces.IUserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

import static java.time.LocalDateTime.now;
import static java.util.Map.of;
import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.OK;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/user")
@CrossOrigin
public class UserController {
    private final IUserService iUserService;

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping
    public ResponseEntity<?> getAll(Pageable pageable) {
        if(iUserService.findAll(pageable).isEmpty()) {
            return new ResponseEntity<>(of("message", "No users found"), OK);
        }
        return new ResponseEntity<>(iUserService.findAll(pageable), OK);
    }
    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/{id}")
    public ResponseEntity<UserDto> get(@PathVariable UUID id) throws NotFoundException {
        return new ResponseEntity<>(iUserService.findById(id), OK);
    }
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    public ResponseEntity<UserDto> create(@Valid @RequestBody UserDto user) throws InvalidDataException {
        return new ResponseEntity<>(iUserService.save(user), CREATED);
    }
    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/{id}")
    public ResponseEntity<UserDto> update(@Valid @RequestBody UserDto user, @PathVariable UUID id) throws NotFoundException {
        return new ResponseEntity<>(iUserService.update(id, user), OK);
    }
    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable UUID id) throws NotFoundException {
        if (iUserService.delete(id)) return new ResponseEntity<>("User with id "+ id +" deleted successfully", OK);
        throw new NotFoundException("Oops! something went wrong");
    }
}
