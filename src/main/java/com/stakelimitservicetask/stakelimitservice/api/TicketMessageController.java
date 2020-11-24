package com.stakelimitservicetask.stakelimitservice.api;

import com.stakelimitservicetask.stakelimitservice.dto.AddTicketMessageResponse;
import com.stakelimitservicetask.stakelimitservice.dto.TicketMessagedto;
import com.stakelimitservicetask.stakelimitservice.service.TicketMessageService;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RequestMapping("api/v1/ticketmessage")
@Validated
@RestController
public class TicketMessageController {
    private final TicketMessageService ticketMessageService;

    public TicketMessageController(TicketMessageService ticketMessageService) {
        this.ticketMessageService = ticketMessageService;
    }

    @PostMapping
    public AddTicketMessageResponse addTicketMessage(@Valid @RequestBody TicketMessagedto dto){
        return new AddTicketMessageResponse(ticketMessageService.AddTicketMessage(dto));
    }
}
