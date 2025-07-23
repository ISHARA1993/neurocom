package com._ds.system.dto;


public record CardRangeLookupResponse(String pan, Long startRange, Long endRange, String threeDSMethodURL,
                                      String message) {


    public CardRangeLookupResponse(String message) {
        this(null, null, null, null, message);
    }
}
