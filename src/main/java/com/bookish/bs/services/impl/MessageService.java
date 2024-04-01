package com.bookish.bs.services.impl;

import com.bookish.bs.dtos.message.MessageDto;
import com.bookish.bs.dtos.message.RequestMessageDto;
import com.bookish.bs.entities.Conversation;
import com.bookish.bs.entities.Message;
import com.bookish.bs.entities.User;
import com.bookish.bs.exceptions.InvalidDataException;
import com.bookish.bs.exceptions.NotFoundException;
import com.bookish.bs.keys.MessageId;
import com.bookish.bs.repositories.ConversationRepository;
import com.bookish.bs.repositories.MessageRepository;
import com.bookish.bs.repositories.UserRepository;
import com.bookish.bs.services.interfaces.IMessageService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@Transactional
@Slf4j
@RequiredArgsConstructor
public class MessageService implements IMessageService {
    private final ModelMapper modelMapper;
    private final MessageRepository messageRepository;
    private final UserRepository userRepository;
    private final ConversationRepository conversationRepository;
    @Override
    public MessageDto save(RequestMessageDto object) {
        log.info("Checking if the user exists");
        User user = userRepository.findById(object.getUserId())
                .orElseThrow(() -> new InvalidDataException("User id doesn't exist"));

        log.info("Checking if the conversation exists");
        Conversation conversation = conversationRepository.findById(object.getConversationId())
                .orElseThrow(() -> new InvalidDataException("Conversation id doesn't exist"));

        MessageId messageId = new MessageId(object.getUserId(), object.getConversationId());

        Message message = new Message();
        message.setId(messageId);
        message.setUser(user);
        message.setConversation(conversation);
        message.setContent(object.getContent());

        log.info("Saving new message");
        return modelMapper.map(messageRepository.save(message), MessageDto.class);
    }

    @Override
    public MessageDto update(MessageId messageId, RequestMessageDto object) throws NotFoundException {
        log.info("Checking if the message exists");
        Message message = messageRepository.findById(messageId)
                .orElseThrow(() -> new NotFoundException("Message id doesn't exist"));

        log.info("Updating the message content");
        message.setContent(object.getContent());
        return modelMapper.map(message, MessageDto.class);
    }

    @Override
    public boolean delete(MessageId messageId) throws NotFoundException {
        log.info("Checking if the message exists");
        Message message = messageRepository.findById(messageId)
                .orElseThrow(() -> new NotFoundException("Message id doesn't exist"));

        log.info("Deleting message {}", message.getId());
        messageRepository.delete(message);
        return true;
    }

    @Override
    public MessageDto findById(MessageId messageId) {
        return null;
    }

    @Override
    public Page<MessageDto> findAll(Pageable pageable) {
        return null;
    }

    @Override
    public List<MessageDto> findMessagesByUser(UUID uuid) throws NotFoundException {
        log.info("Checking if the user exists");
        userRepository.findById(uuid)
                .orElseThrow(() -> new NotFoundException("User id doesn't exist"));

        log.info("Retrieving all messages");
        return List.of(modelMapper.map(messageRepository.findMessagesById_UserId(uuid), MessageDto[].class));
    }

    @Override
    public List<MessageDto> findMessagesByConversation(UUID uuid) throws NotFoundException {
        log.info("Checking if the conversation exists");
        conversationRepository.findById(uuid)
                .orElseThrow(() -> new NotFoundException("Conversation id doesn't exist"));

        log.info("Retrieving all messages");
        return List.of(modelMapper.map(messageRepository.findMessagesById_ConversationId(uuid), MessageDto[].class));
    }
}
