package com.it.kobus.devices.db.entity;

public enum DeviceState {
    AVAILABLE(1),
    IN_USE(2),
    INACTIVE(3);

    private final int id;

    DeviceState(int id) {
        this.id = id;
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

}
