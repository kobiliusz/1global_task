package com.it.kobus.devices.db.repo;

import com.it.kobus.devices.db.entity.Brand;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BrandRepository extends JpaRepository<Brand, Long> {
}
