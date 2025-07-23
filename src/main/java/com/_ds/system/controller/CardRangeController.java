package com._ds.system.controller;

import com._ds.system.dto.CardRangeLookupResponse;
import com._ds.system.dto.Response;
import com._ds.system.model.CardRangeData;
import com._ds.system.service.CardRangeService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/3ds/card-ranges")
public class CardRangeController {

    private final CardRangeService service;

    public CardRangeController(CardRangeService service) {
        this.service = service;
    }

    @PostMapping("/store")
    public ResponseEntity<?> storeCardRanges(@RequestBody List<CardRangeData> cardRanges) {
        boolean isSuccess = service.storeCardRanges(cardRanges);
        return isSuccess ? ResponseEntity.ok("Card ranges stored successfully in H2 DB!") : ResponseEntity.noContent().build();
    }

    @GetMapping("/lookup/{pan}")
    public ResponseEntity<Response> lookupCardRange(@PathVariable String pan) {
        Optional<Response> response = service.findCardRangeForPan(pan);
        if (response.isPresent()) {
            if (response.get().o() instanceof CardRangeData) {
                return ResponseEntity.ok(response.get());
            }
        }
        return ResponseEntity.notFound().build();
    }
}