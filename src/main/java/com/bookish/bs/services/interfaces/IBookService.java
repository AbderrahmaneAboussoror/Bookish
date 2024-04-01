package com.bookish.bs.services.interfaces;

import com.bookish.bs.dtos.BookDto;
import com.bookish.bs.exceptions.NotFoundException;
import com.bookish.bs.services.IService;

import java.util.List;
import java.util.UUID;

public interface IBookService extends IService<BookDto, BookDto, UUID> {
    BookDto findByTitle(String title) throws NotFoundException;
    List<BookDto> findByGenre(String genre);
    List<BookDto> findByAuthor(String author);
}
