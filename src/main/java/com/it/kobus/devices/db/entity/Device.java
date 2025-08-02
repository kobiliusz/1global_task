package com.it.kobus.devices.db.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
public class Device {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 256)
    private String name;

    @ManyToOne
    @JoinColumn(name = "brand", nullable = false)
    private Brand brand;

    @Column(name = "state", nullable = false)
    @Convert(converter = DeviceStateConverter.class)
    private DeviceState state;

    @Column(nullable = false)
    private LocalDateTime created;

    public Device(Long id, String name, Brand brand, DeviceState state, LocalDateTime created) {
        this.id = id;
        this.name = name;
        this.brand = brand;
        this.state = state;
        this.created = created;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Brand getBrand() {
        return brand;
    }

    public void setBrand(Brand brand) {
        this.brand = brand;
    }

    public DeviceState getState() {
        return state;
    }

    public void setState(DeviceState state) {
        this.state = state;
    }

    public LocalDateTime getCreated() {
        return created;
    }

    public void setCreated(LocalDateTime created) {
        this.created = created;
    }
}
