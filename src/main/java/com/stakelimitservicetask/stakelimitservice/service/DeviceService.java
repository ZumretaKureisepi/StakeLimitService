package com.stakelimitservicetask.stakelimitservice.service;

import com.stakelimitservicetask.stakelimitservice.dao.DeviceDao;
import com.stakelimitservicetask.stakelimitservice.dto.Devicedto;
import com.stakelimitservicetask.stakelimitservice.enums.TicketMessageStatus;
import com.stakelimitservicetask.stakelimitservice.model.Device;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;
import java.util.UUID;

@Service
public class DeviceService {
    private final DeviceDao deviceDao;
    @Autowired
    public DeviceService(DeviceDao deviceDao) {
        this.deviceDao = deviceDao;
    }

    public int updateDevice(UUID deviceId, Devicedto dto){
        Optional<Device> device=deviceDao.findDevice(deviceId);
        if(device.isEmpty())
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "Device Not Found");

        if(dto.getRestrExpiry() > 0 && dto.getRestrExpiry() < 60) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST, "The restrExpiry must be at least 60 seconds.");
        }

        device.get().setStakeLimit(dto.getStakeLimit());
        device.get().setHotAmountPctg(dto.getHotAmountPctg());
        device.get().setRestrExpiry(dto.getRestrExpiry());
        device.get().setTimeDuration((dto.getTimeDuration()));

        return deviceDao.updateDevice(deviceId, device.get());
    }


    public Device addDevice(Devicedto dto) {
        Device device = new Device(null, dto.getTimeDuration(), dto.getStakeLimit(), dto.getHotAmountPctg(), 0, dto.getRestrExpiry(), TicketMessageStatus.OK, 0, 0);
        return deviceDao.addDevice(device);
    }
}
