package com._ds.system.service;

import com._ds.system.model.CardRangeData;

import java.util.List;
import java.util.Optional;

public interface CardRangeService {
    void storeCardRanges(List<CardRangeData> cardRanges);

    Optional<CardRangeData> findCardRangeForPan(String pan);
}
