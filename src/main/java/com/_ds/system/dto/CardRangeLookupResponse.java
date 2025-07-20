package com._ds.system.dto;


public record CardRangeLookupResponse(String pan, Long startRange, Long endRange, String threeDSMethodURL,
                                      String message) {


    public CardRangeLookupResponse(String message) {
        this("", 0L, 0L, "", message);
    }
}
