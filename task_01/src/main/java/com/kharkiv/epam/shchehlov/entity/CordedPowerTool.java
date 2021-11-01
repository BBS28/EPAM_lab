package com.kharkiv.epam.shchehlov.entity;

import java.util.Objects;

public class CordedPowerTool extends PowerTool {
    private int power;

    public CordedPowerTool() {
    }

    public CordedPowerTool(long id, String name, double price, int power) {
        super(id, name, price);
        this.power = power;
    }

    public int getPower() {
        return power;
    }

    public void setPower(int power) {
        this.power = power;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof CordedPowerTool)) return false;
        if (!super.equals(o)) return false;
        CordedPowerTool that = (CordedPowerTool) o;
        return getPower() == that.getPower();
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), getPower());
    }

    @Override
    public String toString() {
        return "CordedPowerTool{" + super.toString() +
                "power=" + power +
                '}';
    }
}
