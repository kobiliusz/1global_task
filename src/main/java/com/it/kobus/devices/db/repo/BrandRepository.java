package com.it.kobus.devices.db.repo;

import com.it.kobus.devices.db.entity.Brand;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface BrandRepository extends JpaRepository<Brand, Long> {

    Optional<Brand> findByName(String name);

}
