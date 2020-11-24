DROP TABLE IF EXISTS TicketMessage;
DROP TABLE IF EXISTS Device;

CREATE TABLE Device(
    id UUID NOT NULL PRIMARY KEY,
    stakeLimit DOUBLE PRECISION NOT NULL,
    hotAmountPct DOUBLE PRECISION NOT NULL,
    restrExpiry INT NOT NULL,
    timeDuration INT NOT NULL,
    deviceStatus INT NOT NULL,
    sumStakes DOUBLE PRECISION NOT NULL DEFAULT 0,
    blockedAt BIGINT NOT NULL DEFAULT 0,
    startTime BIGINT NOT NULL DEFAULT 0
);

CREATE TABLE TicketMessage(
    id UUID NOT NULL PRIMARY KEY,
    deviceId UUID NOT NULL,
    stake DOUBLE PRECISION NOT NULL,

    CONSTRAINT fk_ticketmessage_deviceid FOREIGN KEY (deviceId) REFERENCES Device (id)
);

