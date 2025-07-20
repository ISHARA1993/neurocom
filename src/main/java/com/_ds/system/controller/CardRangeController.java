package com._ds.system.controller;

import com._ds.system.dto.CardRangeLookupResponse;
import com._ds.system.model.CardRangeData;
import com._ds.system.service.CardRangeService;
import com._ds.system.service.CardRangeServiceImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/3ds/card-ranges")
public class CardRangeController {

    private final CardRangeService service;

    public CardRangeController(CardRangeServiceImpl service) {
        this.service = service;
    }

    @PostMapping("/store")
    public ResponseEntity<String> storeCardRanges(@RequestBody List<CardRangeData> cardRanges) {
        service.storeCardRanges(cardRanges);
        return ResponseEntity.ok("Card ranges stored successfully in H2 DB!");
    }

    @GetMapping("/lookup/{pan}")
    public ResponseEntity<CardRangeLookupResponse> lookupCardRange(@PathVariable String pan) {


        return service.findCardRangeForPan(pan)
                .map(range -> {
                    //  var response = new CardRangeLookupResponse(pan,range.getStartRange(), range.getEndRange(), range.getThreeDSMethodURL(),"PAN found");
                    return ResponseEntity.ok(new CardRangeLookupResponse(pan, range.getStartRange(), range.getEndRange(), range.getThreeDSMethodURL(), "PAN found"));
                })
                .orElseGet(() -> ResponseEntity.ok(new CardRangeLookupResponse("No PAN found")));
    }
}