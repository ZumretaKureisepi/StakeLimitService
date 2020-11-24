package com.stakelimitservicetask.stakelimitservice.dto;

import com.stakelimitservicetask.stakelimitservice.enums.TicketMessageStatus;

public class AddTicketMessageResponse {
    TicketMessageStatus status;

    public AddTicketMessageResponse(TicketMessageStatus status) {
        this.status = status;
    }

    public TicketMessageStatus getStatus() {
        return status;
    }
}
