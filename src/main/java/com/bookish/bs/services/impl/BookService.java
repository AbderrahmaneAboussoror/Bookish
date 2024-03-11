package com.bookish.bs.services.impl;

import com.bookish.bs.services.interfaces.IBookAdService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Transactional
@Slf4j
@RequiredArgsConstructor
public class BookService implements IBookAdService {
}
