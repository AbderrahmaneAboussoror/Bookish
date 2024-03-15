package com.bookish.bs.services.interfaces;

import com.bookish.bs.dtos.BookDto;
import com.bookish.bs.services.IService;

import java.util.UUID;

public interface IBookService extends IService<BookDto, BookDto, UUID> {
}
