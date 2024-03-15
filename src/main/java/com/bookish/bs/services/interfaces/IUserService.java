package com.bookish.bs.services.interfaces;

import com.bookish.bs.dtos.UserDto;
import com.bookish.bs.services.IService;

import java.util.UUID;

public interface IUserService extends IService<UserDto, UserDto, UUID> {
}
