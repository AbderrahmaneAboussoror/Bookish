package com.bookish.bs.services.impl;

import com.bookish.bs.dtos.ad.AdDto;
import com.bookish.bs.dtos.ad.RequestAdDto;
import com.bookish.bs.services.interfaces.IAdService;
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
public class AdService implements IAdService {
    @Override
    public AdDto save(RequestAdDto object) {
        return null;
    }

    @Override
    public AdDto update(UUID uuid, RequestAdDto object) {
        return null;
    }

    @Override
    public AdDto findById(UUID uuid) {
        return null;
    }

    @Override
    public List<AdDto> findAll() {
        return null;
    }
}
