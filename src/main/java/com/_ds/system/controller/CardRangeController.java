package com._ds.system.controller;

import com._ds.system.dto.CardRangeLookupResponse;
import com._ds.system.model.CardRangeData;
import com._ds.system.service.CardRangeService;
import com._ds.system.service.CardRangeServiceImpl;
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
    public ResponseEntity<String> storeCardRanges(@RequestBody List<CardRangeData> cardRanges) {
        service.storeCardRanges(cardRanges);
        return ResponseEntity.ok("Card ranges stored successfully in H2 DB!");
    }

    @GetMapping("/lookup/{pan}")
    public ResponseEntity<?> lookupCardRange(@PathVariable String pan) {


        Optional<CardRangeData> response= service.findCardRangeForPan(pan);

        if(response.isPresent())
        {
            return ResponseEntity.ok(new CardRangeLookupResponse(pan, response.get().getStartRange(), response.get().getEndRange(), response.get().getThreeDSMethodURL(), "PAN found"));
        }
        return ResponseEntity.notFound().build();

//        return service.findCardRangeForPan(pan)
//                .map(range -> {
//                    return ResponseEntity.ok(new CardRangeLookupResponse(pan, range.getStartRange(), range.getEndRange(), range.getThreeDSMethodURL(), "PAN found"));
//                })
//                .orElseGet(() -> ResponseEntity.ok(new CardRangeLookupResponse("No PAN found")));
    }
}