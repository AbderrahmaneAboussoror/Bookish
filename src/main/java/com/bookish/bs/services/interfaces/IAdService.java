package com.bookish.bs.services.interfaces;

import com.bookish.bs.dtos.ad.AdDto;
import com.bookish.bs.dtos.ad.RequestAdDto;
import com.bookish.bs.services.IService;

import java.util.UUID;

public interface IAdService extends IService<AdDto, RequestAdDto, UUID> {
}