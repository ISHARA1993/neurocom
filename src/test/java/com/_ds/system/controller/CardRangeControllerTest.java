package com._ds.system.controller;


import com._ds.system.model.CardRangeData;
import com._ds.system.service.CardRangeServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Optional;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

@ExtendWith(MockitoExtension.class)
class CardRangeControllerTest {

    private MockMvc mockMvc;

    @Mock
    private CardRangeServiceImpl service;

    @InjectMocks
    private CardRangeController cardRangeController;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(new CardRangeController(service)).build();
    }

    @Test
    void testLookupCardRangeFound() throws Exception {
        String pan = "4000020000000000";
        CardRangeData mockRange = new CardRangeData(
                1L,
                4000020000000000L,
                4000020009999999L,
                "https://secure4.arcot.com"
        );

        when(service.findCardRangeForPan(pan)).thenReturn(Optional.of(mockRange));

        mockMvc.perform(get("/3ds/card-ranges/lookup/{pan}", pan))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.pan").value(pan))  // ✅ now matches
                .andExpect(jsonPath("$.startRange").value(mockRange.getStartRange()))
                .andExpect(jsonPath("$.endRange").value(mockRange.getEndRange()))
                .andExpect(jsonPath("$.threeDSMethodURL").value(mockRange.getThreeDSMethodURL()))
                .andExpect(jsonPath("$.message").value("PAN found"))
                .andDo(print());
    }


}