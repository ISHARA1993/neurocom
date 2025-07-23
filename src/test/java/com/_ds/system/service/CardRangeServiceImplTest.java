package com._ds.system.service;

import com._ds.system.dto.Response;
import com._ds.system.model.CardRangeData;
import com._ds.system.repository.CardRangeRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CardRangeServiceImplTest {

    @Mock
    private CardRangeRepository repository;

    @InjectMocks
    private CardRangeServiceImpl service;

    private CardRangeData sampleRange;

    @BeforeEach
    void setup() {
        sampleRange = new CardRangeData(
                1L,
                4000020000000000L,
                4000020009999999L,
                "https://secure4.arcot.com"
        );
    }

    @Test
    void testStoreCardRangesSuccess() {
        List<CardRangeData> inputRanges = List.of(sampleRange);
        when(repository.saveAll(anyList())).thenReturn(inputRanges);
        boolean result = service.storeCardRanges(inputRanges);
        assertTrue(result, "Expected storeCardRanges() to return true when repository saves data");
    }

    @Test
    void testStoreCardRangesEmpty() {
        List<CardRangeData> inputRanges = List.of();
        when(repository.saveAll(anyList())).thenReturn(List.of());
        boolean result = service.storeCardRanges(inputRanges);
        assertFalse(result, "Expected storeCardRanges() to return false when repository returns empty");
    }

    @Test
    void testFindCardRangeForPanValid() {
        String validPan = "4000020000000123";
        when(repository.findByPanRange(anyLong())).thenReturn(Optional.of(sampleRange));
        Optional<Response> result = service.findCardRangeForPan(validPan);
        assertTrue(result.isPresent(), "Expected a Response for valid PAN");
        assertInstanceOf(CardRangeData.class, result.get().o());
        assertEquals("PAN :" + validPan, result.get().messageType());
    }

    @Test
    void testFindCardRangeForPanNotFound() {
        String pan = "4000999999999999";
        when(repository.findByPanRange(anyLong())).thenReturn(Optional.empty());
        Optional<Response> result = service.findCardRangeForPan(pan);
        assertTrue(result.isEmpty(), "Expected Optional.empty() when PAN not found");
    }

    @Test
    void testFindCardRangeForPanInvalid() {
        String invalidPan = "ABC123XYZ";
        Optional<Response> result = service.findCardRangeForPan(invalidPan);
        assertTrue(result.isEmpty(), "Expected Optional.empty() for invalid PAN (NumberFormatException)");
    }
}