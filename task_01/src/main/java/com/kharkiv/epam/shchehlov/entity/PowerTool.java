package com.kharkiv.epam.shchehlov.entity;

import java.util.Objects;

public class PowerTool {
    private long id;
    private String name;
    private double price;

    public PowerTool() {
    }

    public PowerTool(long id, String name, double price) {
        this.id = id;
        this.name = name;
        this.price = price;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof PowerTool)) return false;
        PowerTool powerTool = (PowerTool) o;
        return getId() == powerTool.getId() &&
                Double.compare(powerTool.getPrice(), getPrice()) == 0 &&
                getName().equals(powerTool.getName());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getName(), getPrice());
    }

    @Override
    public String toString() {
        return "PowerTool{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", price=" + price +
                '}';
    }
}
