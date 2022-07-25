package com.kharkiv.epam.shchehlov.entity;

import java.util.Objects;

public class CordlessPowerTool extends PowerTool {
    private int voltage;
    private String batteryType;
    private int batteryCapacity;

    public CordlessPowerTool() {
    }

    public CordlessPowerTool(long id, String name, double price, int voltage, String batteryType, int batteryCapacity) {
        super(id, name, price);
        this.voltage = voltage;
        this.batteryType = batteryType;
        this.batteryCapacity = batteryCapacity;
    }

    public int getVoltage() {
        return voltage;
    }

    public void setVoltage(int voltage) {
        this.voltage = voltage;
    }

    public String getBatteryType() {
        return batteryType;
    }

    public void setBatteryType(String batteryType) {
        this.batteryType = batteryType;
    }

    public int getBatteryCapacity() {
        return batteryCapacity;
    }

    public void setBatteryCapacity(int batteryCapacity) {
        this.batteryCapacity = batteryCapacity;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof CordlessPowerTool)) return false;
        if (!super.equals(o)) return false;
        CordlessPowerTool that = (CordlessPowerTool) o;
        return getVoltage() == that.getVoltage() &&
                getBatteryCapacity() == that.getBatteryCapacity() &&
                getBatteryType().equals(that.getBatteryType());
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), getVoltage(), getBatteryType(), getBatteryCapacity());
    }

    @Override
    public String toString() {
        return "CordlessPowerTool{" + super.toString() +
                "voltage=" + voltage +
                ", batteryType='" + batteryType + '\'' +
                ", batteryCapacity=" + batteryCapacity +
                '}';
    }
}
