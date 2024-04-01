package com.bookish.bs.services.interfaces;

import com.bookish.bs.dtos.message.MessageDto;
import com.bookish.bs.dtos.userBook.RequestUserBookDto;
import com.bookish.bs.dtos.userBook.UserBookDto;
import com.bookish.bs.exceptions.NotFoundException;
import com.bookish.bs.keys.UserBookId;
import com.bookish.bs.services.IService;

import java.util.List;
import java.util.UUID;

public interface IUserBookService extends IService<UserBookDto, RequestUserBookDto, UserBookId> {
    List<UserBookDto> findUserBooksByUser(UUID uuid) throws NotFoundException;
}
