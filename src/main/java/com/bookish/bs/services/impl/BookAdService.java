package com.bookish.bs.services.impl;

import com.bookish.bs.dtos.bookAd.BookAdDto;
import com.bookish.bs.dtos.bookAd.RequestBookAdDto;
import com.bookish.bs.entities.*;
import com.bookish.bs.exceptions.InvalidDataException;
import com.bookish.bs.exceptions.NotFoundException;
import com.bookish.bs.keys.BookAdId;
import com.bookish.bs.repositories.AdRepository;
import com.bookish.bs.repositories.BookAdRepository;
import com.bookish.bs.repositories.BookRepository;
import com.bookish.bs.services.interfaces.IBookAdService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@Slf4j
@RequiredArgsConstructor
public class BookAdService implements IBookAdService {
    private final ModelMapper modelMapper;
    private final BookAdRepository bookAdRepository;
    private final BookRepository bookRepository;
    private final AdRepository adRepository;
    @Override
    public BookAdDto save(RequestBookAdDto object) {
        log.info("Checking if the book exists");
        Book book = bookRepository.findById(object.getBookId())
                .orElseThrow(() -> new InvalidDataException("Book id doesn't exist"));

        log.info("Checking if the ad exists");
        Ad ad = adRepository.findById(object.getAdId())
                .orElseThrow(() -> new InvalidDataException("Ad id doesn't exist"));

        BookAdId bookAdId = new BookAdId(object.getBookId(), object.getAdId());

        BookAd bookAd = new BookAd();
        bookAd.setId(bookAdId);
        bookAd.setBook(book);
        bookAd.setAd(ad);

        log.info("Saving new book ad");
        return modelMapper.map(bookAdRepository.save(bookAd), BookAdDto.class);
    }

    @Override
    public BookAdDto update(BookAdId bookAdId, RequestBookAdDto object) {
        return null;
    }

    @Override
    public boolean delete(BookAdId bookAdId) throws NotFoundException {
        log.info("Checking if the book ad exists");
        BookAd bookAd = bookAdRepository.findById(bookAdId)
                .orElseThrow(() -> new NotFoundException("Book Ad id doesn't exist"));

        log.info("Deleting book ad {}", bookAd.getId());
        bookAdRepository.delete(bookAd);
        return true;
    }

    @Override
    public BookAdDto findById(BookAdId bookAdId) throws NotFoundException {
        log.info("Retrieving a book ad by id");
        BookAd bookAd = bookAdRepository.findById(bookAdId)
                .orElseThrow(() -> new NotFoundException("Book ad not found"));
        return modelMapper.map(bookAd, BookAdDto.class);
    }

    @Override
    public Page<BookAdDto> findAll(Pageable pageable) {
        log.info("Retrieving all book ads");
        Page<BookAd> bookAds = bookAdRepository.findAll(pageable);

        return new PageImpl<>(
                bookAds.getContent()
                        .stream()
                        .map(bookAd -> modelMapper.map(bookAd, BookAdDto.class))
                        .collect(Collectors.toList()),
                bookAds.getPageable(),
                bookAds.getTotalElements()
        );
    }
}
