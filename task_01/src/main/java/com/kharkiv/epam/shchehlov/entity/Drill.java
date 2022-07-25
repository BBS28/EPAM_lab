package com.kharkiv.epam.shchehlov.entity;

import java.util.Objects;

public class Drill extends CordedPowerTool {
    private int maxRPM;

    public Drill() {
    }

    public Drill(long id, String name, double price, int power, int maxRPM) {
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
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Drill)) return false;
        if (!super.equals(o)) return false;
        Drill drill = (Drill) o;
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
