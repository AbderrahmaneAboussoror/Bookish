package com.bookish.bs.services.impl;

import com.bookish.bs.dtos.review.RequestReviewDto;
import com.bookish.bs.dtos.review.ReviewDto;
import com.bookish.bs.services.interfaces.IReviewService;
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
public class ReviewService implements IReviewService {
    @Override
    public ReviewDto save(RequestReviewDto object) {
        return null;
    }

    @Override
    public ReviewDto update(UUID uuid, RequestReviewDto object) {
        return null;
    }

    @Override
    public ReviewDto findById(UUID uuid) {
        return null;
    }

    @Override
    public List<ReviewDto> findAll() {
        return null;
    }
}
