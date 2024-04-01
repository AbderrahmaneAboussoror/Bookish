package com.bookish.bs.services.impl;

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
import lombok.extern.slf4j.Slf4j;
import org.junit.Ignore;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@Slf4j
public class AdServiceTest {
    @InjectMocks
    private AdService adService;
    @Mock
    private AdRepository adRepository;
    @Mock
    private UserRepository userRepository;
    @Mock
    private ModelMapper modelMapper;
    @Test
    void saveAdTest() {
        // Arrange
        RequestAdDto requestAdDto = new RequestAdDto(null, "AVAILABLE", UUID.randomUUID());
        Ad ad = new Ad();
        ad.setState(AdState.AVAILABLE);
        User user = new User();
        user.setId(requestAdDto.getUserId());
        AdDto expectedAdDto = new AdDto();

        when(userRepository.findById(requestAdDto.getUserId())).thenReturn(Optional.of(user));
        when(modelMapper.map(requestAdDto, Ad.class)).thenReturn(ad);
        when(adRepository.save(ad)).thenReturn(ad);
        when(modelMapper.map(ad, AdDto.class)).thenReturn(expectedAdDto);

        // Act
        AdDto actualAdDto = adService.save(requestAdDto);

        // Assert
        assertEquals(expectedAdDto, actualAdDto);
        verify(userRepository, times(1)).findById(requestAdDto.getUserId());
        verify(modelMapper, times(1)).map(requestAdDto, Ad.class);
        verify(adRepository, times(1)).save(ad);
        verify(modelMapper, times(1)).map(ad, AdDto.class);
    }

    @Test
    void updateTest_ExistingAd() throws NotFoundException {
        // Arrange
        UUID adId = UUID.randomUUID();
        RequestAdDto requestAdDto = new RequestAdDto(adId, "AVAILABLE", UUID.randomUUID());
        Ad existingAd = new Ad();
        existingAd.setId(adId);
        existingAd.setState(AdState.INACTIVE);
        AdDto expectedAdDto = new AdDto();
        expectedAdDto.setId(adId);
        expectedAdDto.setState(AdState.AVAILABLE);

        when(adRepository.findById(adId)).thenReturn(Optional.of(existingAd));
        when(adRepository.save(any(Ad.class))).thenAnswer(invocation -> {
            Ad updatedAd = invocation.getArgument(0);
            updatedAd.setId(adId);
            updatedAd.setState(AdState.AVAILABLE);
            return updatedAd;
        });
        when(modelMapper.map(any(Ad.class), eq(AdDto.class))).thenReturn(expectedAdDto);

        // Act
        AdDto updatedAdDto = adService.update(adId, requestAdDto);

        // Assert
        assertEquals(expectedAdDto.getState(), updatedAdDto.getState());
        verify(adRepository, times(1)).findById(adId);
        verify(adRepository, times(1)).save(any(Ad.class));
        verify(modelMapper, times(1)).map(any(Ad.class), eq(AdDto.class));
    }

    @Test
    void updateTest_NonExistingAd() {
        // Arrange
        UUID adId = UUID.randomUUID();
        RequestAdDto requestAdDto = new RequestAdDto(adId, "AVAILABLE", UUID.randomUUID());

        when(adRepository.findById(adId)).thenReturn(Optional.empty());

        // Act & Assert
        assertThrows(NotFoundException.class, () -> adService.update(adId, requestAdDto));
        verify(adRepository, times(1)).findById(adId);
        verifyNoMoreInteractions(adRepository);
    }

    @Test
    void deleteTest_ExistingAd() throws NotFoundException {
        // Arrange
        UUID adId = UUID.randomUUID();
        Ad existingAd = new Ad();
        existingAd.setId(adId);

        when(adRepository.findById(adId)).thenReturn(Optional.of(existingAd));

        // Act
        boolean result = adService.delete(adId);

        // Assert
        assertTrue(result);
        verify(adRepository, times(1)).findById(adId);
        verify(adRepository, times(1)).delete(existingAd);
    }

    @Test
    void deleteTest_NonExistingAd() {
        // Arrange
        UUID adId = UUID.randomUUID();

        when(adRepository.findById(adId)).thenReturn(Optional.empty());

        // Act & Assert
        assertThrows(NotFoundException.class, () -> adService.delete(adId));
        verify(adRepository, times(1)).findById(adId);
        verifyNoMoreInteractions(adRepository);
    }

    @Test
    void findByIdTest_ExistingAd() throws NotFoundException {
        // Arrange
        UUID adId = UUID.randomUUID();
        Ad existingAd = new Ad();
        existingAd.setId(adId);
        AdDto expectedAdDto = new AdDto();

        when(adRepository.findById(adId)).thenReturn(Optional.of(existingAd));
        when(modelMapper.map(existingAd, AdDto.class)).thenReturn(expectedAdDto);

        // Act
        AdDto actualAdDto = adService.findById(adId);

        // Assert
        assertEquals(expectedAdDto, actualAdDto);
        verify(adRepository, times(1)).findById(adId);
        verify(modelMapper, times(1)).map(existingAd, AdDto.class);
    }

    @Test
    void findByIdTest_NonExistingAd() {
        // Arrange
        UUID adId = UUID.randomUUID();

        when(adRepository.findById(adId)).thenReturn(Optional.empty());

        // Act & Assert
        assertThrows(NotFoundException.class, () -> adService.findById(adId));
        verify(adRepository, times(1)).findById(adId);
        verifyNoMoreInteractions(modelMapper);
    }

    @Test
    void findAllTest() {
        // Arrange
        Pageable pageable = PageRequest.of(0, 10);
        List<Ad> adList = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            Ad ad = new Ad();
            ad.setId(UUID.randomUUID());
            adList.add(ad);
        }
        Page<Ad> adPage = new PageImpl<>(adList, pageable, adList.size());

        when(adRepository.findAll(pageable)).thenReturn(adPage);
        when(modelMapper.map(any(Ad.class), eq(AdDto.class))).thenAnswer(invocation -> {
            Ad ad = invocation.getArgument(0);
            AdDto adDto = new AdDto();
            adDto.setId(ad.getId());
            return adDto;
        });

        // Act
        Page<AdDto> actualAdDtoPage = adService.findAll(pageable);

        // Assert
        assertEquals(adPage.getContent().size(), actualAdDtoPage.getContent().size());
        assertEquals(adPage.getTotalElements(), actualAdDtoPage.getTotalElements());
        assertEquals(adPage.getPageable(), actualAdDtoPage.getPageable());
        verify(adRepository, times(1)).findAll(pageable);
        verify(modelMapper, times(adList.size())).map(any(Ad.class), eq(AdDto.class));
    }
}