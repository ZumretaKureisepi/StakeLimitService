package com.stakelimitservicetask.stakelimitservice.dao;

import com.stakelimitservicetask.stakelimitservice.model.TicketMessage;

import java.util.Optional;
import java.util.UUID;


public interface TicketMessageDao {

int insertTicketMessage(TicketMessage tm);

Optional<TicketMessage> findTicketMessage(UUID id);

}
