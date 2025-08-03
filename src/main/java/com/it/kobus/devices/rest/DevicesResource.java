package com.it.kobus.devices.rest;

import com.it.kobus.devices.db.entity.Device;
import com.it.kobus.devices.logic.DevicesService;
import com.it.kobus.devices.logic.dto.DeviceDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/device")
public class DevicesResource {

    private final DevicesService deviceService;

    public DevicesResource(DevicesService deviceService) {
        this.deviceService = deviceService;
    }

    @GetMapping("/hello")
    public String hello() {
        return "Service UP";
    }

    @PostMapping
    public ResponseEntity<DeviceDTO> create(@RequestBody DeviceDTO dto) {
        DeviceDTO created = deviceService.createDevice(dto);
        return ResponseEntity.ok(created);
    }

    @PutMapping("/{id}")
    public ResponseEntity<DeviceDTO> update(@PathVariable Long id, @RequestBody DeviceDTO dto) {
        dto.id = id;
        DeviceDTO updated = deviceService.updateDevice(dto);
        return ResponseEntity.ok(updated);
    }

    @GetMapping("/{id}")
    public ResponseEntity<DeviceDTO> getById(@PathVariable Long id) {
        Optional<DeviceDTO> dto = deviceService.getDevice(id);
        return dto.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping
    public ResponseEntity<List<DeviceDTO>> getAll() {
        return ResponseEntity.ok(deviceService.getAllDevices());
    }

    @GetMapping("/brand/{brand}")
    public ResponseEntity<List<DeviceDTO>> getByBrand(@PathVariable String brand) {
        return ResponseEntity.ok(deviceService.getDevicesByBrand(brand));
    }

    @GetMapping("/state/{state}")
    public ResponseEntity<List<DeviceDTO>> getByState(@PathVariable String state) {
        return ResponseEntity.ok(deviceService.getDevicesByState(state));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        deviceService.deleteDevice(id);
        return ResponseEntity.noContent().build();
    }
}