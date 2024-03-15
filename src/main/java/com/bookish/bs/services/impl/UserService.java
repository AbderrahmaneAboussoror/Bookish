package com.bookish.bs.services.impl;

import com.bookish.bs.dtos.UserDto;
import com.bookish.bs.services.interfaces.IUserService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@Transactional
@Slf4j
@RequiredArgsConstructor
public class UserService implements IUserService {
    @Override
    public UserDto save(UserDto object) {
        return null;
    }

    @Override
    public UserDto update(UUID uuid, UserDto object) {
        return null;
    }

    @Override
    public UserDto findById(UUID uuid) {
        return null;
    }

    @Override
    public List<UserDto> findAll() {
        return null;
    }
}
