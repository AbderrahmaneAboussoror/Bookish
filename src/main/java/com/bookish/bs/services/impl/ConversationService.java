package com.bookish.bs.services.impl;

import com.bookish.bs.dtos.ConversationDto;
import com.bookish.bs.services.interfaces.IConversationService;
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
public class ConversationService implements IConversationService {
    @Override
    public ConversationDto save(ConversationDto object) {
        return null;
    }

    @Override
    public ConversationDto update(UUID uuid, ConversationDto object) {
        return null;
    }

    @Override
    public ConversationDto findById(UUID uuid) {
        return null;
    }

    @Override
    public List<ConversationDto> findAll() {
        return null;
    }
}
