package com.bookish.bs.services.impl;

import com.bookish.bs.dtos.bookAd.BookAdDto;
import com.bookish.bs.dtos.bookAd.RequestBookAdDto;
import com.bookish.bs.keys.BookAdId;
import com.bookish.bs.services.interfaces.IBookAdService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
@Slf4j
@RequiredArgsConstructor
public class BookAdService implements IBookAdService {
    @Override
    public BookAdDto save(RequestBookAdDto object) {
        return null;
    }

    @Override
    public BookAdDto update(BookAdId bookAdId, RequestBookAdDto object) {
        return null;
    }

    @Override
    public BookAdDto findById(BookAdId bookAdId) {
        return null;
    }

    @Override
    public List<BookAdDto> findAll() {
        return null;
    }
}
