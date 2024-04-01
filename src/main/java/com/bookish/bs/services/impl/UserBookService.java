package com.bookish.bs.services.impl;

import com.bookish.bs.dtos.userBook.RequestUserBookDto;
import com.bookish.bs.dtos.userBook.UserBookDto;
import com.bookish.bs.entities.*;
import com.bookish.bs.enums.BookState;
import com.bookish.bs.exceptions.InvalidDataException;
import com.bookish.bs.exceptions.NotFoundException;
import com.bookish.bs.keys.UserBookId;
import com.bookish.bs.repositories.BookRepository;
import com.bookish.bs.repositories.UserBookRepository;
import com.bookish.bs.repositories.UserRepository;
import com.bookish.bs.services.interfaces.IUserBookService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@Transactional
@Slf4j
@RequiredArgsConstructor
public class UserBookService implements IUserBookService {
    private final ModelMapper modelMapper;
    private final UserBookRepository userBookRepository;
    private final UserRepository userRepository;
    private final BookRepository bookRepository;

    @Override
    public UserBookDto save(RequestUserBookDto object) {
        log.info("Checking if the user exists");
        User user = userRepository.findById(object.getUserId())
                .orElseThrow(() -> new InvalidDataException("User id doesn't exist"));

        log.info("Checking if the book exists");
        Book book = bookRepository.findById(object.getBookId())
                .orElseThrow(() -> new InvalidDataException("Book id doesn't exist"));

        UserBookId userBookId = new UserBookId(object.getUserId(), object.getBookId());

        UserBook userBook = new UserBook();
        userBook.setId(userBookId);
        userBook.setUser(user);
        userBook.setBook(book);
        userBook.setState(BookState.valueOf(object.getState()));

        log.info("Saving new user book");
        return modelMapper.map(userBookRepository.save(userBook), UserBookDto.class);
    }

    @Override
    public UserBookDto update(UserBookId userBookId, RequestUserBookDto object) throws NotFoundException {
        log.info("Checking if the user book exists");
        UserBook userBook = userBookRepository.findById(userBookId)
                .orElseThrow(() -> new NotFoundException("User Book id doesn't exist"));

        log.info("Updating the user book state");
        userBook.setState(BookState.valueOf(object.getState()));
        return modelMapper.map(userBook, UserBookDto.class);
    }

    @Override
    public boolean delete(UserBookId userBookId) throws NotFoundException {
        log.info("Checking if the user book exists");
        UserBook userBook = userBookRepository.findById(userBookId)
                .orElseThrow(() -> new NotFoundException("User Book id doesn't exist"));

        log.info("Deleting user book {}", userBook.getId());
        userBookRepository.delete(userBook);
        return true;
    }

    @Override
    public UserBookDto findById(UserBookId userBookId) throws NotFoundException {
        log.info("Retrieving a user book by id");
        UserBook userBook = userBookRepository.findById(userBookId)
                .orElseThrow(() -> new NotFoundException("User Book not found"));
        return modelMapper.map(userBook, UserBookDto.class);
    }

    @Override
    public Page<UserBookDto> findAll(Pageable pageable) {
        log.info("Retrieving all user books");
        Page<UserBook> userBooks = userBookRepository.findAll(pageable);

        return new PageImpl<>(
                userBooks.getContent()
                        .stream()
                        .map(userBook -> modelMapper.map(userBook, UserBookDto.class))
                        .collect(Collectors.toList()),
                userBooks.getPageable(),
                userBooks.getTotalElements()
        );
    }

    @Override
    public List<UserBookDto> findUserBooksByUser(UUID uuid) throws NotFoundException {
        log.info("Checking if the user exists");
        userRepository.findById(uuid)
                .orElseThrow(() -> new NotFoundException("User id doesn't exist"));

        log.info("Retrieving all user books");
        return List.of(modelMapper.map(userBookRepository.findUserBooksById_UserId(uuid), UserBookDto[].class));
    }
}
