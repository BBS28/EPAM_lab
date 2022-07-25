package com.epam.lab.shchehlov.task_01.entity;

import com.epam.lab.shchehlov.task_07.shop.reflection.annotation.FieldName;

import java.util.Objects;

public class CordlessPowerTool extends PowerTool {
    @FieldName(value = "TOOL_BATTERY_VOLTAGE")
    private int voltage;
    @FieldName(value = "TOOL_BATTERY_CAPACITY")
    private int batteryCapacity;

    public CordlessPowerTool() {
    }

    public CordlessPowerTool(long id, String name, int price, int voltage, int batteryCapacity) {
        super(id, name, price);
        this.voltage = voltage;
        this.batteryCapacity = batteryCapacity;
    }

    public int getVoltage() {
        return voltage;
    }

    public void setVoltage(int voltage) {
        this.voltage = voltage;
    }

    public int getBatteryCapacity() {
        return batteryCapacity;
    }

    public void setBatteryCapacity(int batteryCapacity) {
        this.batteryCapacity = batteryCapacity;
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) {
            return true;
        }
        if (object == null || getClass() != object.getClass()) {
            return false;
        }
        if (!super.equals(object)) {
            return false;
        }
        CordlessPowerTool that = (CordlessPowerTool) object;
        return getVoltage() == that.getVoltage() &&
                getBatteryCapacity() == that.getBatteryCapacity();
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), getVoltage(), getBatteryCapacity());
    }

    @Override
    public String toString() {
        return "CordlessPowerTool{" + super.toString() +
                "voltage=" + voltage +
                ", batteryCapacity=" + batteryCapacity +
                '}';
    }
}
