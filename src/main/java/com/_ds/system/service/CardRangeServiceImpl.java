package com._ds.system.service;

import com._ds.system.dto.Response;
import com._ds.system.model.CardRangeData;
import com._ds.system.repository.CardRangeRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CardRangeServiceImpl implements CardRangeService{

    private final CardRangeRepository repository;

    public CardRangeServiceImpl(CardRangeRepository repository) {
        this.repository = repository;
    }

    public boolean storeCardRanges(List<CardRangeData> ranges) {
        List<CardRangeData> cardRangeDataList = repository.saveAll(ranges);
        return !cardRangeDataList.isEmpty();
    }

    public Optional<Response> findCardRangeForPan(String pan) {
        try {
            long panNumber = Long.parseLong(pan);
            Optional<CardRangeData> byPanRange = repository.findByPanRange(panNumber);
            return byPanRange.map(cardRangeData -> new Response(cardRangeData, "PAN :" + pan));
        } catch (NumberFormatException e) {
            return Optional.empty();
        }
    }
}