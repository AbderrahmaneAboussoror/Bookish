package com.bookish.bs.services.impl;

import com.bookish.bs.dtos.ConversationDto;
import com.bookish.bs.dtos.UserDto;
import com.bookish.bs.entities.Conversation;
import com.bookish.bs.entities.User;
import com.bookish.bs.enums.Role;
import com.bookish.bs.exceptions.NotFoundException;
import com.bookish.bs.repositories.ConversationRepository;
import com.bookish.bs.services.interfaces.IConversationService;
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
public class ConversationService implements IConversationService {
    private final ConversationRepository conversationRepository;
    private final ModelMapper modelMapper;
    @Override
    public ConversationDto save(ConversationDto object) {
        Conversation conversation = modelMapper.map(object, Conversation.class);
        log.info("Saving new conversation {}", conversation.getId());
        return modelMapper.map(conversationRepository.save(conversation), ConversationDto.class);
    }

    @Override
    public ConversationDto update(UUID uuid, ConversationDto object) {
        return null;
    }

    @Override
    public boolean delete(UUID uuid) throws NotFoundException {
        log.info("Checking if the conversation exists");
        Conversation conversation = conversationRepository.findById(uuid)
                .orElseThrow(() -> new NotFoundException("Conversation not found"));

        log.info("Deleting conversation {}", conversation.getId());
        conversationRepository.delete(conversation);
        return true;
    }

    @Override
    public ConversationDto findById(UUID uuid) throws NotFoundException {
        log.info("Retrieving a conversation by id");
        Conversation conversation = conversationRepository.findById(uuid)
                .orElseThrow(() -> new NotFoundException("Conversation not found"));
        return modelMapper.map(conversation, ConversationDto.class);
    }

    @Override
    public Page<ConversationDto> findAll(Pageable pageable) {
        log.info("Retrieving all conversations");
        Page<Conversation> conversations = conversationRepository.findAll(pageable);

        return new PageImpl<>(
                conversations.getContent()
                        .stream()
                        .map(conversation -> modelMapper.map(conversation, ConversationDto.class))
                        .collect(Collectors.toList()),
                conversations.getPageable(),
                conversations.getTotalElements()
        );
    }
}
