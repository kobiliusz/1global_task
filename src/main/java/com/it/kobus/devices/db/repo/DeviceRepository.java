package com.it.kobus.devices.db.repo;

import com.it.kobus.devices.db.entity.Device;
import com.it.kobus.devices.db.entity.DeviceState;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DeviceRepository extends JpaRepository<Device, Long> {

    List<Device> findByBrandId(Long brandId);

    List<Device> findByState(DeviceState state);
}
