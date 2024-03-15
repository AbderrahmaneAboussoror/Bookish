package com.bookish.bs.services.interfaces;

import com.bookish.bs.dtos.bookAd.BookAdDto;
import com.bookish.bs.dtos.bookAd.RequestBookAdDto;
import com.bookish.bs.keys.BookAdId;
import com.bookish.bs.services.IService;

public interface IBookAdService extends IService<BookAdDto, RequestBookAdDto, BookAdId> {
}
