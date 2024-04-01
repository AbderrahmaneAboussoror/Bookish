package com.bookish.bs.services.impl;

import com.bookish.bs.dtos.BookDto;
import com.bookish.bs.entities.Book;
import com.bookish.bs.enums.BookGenre;
import com.bookish.bs.exceptions.NotFoundException;
import com.bookish.bs.repositories.BookRepository;
import com.bookish.bs.services.interfaces.IBookService;
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
public class BookService implements IBookService {
    private final ModelMapper modelMapper;
    private final BookRepository bookRepository;
    @Override
    public BookDto save(BookDto object) {
        Book book = modelMapper.map(object, Book.class);
        book.setGenre(BookGenre.valueOf(object.getGenre()));
        log.info("Saving new book {}", book.getTitle());
        return modelMapper.map(bookRepository.save(book), BookDto.class);
    }

    @Override
    public BookDto update(UUID uuid, BookDto object) throws NotFoundException {
        log.info("Checking if the book exists");
        Book book = bookRepository.findById(uuid)
                .orElseThrow(() -> new NotFoundException("Book not found"));

        book.setAuthor(object.getAuthor());
        book.setTitle(object.getTitle());
        book.setGenre(BookGenre.valueOf(object.getGenre()));

        log.info("Updating book {}", book.getTitle());
        return modelMapper.map(bookRepository.save(book), BookDto.class);
    }

    @Override
    public boolean delete(UUID uuid) throws NotFoundException {
        log.info("Checking if the book exists");
        Book book = bookRepository.findById(uuid)
                .orElseThrow(() -> new NotFoundException("Book not found"));

        log.info("Deleting book {}", book.getTitle());
        bookRepository.delete(book);
        return true;
    }

    @Override
    public BookDto findById(UUID uuid) throws NotFoundException {
        log.info("Retrieving a book by id");
        Book book = bookRepository.findById(uuid)
                .orElseThrow(() -> new NotFoundException("Book not found"));
        return modelMapper.map(book, BookDto.class);
    }

    @Override
    public Page<BookDto> findAll(Pageable pageable) {
        log.info("Retrieving all books");
        Page<Book> books = bookRepository.findAll(pageable);

        return new PageImpl<>(
                books.getContent()
                        .stream()
                        .map(book -> modelMapper.map(book, BookDto.class))
                        .collect(Collectors.toList()),
                books.getPageable(),
                books.getTotalElements()
        );
    }

    @Override
    public BookDto findByTitle(String title) throws NotFoundException {
        log.info("Retrieving a book by title");
        Book book = bookRepository.findBookByTitle(title)
                .orElseThrow(() -> new NotFoundException("Book not found"));
        return modelMapper.map(book, BookDto.class);
    }

    @Override
    public List<BookDto> findByGenre(String genre) {
        return null;
    }

    @Override
    public List<BookDto> findByAuthor(String author) {
        log.info("Retrieving all books by author");
        return List.of(modelMapper.map(bookRepository.findBooksByAuthor(author), BookDto[].class));
    }
}
