package com.bookish.bs.services.interfaces;

import com.bookish.bs.dtos.ConversationDto;
import com.bookish.bs.services.IService;

import java.util.UUID;

public interface IConversationService extends IService<ConversationDto, ConversationDto, UUID> {
}
