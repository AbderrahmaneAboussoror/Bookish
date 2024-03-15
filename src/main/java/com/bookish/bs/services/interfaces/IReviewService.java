package com.bookish.bs.services.interfaces;

import com.bookish.bs.dtos.review.RequestReviewDto;
import com.bookish.bs.dtos.review.ReviewDto;
import com.bookish.bs.services.IService;

import java.util.UUID;

public interface IReviewService extends IService<ReviewDto, RequestReviewDto, UUID> {
}
