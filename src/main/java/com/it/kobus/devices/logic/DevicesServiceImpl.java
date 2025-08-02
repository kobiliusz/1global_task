package com.it.kobus.devices.logic;

import com.it.kobus.devices.db.entity.Brand;
import com.it.kobus.devices.db.entity.Device;
import com.it.kobus.devices.db.entity.DeviceState;
import com.it.kobus.devices.db.repo.BrandRepository;
import com.it.kobus.devices.db.repo.DeviceRepository;
import com.it.kobus.devices.logic.dto.DeviceDTO;
import com.it.kobus.devices.logic.dto.DeviceMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class DevicesServiceImpl implements DevicesService {

    private final DeviceRepository deviceRepository;
    private final BrandRepository brandRepository;

    public DevicesServiceImpl(DeviceRepository deviceRepository, BrandRepository brandRepository) {
        this.deviceRepository = deviceRepository;
        this.brandRepository = brandRepository;
    }

    @Override
    public DeviceDTO createDevice(DeviceDTO dto) {
        Brand brand = brandRepository.findByName(dto.brand)
                .orElseGet(() -> brandRepository.save(new Brand(dto.brand)));

        Device device = new Device();
        device.setName(dto.name);
        device.setBrand(brand);
        device.setState(DeviceState.valueOf(dto.state.toUpperCase()));
        device.setCreated(dto.created != null ? dto.created : java.time.LocalDateTime.now());

        return DeviceMapper.toDTO(deviceRepository.save(device));
    }

    @Override
    public DeviceDTO updateDevice(DeviceDTO dto) {
        Device existing = deviceRepository.findById(dto.id)
                .orElseThrow(() -> new IllegalArgumentException("Device not found"));

        // Created cannot be updated
        DeviceState newState = DeviceState.valueOf(dto.state.toUpperCase());

        if (existing.getState() == DeviceState.IN_USE) {
            // name and brand cannot be changed
            dto.name = existing.getName();
            dto.brand = existing.getBrand().getName();
        }

        existing.setState(newState);
        existing.setName(dto.name);
        existing.setBrand(brandRepository.findByName(dto.brand)
                .orElseThrow(() -> new IllegalArgumentException("Unknown brand: " + dto.brand)));

        return DeviceMapper.toDTO(deviceRepository.save(existing));
    }

    @Override
    public Optional<DeviceDTO> getDevice(Long id) {
        return deviceRepository.findById(id).map(DeviceMapper::toDTO);
    }

    @Override
    public List<DeviceDTO> getAllDevices() {
        return deviceRepository.findAll().stream().map(DeviceMapper::toDTO).collect(Collectors.toList());
    }

    @Override
    public List<DeviceDTO> getDevicesByBrand(String brandName) {
        return brandRepository.findByName(brandName)
                .map(brand -> deviceRepository.findByBrandId(brand.getId()))
                .orElse(List.of())
                .stream()
                .map(DeviceMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<DeviceDTO> getDevicesByState(String state) {
        try {
            DeviceState deviceState = DeviceState.valueOf(state.toUpperCase());
            return deviceRepository.findByState(deviceState).stream()
                    .map(DeviceMapper::toDTO)
                    .collect(Collectors.toList());
        } catch (IllegalArgumentException e) {
            return List.of();
        }
    }

    @Override
    public void deleteDevice(Long id) {
        Device device = deviceRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Device not found"));

        if (device.getState() == DeviceState.IN_USE) {
            throw new IllegalStateException("Cannot delete a device that is in use.");
        }

        deviceRepository.deleteById(id);
    }
}