package com.bookish.bs.services.impl;

import com.bookish.bs.dtos.BookDto;
import com.bookish.bs.services.interfaces.IBookService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@Transactional
@Slf4j
@RequiredArgsConstructor
public class BookService implements IBookService {
    @Override
    public BookDto save(BookDto object) {
        return null;
    }

    @Override
    public BookDto update(UUID uuid, BookDto object) {
        return null;
    }

    @Override
    public BookDto findById(UUID uuid) {
        return null;
    }

    @Override
    public List<BookDto> findAll() {
        return null;
    }
}
