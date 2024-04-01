package com.bookish.bs.services.impl;

import com.bookish.bs.dtos.review.RequestReviewDto;
import com.bookish.bs.dtos.review.ReviewDto;
import com.bookish.bs.entities.Review;
import com.bookish.bs.entities.User;
import com.bookish.bs.exceptions.InvalidDataException;
import com.bookish.bs.exceptions.NotFoundException;
import com.bookish.bs.repositories.ReviewRepository;
import com.bookish.bs.repositories.UserRepository;
import com.bookish.bs.services.interfaces.IReviewService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@Transactional
@Slf4j
@RequiredArgsConstructor
public class ReviewService implements IReviewService {
    private final ModelMapper modelMapper;
    private final ReviewRepository reviewRepository;
    private final UserRepository userRepository;

    @Override
    public ReviewDto save(RequestReviewDto object) {
        Review review  = modelMapper.map(object, Review.class);

        log.info("Fetching the user with the code {}", object.getUserId());
        Optional<User> userOptional = userRepository.findById(object.getUserId());
        review.setUser(
                userOptional.orElseThrow(
                        () -> new InvalidDataException("Invalid user code")
                )
        );

        log.info("Saving new review {}", review.getId());
        return modelMapper.map(reviewRepository.save(review), ReviewDto.class);
    }

    @Override
    public ReviewDto update(UUID uuid, RequestReviewDto object) throws NotFoundException {
        log.info("Checking if the review exists");
        Review review = reviewRepository.findById(uuid)
                .orElseThrow(() -> new NotFoundException("Review not found"));

        review.setComment(object.getComment());
        review.setNote(object.getNote());
        log.info("Updating review {}", review.getId());
        return modelMapper.map(reviewRepository.save(review), ReviewDto.class);
    }

    @Override
    public boolean delete(UUID uuid) throws NotFoundException {
        log.info("Checking if the review exists");
        Review review = reviewRepository.findById(uuid)
                .orElseThrow(() -> new NotFoundException("Review not found"));

        log.info("Deleting review {}", review.getId());
        reviewRepository.delete(review);
        return true;
    }

    @Override
    public ReviewDto findById(UUID uuid) throws NotFoundException {
        log.info("Retrieving a review by id");
        Review review = reviewRepository.findById(uuid)
                .orElseThrow(() -> new NotFoundException("Review not found"));
        return modelMapper.map(review, ReviewDto.class);
    }

    @Override
    public Page<ReviewDto> findAll(Pageable pageable) {
        log.info("Retrieving all reviews");
        Page<Review> reviews = reviewRepository.findAll(pageable);

        return new PageImpl<>(
                reviews.getContent()
                        .stream()
                        .map(review -> modelMapper.map(review, ReviewDto.class))
                        .collect(Collectors.toList()),
                reviews.getPageable(),
                reviews.getTotalElements()
        );
    }
}
