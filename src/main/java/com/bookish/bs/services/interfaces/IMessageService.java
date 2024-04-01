package com.bookish.bs.services.interfaces;

import com.bookish.bs.dtos.message.MessageDto;
import com.bookish.bs.dtos.message.RequestMessageDto;
import com.bookish.bs.exceptions.NotFoundException;
import com.bookish.bs.keys.MessageId;
import com.bookish.bs.services.IService;

import java.util.List;
import java.util.UUID;

public interface IMessageService extends IService<MessageDto, RequestMessageDto, MessageId> {
    List<MessageDto> findMessagesByUser(UUID uuid) throws NotFoundException;
    List<MessageDto> findMessagesByConversation(UUID uuid) throws NotFoundException;
}
