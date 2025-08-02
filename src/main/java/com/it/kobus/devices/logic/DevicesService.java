package com.it.kobus.devices.logic;

import com.it.kobus.devices.logic.dto.DeviceDTO;

import java.util.List;
import java.util.Optional;

public interface DevicesService {

    DeviceDTO createDevice(DeviceDTO device);

    DeviceDTO updateDevice(DeviceDTO device);

    Optional<DeviceDTO> getDevice(Long id);

    List<DeviceDTO> getAllDevices();

    List<DeviceDTO> getDevicesByBrand(String brand);

    List<DeviceDTO> getDevicesByState(String state);

    void deleteDevice(Long id);

}
