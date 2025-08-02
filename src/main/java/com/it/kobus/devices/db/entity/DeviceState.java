package com.it.kobus.devices.db.entity;

public enum DeviceState {
    AVAILABLE(1, "available"),
    IN_USE(2, "in-use"),
    INACTIVE(3, "inactive");

    private final int id;
    private final String name;

    DeviceState(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public static DeviceState fromId(int id) {
        for (DeviceState state : values()) {
            if (state.id == id) return state;
        }
        throw new IllegalArgumentException("Unknown DeviceState id: " + id);
    }

    public static DeviceState fromName(String name) {
        for (DeviceState state : values()) {
            if (state.name.equals(name)) return state;
        }
        throw new IllegalArgumentException("Unknown DeviceState name: " + name);
    }

}
