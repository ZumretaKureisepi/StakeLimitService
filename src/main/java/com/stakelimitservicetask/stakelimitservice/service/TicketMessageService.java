package com.stakelimitservicetask.stakelimitservice.service;

import com.stakelimitservicetask.stakelimitservice.dao.DeviceDao;
import com.stakelimitservicetask.stakelimitservice.dao.TicketMessageDao;
import com.stakelimitservicetask.stakelimitservice.dto.TicketMessagedto;
import com.stakelimitservicetask.stakelimitservice.enums.TicketMessageStatus;
import com.stakelimitservicetask.stakelimitservice.model.Device;
import com.stakelimitservicetask.stakelimitservice.model.TicketMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.Instant;
import java.util.Optional;

@Service
public class TicketMessageService {

    private final TicketMessageDao ticketMessageDao;
    private final DeviceDao deviceDao;

    @Autowired
    public TicketMessageService(TicketMessageDao ticketMessageDao, DeviceDao deviceDao) {
        this.ticketMessageDao = ticketMessageDao;
        this.deviceDao = deviceDao;
    }

    public TicketMessageStatus AddTicketMessage(TicketMessagedto dto){
        Optional<TicketMessage> existingTicketMessage = ticketMessageDao.findTicketMessage(dto.getId());
        if(existingTicketMessage.isPresent()) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST, "Ticket message with UUID you provided already exists.");
        }

        TicketMessage ticketMessage=new TicketMessage(dto.getId(),dto.getDeviceId(),dto.getStake());
        Optional<Device> deviceExists=deviceDao.findDevice(ticketMessage.getDeviceId());
        if(deviceExists.isEmpty())
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "Device Not Found");

        Device device = deviceExists.get();
        
        Instant instant = Instant.now();
        long timeStampSeconds = instant.getEpochSecond();

        if(device.getDevicestatus() == TicketMessageStatus.BLOCKED){
            if(device.getRestrExpiry() == 0 || timeStampSeconds - device.getBlockedat() < device.getRestrExpiry())
                return TicketMessageStatus.BLOCKED;
            else{
                device.setSumStakes(0);
                device.setBlockedat(0);
                device.setStartTime(0);

            }
        }

        if(device.getStartTime() == 0 ||
                device.getStartTime() + device.getTimeDuration() < timeStampSeconds) {
            device.setStartTime(timeStampSeconds);
            device.setSumStakes(0);
        }

        double newSumStakes=device.getSumStakes() + dto.getStake();
        if(newSumStakes >= device.getStakeLimit()) {
            device.setDevicestatus(TicketMessageStatus.BLOCKED);
            device.setBlockedat(timeStampSeconds);
        }
        else {
            double hotAmount = device.getStakeLimit() * (device.getHotAmountPctg() / 100);
            if(newSumStakes >= hotAmount) {
                device.setDevicestatus(TicketMessageStatus.HOT);
            }
            else {
                device.setDevicestatus(TicketMessageStatus.OK);
            }
            device.setSumStakes(device.getSumStakes() + dto.getStake());
            ticketMessageDao.insertTicketMessage(ticketMessage);
        }
        deviceDao.updateDevice(dto.getDeviceId(), device);

        return device.getDevicestatus();

    }


}
