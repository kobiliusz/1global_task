package com.it.kobus.devices.logic.dto;

import com.it.kobus.devices.db.entity.Brand;
import com.it.kobus.devices.db.entity.Device;
import com.it.kobus.devices.db.entity.DeviceState;
import com.it.kobus.devices.db.repo.BrandRepository;

public class DeviceMapper {

    public static DeviceDTO toDTO(Device device) {
        DeviceDTO dto = new DeviceDTO();
        dto.id = device.getId();
        dto.name = device.getName();
        dto.created = device.getCreated();
        dto.state = device.getState().name();
        dto.brand = device.getBrand().getName();
        return dto;
    }

    public static Device fromDTO(DeviceDTO dto, BrandRepository brandRepository) {
        Device device = new Device();
        device.setId(dto.id);
        device.setName(dto.name);
        device.setCreated(dto.created);
        device.setState(DeviceState.valueOf(dto.state.toUpperCase()));

        Brand brand = brandRepository.findByName(dto.brand)
                .orElseThrow(() -> new IllegalArgumentException("Unknown brand: " + dto.brand));
        device.setBrand(brand);

        return device;
    }
}
