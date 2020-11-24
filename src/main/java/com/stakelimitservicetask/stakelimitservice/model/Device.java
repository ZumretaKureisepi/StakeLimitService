package com.stakelimitservicetask.stakelimitservice.model;

import com.stakelimitservicetask.stakelimitservice.enums.TicketMessageStatus;

import javax.persistence.*;
import java.util.Set;
import java.util.UUID;

public class Device {

    private UUID id;
    private int timeDuration;
    private double stakeLimit;
    private double hotAmountPctg;
    private double sumStakes;
    private  int restrExpiry;
    private TicketMessageStatus devicestatus;
    private  long blockedat;
    private  long startTime;

    @OneToMany(mappedBy = "device", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<TicketMessage> ticketMessages;

    public Device(UUID id, int timeDuration, double stakeLimit, double hotAmountPctg, double sumStakes, int restrExpiry, TicketMessageStatus devicestatus,long blockedat,long startTime) {
        this.id = id;
        this.timeDuration = timeDuration;
        this.stakeLimit = stakeLimit;
        this.hotAmountPctg = hotAmountPctg;
        this.sumStakes = sumStakes;
        this.restrExpiry = restrExpiry;
        this.devicestatus = devicestatus;
        this.blockedat=blockedat;
        this.startTime=startTime;
    }

    public UUID getId() {
        return id;
    }

    public int getTimeDuration() {
        return timeDuration;
    }

    public double getStakeLimit() {
        return stakeLimit;
    }

    public double getHotAmountPctg() {
        return hotAmountPctg;
    }

    public double getSumStakes() {
        return sumStakes;
    }

    public int getRestrExpiry() {
        return restrExpiry;
    }


    public long getBlockedat() {
        return blockedat;
    }

    public TicketMessageStatus getDevicestatus() {
        return devicestatus;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public long getStartTime() {
        return startTime;
    }

    public void setTimeDuration(int timeDuration) {
        this.timeDuration = timeDuration;
    }

    public void setStakeLimit(double stakeLimit) {
        this.stakeLimit = stakeLimit;
    }

    public void setHotAmountPctg(double hotAmountPctg) {
        this.hotAmountPctg = hotAmountPctg;
    }

    public void setSumStakes(double sumStakes) {
        this.sumStakes = sumStakes;
    }

    public void setRestrExpiry(int restrExpiry) {
        this.restrExpiry = restrExpiry;
    }

    public void setDevicestatus(TicketMessageStatus devicestatus) {
        this.devicestatus = devicestatus;
    }

    public void setBlockedat(long blockedat) {
        this.blockedat = blockedat;
    }

    public void setStartTime(long startTime) {
        this.startTime = startTime;
    }
}
