package com.stakelimitservicetask.stakelimitservice.dto;

import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

public class Devicedto {
    @Min(value = 5 * 60, message = "The timeDuration must be 5 minutes (300 seconds) at minimum.")
    @Max(value = 24 * 60 * 60, message = "The timeDuration must be less than 24 hours (86400 seconds).")
    private final int timeDuration;
    @Range(min = 1, max = 10000000, message = "The stakeLimit must be between 1 and 10000000.")
    private final double stakeLimit;
    @Range(min = 1, max = 100, message = "The hotAmountPctg must be between 1 and 100.")
    private final double hotAmountPctg;
    @Min(value = 0, message = "The restrExpiry must be at least 0.")
    private final int restrExpiry;

    public Devicedto(int timeDuration, double stakeLimit, double hotAmountPctg, int restrExpiry) {
        this.timeDuration = timeDuration;
        this.stakeLimit = stakeLimit;
        this.hotAmountPctg = hotAmountPctg;
        this.restrExpiry = restrExpiry;
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

    public int getRestrExpiry() {
        return restrExpiry;
    }
}
