package com.it.kobus.devices.db.entity;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter(autoApply = true)
public class DeviceStateConverter implements AttributeConverter<DeviceState, Integer> {

    @Override
    public Integer convertToDatabaseColumn(DeviceState state) {
        return state != null ? state.getId() : null;
    }

    @Override
    public DeviceState convertToEntityAttribute(Integer dbData) {
        return dbData != null ? DeviceState.fromId(dbData) : null;
    }
}
