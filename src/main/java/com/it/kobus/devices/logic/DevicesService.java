package com.it.kobus.devices.logic;

import com.it.kobus.devices.db.entity.Device;
import com.it.kobus.devices.db.entity.DeviceState;

import java.util.List;
import java.util.Optional;

public interface DevicesService {

    Device createDevice(Device device);

    Device updateDevice(Device device);

    Optional<Device> getDevice(Long id);

    List<Device> getAllDevices();

    List<Device> getDevicesByBrand(String brand);

    List<Device> getDevicesByState(String state);

    void deleteDevice(Long id);

}
