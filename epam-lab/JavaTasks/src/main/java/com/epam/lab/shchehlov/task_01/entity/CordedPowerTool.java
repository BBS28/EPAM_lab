package com.epam.lab.shchehlov.task_01.entity;

import com.epam.lab.shchehlov.task_07.shop.reflection.annotation.FieldName;

import java.util.Objects;

public class CordedPowerTool extends PowerTool {
    @FieldName(value = "TOOL_POWER")
    private int power;

    public CordedPowerTool() {
    }

    public CordedPowerTool(long id, String name, int price, int power) {
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
        CordedPowerTool that = (CordedPowerTool) object;
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
