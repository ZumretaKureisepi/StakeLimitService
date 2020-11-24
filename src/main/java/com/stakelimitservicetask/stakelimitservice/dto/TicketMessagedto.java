package com.stakelimitservicetask.stakelimitservice.dto;

import javax.validation.constraints.DecimalMin;
import java.util.UUID;

public class TicketMessagedto {
    private final UUID id;
    private final UUID deviceId;
    @DecimalMin(value = "0.01", message = "The stake must be 0.01 or greater.")
    private final double stake;

    public TicketMessagedto(UUID id, UUID deviceId, double stake) {
        this.id = id;
        this.deviceId = deviceId;
        this.stake = stake;
    }

    public UUID getId() {
        return id;
    }

    public UUID getDeviceId() {
        return deviceId;
    }

    public double getStake() {
        return stake;
    }
}
