package com.bookish.bs.services.impl;

import com.bookish.bs.dtos.notification.NotificationDto;
import com.bookish.bs.dtos.notification.RequestNotificationDto;
import com.bookish.bs.exceptions.NotFoundException;
import com.bookish.bs.services.interfaces.INotificationService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@Transactional
@Slf4j
@RequiredArgsConstructor
public class NotificationService implements INotificationService {
    @Override
    public NotificationDto save(RequestNotificationDto object) {
        return null;
    }

    @Override
    public NotificationDto update(UUID uuid, RequestNotificationDto object) {
        return null;
    }

    @Override
    public boolean delete(UUID uuid) throws NotFoundException {
        return false;
    }

    @Override
    public NotificationDto findById(UUID uuid) {
        return null;
    }

    @Override
    public Page<NotificationDto> findAll(Pageable pageable) {
        return null;
    }
}
