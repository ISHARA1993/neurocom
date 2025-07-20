package com._ds.system.service;

import com._ds.system.model.CardRangeData;
import com._ds.system.repository.CardRangeRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CardRangeServiceImpl implements CardRangeService {

    private final CardRangeRepository repository;

    public CardRangeServiceImpl(CardRangeRepository repository) {
        this.repository = repository;
    }

    public void storeCardRanges(List<CardRangeData> ranges) {
        repository.saveAll(ranges);
    }

    public Optional<CardRangeData> findCardRangeForPan(String pan) {
        try {
            Long panNumber = Long.parseLong(pan);
            //add query to repository
            CardRangeData result = null;
            return Optional.ofNullable(result);
        } catch (NumberFormatException e) {
            return Optional.empty();
        }
    }
}