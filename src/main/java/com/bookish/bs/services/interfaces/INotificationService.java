package com.bookish.bs.services.interfaces;

import com.bookish.bs.dtos.notification.NotificationDto;
import com.bookish.bs.dtos.notification.RequestNotificationDto;
import com.bookish.bs.services.IService;

import java.util.UUID;

public interface INotificationService extends IService<NotificationDto, RequestNotificationDto, UUID> {
}
