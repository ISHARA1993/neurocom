package com._ds.system.controller;


import com._ds.system.dto.Response;
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

import java.util.List;
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
    void testStoreCardRangesSuccess() throws Exception {
        List<CardRangeData> cardRanges = List.of(
                new CardRangeData(null, 4000020000000000L, 4000020009999999L, "https://secure4.arcot.com")
        );

        // Mock service to return true
        when(service.storeCardRanges(anyList())).thenReturn(true);

        String jsonBody = """
        [
          {
            "startRange": 4000020000000000,
            "endRange": 4000020009999999,
            "threeDSMethodURL": "https://secure4.arcot.com"
          }
        ]
    """;

        mockMvc.perform(post("/3ds/card-ranges/store")
                        .contentType("application/json")
                        .content(jsonBody))
                .andExpect(status().isOk())
                .andExpect(content().string("Card ranges stored successfully in H2 DB!"));
    }

    @Test
    void testStoreCardRangesNoContent() throws Exception {
        // Mock service to return false
        when(service.storeCardRanges(anyList())).thenReturn(false);

        String jsonBody = "[]";

        mockMvc.perform(post("/3ds/card-ranges/store")
                        .contentType("application/json")
                        .content(jsonBody))
                .andExpect(status().isNoContent());
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

        Response mockResponse = new Response(mockRange, "PAN :" + pan);

        when(service.findCardRangeForPan(pan)).thenReturn(Optional.of(mockResponse));

        mockMvc.perform(get("/3ds/card-ranges/lookup/{pan}", pan))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.o.startRange").value(mockRange.getStartRange()))
                .andExpect(jsonPath("$.o.endRange").value(mockRange.getEndRange()))
                .andExpect(jsonPath("$.o.threeDSMethodURL").value(mockRange.getThreeDSMethodURL()))
                .andExpect(jsonPath("$.messageType").value("PAN :" + pan))
                .andDo(print());
    }

    @Test
    void testLookupCardRangeNotFound() throws Exception {
        String pan = "45000020000000000";
        when(service.findCardRangeForPan(pan)).thenReturn(Optional.empty());
        mockMvc.perform(get("/3ds/card-ranges/lookup/{pan}", pan))
                .andExpect(status().isNotFound())
                .andDo(print());
    }

    @Test
    void testLookupCardRangInvalidPan() throws Exception {
        String pan = "4500002000000000F";
        when(service.findCardRangeForPan(pan)).thenReturn(Optional.empty());
        mockMvc.perform(get("/3ds/card-ranges/lookup/{pan}", pan))
                .andExpect(status().isNotFound())
                .andDo(print());
    }


}