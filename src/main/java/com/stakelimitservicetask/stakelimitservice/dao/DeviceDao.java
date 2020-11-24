package com.stakelimitservicetask.stakelimitservice.dao;

import com.stakelimitservicetask.stakelimitservice.model.Device;

import java.util.Optional;
import java.util.UUID;

public interface DeviceDao {
    Optional<Device> findDevice(UUID id);
    int updateDevice(UUID deviceId, Device device);

    int insertDevice(Device device);
    default Device addDevice(Device device) {
        device.setId(UUID.randomUUID());

        if(insertDevice(device) == 1)
            return device;

        return null;
    }

}
