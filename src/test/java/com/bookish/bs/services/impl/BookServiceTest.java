package com.bookish.bs.services.impl;

import com.bookish.bs.dtos.BookDto;
import com.bookish.bs.entities.Book;
import com.bookish.bs.enums.BookGenre;
import com.bookish.bs.repositories.BookRepository;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@Slf4j
public class BookServiceTest {
    @InjectMocks
    private BookService bookService;

    @Mock
    private BookRepository bookRepository;

    @Mock
    private ModelMapper modelMapper;

    @Test
    void saveBookTest() {
        // Arrange
        BookDto requestBookDto = new BookDto(null, "Title", "Author", "FICTION", null, null);
        Book book = new Book();
        book.setId(UUID.randomUUID());
        book.setTitle(requestBookDto.getTitle());
        book.setAuthor(requestBookDto.getAuthor());
        book.setGenre(BookGenre.valueOf(requestBookDto.getGenre()));
        BookDto expectedBookDto = new BookDto();
        expectedBookDto.setId(book.getId());
        expectedBookDto.setTitle(book.getTitle());
        expectedBookDto.setAuthor(book.getAuthor());
        expectedBookDto.setGenre(book.getGenre().toString());

        when(modelMapper.map(requestBookDto, Book.class)).thenReturn(book);
        when(bookRepository.save(book)).thenReturn(book);
        when(modelMapper.map(book, BookDto.class)).thenReturn(expectedBookDto);

        // Act
        BookDto actualBookDto = bookService.save(requestBookDto);

        // Assert
        assertEquals(expectedBookDto, actualBookDto);
        verify(modelMapper, times(1)).map(requestBookDto, Book.class);
        verify(bookRepository, times(1)).save(book);
        verify(modelMapper, times(1)).map(book, BookDto.class);
    }
}
