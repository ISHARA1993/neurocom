package com._ds.system.dto;

public record Response(String serialNum, String messageType, String dsTransID, String messageVersion,
                       String dsEndProtocolVersion, String dsStartProtocolVersion, String threeDSServerTransID ,
                        Object o) {

    public Response(Object o, String messageType) {
        this("5780074", messageType, "9f05c4e2-e3a2-4127-b3f6-e9957a2c597d", "2.2.0",
                "2.2.0", "2.1.0", "12677a32-97fb-4c0d-a67b-44f2e5bcc172", o);
    }
}
