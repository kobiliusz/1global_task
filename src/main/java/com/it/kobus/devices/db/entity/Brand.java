package com.it.kobus.devices.db.entity;
import jakarta.persistence.*;
import java.util.Set;

@Entity
public class Brand {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 256)
    private String name;

    @OneToMany(mappedBy = "brand")
    private Set<Device> devices;

    public Brand(Long id, String name, Set<Device> devices) {
        this.id = id;
        this.name = name;
        this.devices = devices;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<Device> getDevices() {
        return devices;
    }

    public void setDevices(Set<Device> devices) {
        this.devices = devices;
    }
}