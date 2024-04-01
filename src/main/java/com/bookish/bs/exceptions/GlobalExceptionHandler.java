package com.bookish.bs.exceptions;

import com.bookish.bs.dtos.Response;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import static java.time.LocalDateTime.now;
import static org.springframework.http.HttpStatus.NOT_FOUND;

@RestControllerAdvice
public class ExceptionHandler {
    @org.springframework.web.bind.annotation.ExceptionHandler(NotFoundException.class)
    @ResponseStatus(NOT_FOUND)
    public Response handleNotFoundException(NotFoundException exception) {
        return Response.builder()
                .timeStamp(now())
                .statusCode(NOT_FOUND.value())
                .reason(exception.getMessage())
                .build();
    }
}
