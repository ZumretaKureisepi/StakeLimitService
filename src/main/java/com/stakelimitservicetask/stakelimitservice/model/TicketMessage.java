package com.stakelimitservicetask.stakelimitservice.model;

import javax.persistence.*;
import java.util.UUID;

public class TicketMessage {
    private UUID id;
    private UUID deviceId;
    private double stake;
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "deviceid", nullable = false, foreignKey = @ForeignKey(
            name = "fk_ticketmessage_deviceid"
    ))
    private Device device;

    public TicketMessage(UUID id, UUID deviceId, double stake) {
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
