package com.bookish.bs.services.impl;

import com.bookish.bs.dtos.UserDto;
import com.bookish.bs.entities.User;
import com.bookish.bs.enums.Role;
import com.bookish.bs.exceptions.NotFoundException;
import com.bookish.bs.repositories.UserRepository;
import com.bookish.bs.services.interfaces.IUserService;
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
public class UserService implements IUserService {
    private final UserRepository userRepository;
    private final ModelMapper modelMapper;
    @Override
    public UserDto save(UserDto object) {
        User user = modelMapper.map(object, User.class);
        user.setRole(Role.valueOf(object.getRole()));
        log.info("Saving new user {}", user.getFirstName());
        return modelMapper.map(userRepository.save(user), UserDto.class);
    }

    @Override
    public UserDto update(UUID uuid, UserDto object) throws NotFoundException {
        log.info("Checking if the user exists");
        User user = userRepository.findById(uuid)
                .orElseThrow(() -> new NotFoundException("User not found"));

        user.setFirstName(object.getFirstName());
        user.setLastName(object.getLastName());
        user.setUsername(object.getUsername());
        user.setRole(Role.valueOf(object.getRole()));

        log.info("Updating user {}", user.getFirstName());
        return modelMapper.map(userRepository.save(user), UserDto.class);
    }

    @Override
    public boolean delete(UUID uuid) throws NotFoundException {
        log.info("Checking if the user exists");
        User user = userRepository.findById(uuid)
                .orElseThrow(() -> new NotFoundException("User not found"));

        log.info("Deleting user {}", user.getFirstName());
        userRepository.delete(user);
        return true;
    }

    @Override
    public UserDto findById(UUID uuid) throws NotFoundException {
        log.info("Retrieving a user by id");
        User user = userRepository.findById(uuid)
                .orElseThrow(() -> new NotFoundException("User not found"));
        return modelMapper.map(user, UserDto.class);
    }

    @Override
    public Page<UserDto> findAll(Pageable pageable) {
        log.info("Retrieving all users");
        Page<User> users = userRepository.findAll(pageable);

        return new PageImpl<>(
                users.getContent()
                        .stream()
                        .map(user -> modelMapper.map(user, UserDto.class))
                        .collect(Collectors.toList()),
                users.getPageable(),
                users.getTotalElements()
        );
    }
}
