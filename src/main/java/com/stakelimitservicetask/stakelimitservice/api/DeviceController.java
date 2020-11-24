package com.stakelimitservicetask.stakelimitservice.api;

import com.stakelimitservicetask.stakelimitservice.dto.Devicedto;
import com.stakelimitservicetask.stakelimitservice.model.Device;
import com.stakelimitservicetask.stakelimitservice.service.DeviceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.UUID;

@RequestMapping("api/v1/device")
@Validated
@RestController
public class DeviceController {
    private  final DeviceService deviceService;

    @Autowired
    public DeviceController(DeviceService deviceService) {
        this.deviceService = deviceService;
    }
    @PutMapping(path="/{deviceid}")
    public int updateDevice(@Valid @RequestBody Devicedto dto, @Valid @PathVariable UUID deviceid) {
        return deviceService.updateDevice(deviceid, dto);
    }

    @PostMapping
    public Device addDevice(@Valid @RequestBody Devicedto dto){
        return deviceService.addDevice(dto);

    }


}
