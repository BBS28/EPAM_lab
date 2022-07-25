package com.epam.lab.shchehlov.task_01.entity;

import com.epam.lab.shchehlov.task_07.shop.reflection.annotation.FieldName;

import java.io.Serializable;
import java.util.Objects;

public class PowerTool implements Serializable {
    private long id;
    @FieldName(value = "TOOL_NAME")
    private String name;
    @FieldName(value = "TOOL_PRICE")
    private int price;

    public PowerTool() {
    }

    public PowerTool(long id, String name, int price) {
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

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) {
            return true;
        }
        if (object == null || getClass() != object.getClass()) {
            return false;
        }
        PowerTool powerTool = (PowerTool) object;
        return getId() == powerTool.getId() &&
                powerTool.getPrice() == getPrice() &&
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
