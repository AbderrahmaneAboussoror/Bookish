package com.bookish.bs.services.impl;

import com.bookish.bs.dtos.UserDto;
import com.bookish.bs.dtos.ad.AdDto;
import com.bookish.bs.dtos.ad.RequestAdDto;
import com.bookish.bs.entities.Ad;
import com.bookish.bs.entities.User;
import com.bookish.bs.enums.AdState;
import com.bookish.bs.exceptions.InvalidDataException;
import com.bookish.bs.exceptions.NotFoundException;
import com.bookish.bs.repositories.AdRepository;
import com.bookish.bs.repositories.UserRepository;
import com.bookish.bs.services.interfaces.IAdService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@Transactional
@Slf4j
@RequiredArgsConstructor
public class AdService implements IAdService {
    private final ModelMapper modelMapper;
    private final AdRepository adRepository;
    private final UserRepository userRepository;
    @Override
    public AdDto save(RequestAdDto object) {
        Ad ad = modelMapper.map(object, Ad.class);
        ad.setState(AdState.valueOf(object.getState()));

        log.info("Fetching the user with the code {}", object.getUserId());
        Optional<User> userOptional = userRepository.findById(object.getUserId());
        User user = userOptional.orElseThrow(
                () -> new InvalidDataException("Invalid user code")
        );
        ad.setUser(user);

        log.info("Saving new ad {}", ad.getId());
        return modelMapper.map(adRepository.save(ad), AdDto.class);
    }

    @Override
    public AdDto update(UUID uuid, RequestAdDto object) throws NotFoundException {
        log.info("Checking if the ad exists");
        Ad ad = adRepository.findById(uuid)
                .orElseThrow(() -> new NotFoundException("Ad not found"));

        ad.setState(AdState.valueOf(object.getState()));
        log.info("Updating ad {}", ad.getId());
        return modelMapper.map(adRepository.save(ad), AdDto.class);
    }

    @Override
    public boolean delete(UUID uuid) throws NotFoundException {
        log.info("Checking if the ad exists");
        Ad ad = adRepository.findById(uuid)
                .orElseThrow(() -> new NotFoundException("Ad not found"));

        log.info("Deleting ad {}", ad.getId());
        adRepository.delete(ad);
        return true;
    }

    @Override
    public AdDto findById(UUID uuid) throws NotFoundException {
        log.info("Retrieving a ad by id");
        Ad ad = adRepository.findById(uuid)
                .orElseThrow(() -> new NotFoundException("Ad not found"));
        return modelMapper.map(ad, AdDto.class);
    }

    @Override
    public Page<AdDto> findAll(Pageable pageable) {
        log.info("Retrieving all ads");
        Page<Ad> ads = adRepository.findAll(pageable);

        return new PageImpl<>(
            ads.getContent()
                    .stream()
                    .map(ad -> modelMapper.map(ad, AdDto.class))
                    .collect(Collectors.toList()),
                ads.getPageable(),
                ads.getTotalElements()
        );
    }
}
