package com.bookish.bs.services.impl;

import com.bookish.bs.dtos.userBook.RequestUserBookDto;
import com.bookish.bs.dtos.userBook.UserBookDto;
import com.bookish.bs.keys.UserBookId;
import com.bookish.bs.services.interfaces.IUserBookService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
@Slf4j
@RequiredArgsConstructor
public class UserBookService implements IUserBookService {
    @Override
    public UserBookDto save(RequestUserBookDto object) {
        return null;
    }

    @Override
    public UserBookDto update(UserBookId userBookId, RequestUserBookDto object) {
        return null;
    }

    @Override
    public UserBookDto findById(UserBookId userBookId) {
        return null;
    }

    @Override
    public List<UserBookDto> findAll() {
        return null;
    }
}
