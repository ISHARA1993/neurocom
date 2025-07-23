package com._ds.system.service;

import com._ds.system.dto.Response;
import com._ds.system.model.CardRangeData;

import java.util.List;
import java.util.Optional;

public interface CardRangeService {

    boolean storeCardRanges(List<CardRangeData> cardRanges);
    Optional<Response> findCardRangeForPan(String pan);
}
