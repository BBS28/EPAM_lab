package com.epam.lab.shchehlov.task_01.entity;

import com.epam.lab.shchehlov.task_07.shop.reflection.annotation.FieldName;

import java.util.Objects;

public class Drill extends CordedPowerTool {
    @FieldName(value = "DRILL_MAX_RPM")
    private int maxRPM;

    public Drill() {
    }

    public Drill(long id, String name, int price, int power, int maxRPM) {
        super(id, name, price, power);
        this.maxRPM = maxRPM;
    }

    public int getMaxRPM() {
        return maxRPM;
    }

    public void setMaxRPM(int maxRPM) {
        this.maxRPM = maxRPM;
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
        Drill drill = (Drill) object;
        return getMaxRPM() == drill.getMaxRPM();
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), getMaxRPM());
    }

    @Override
    public String toString() {
        return "Drill{" + super.toString() +
                "maxRPM=" + maxRPM +
                '}';
    }
}
