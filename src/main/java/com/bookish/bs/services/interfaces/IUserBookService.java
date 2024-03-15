package com.bookish.bs.services.interfaces;

import com.bookish.bs.dtos.userBook.RequestUserBookDto;
import com.bookish.bs.dtos.userBook.UserBookDto;
import com.bookish.bs.keys.UserBookId;
import com.bookish.bs.services.IService;

public interface IUserBookService extends IService<UserBookDto, RequestUserBookDto, UserBookId> {
}
