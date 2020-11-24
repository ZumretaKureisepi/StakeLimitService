package com.stakelimitservicetask.stakelimitservice.enums;

import java.util.HashMap;
import java.util.Map;

public enum TicketMessageStatus {
    OK,
    HOT,
    BLOCKED;

    private static Map<Integer, TicketMessageStatus> intToEnumValuesMapping = new HashMap<>();

    static {
        for (TicketMessageStatus ticketMessageStatus : TicketMessageStatus.values()) {
            intToEnumValuesMapping.put(
                    ticketMessageStatus.ordinal(),
                    ticketMessageStatus
            );
        }
    }

    public static TicketMessageStatus castIntToEnum(int numericaal) {
        return intToEnumValuesMapping.get(numericaal);
    }
}
