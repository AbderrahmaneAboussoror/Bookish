package com.bookish.bs.services.interfaces;

import com.bookish.bs.dtos.message.MessageDto;
import com.bookish.bs.dtos.message.RequestMessageDto;
import com.bookish.bs.keys.MessageId;
import com.bookish.bs.services.IService;

public interface IMessageService extends IService<MessageDto, RequestMessageDto, MessageId> {
}
