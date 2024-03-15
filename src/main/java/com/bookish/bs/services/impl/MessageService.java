package com.bookish.bs.services.impl;

import com.bookish.bs.dtos.message.MessageDto;
import com.bookish.bs.dtos.message.RequestMessageDto;
import com.bookish.bs.keys.MessageId;
import com.bookish.bs.services.interfaces.IMessageService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
@Slf4j
@RequiredArgsConstructor
public class MessageService implements IMessageService {
    @Override
    public MessageDto save(RequestMessageDto object) {
        return null;
    }

    @Override
    public MessageDto update(MessageId messageId, RequestMessageDto object) {
        return null;
    }

    @Override
    public MessageDto findById(MessageId messageId) {
        return null;
    }

    @Override
    public List<MessageDto> findAll() {
        return null;
    }
}
