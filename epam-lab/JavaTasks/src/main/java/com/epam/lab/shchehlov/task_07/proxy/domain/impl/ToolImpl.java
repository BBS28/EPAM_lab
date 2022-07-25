package com.epam.lab.shchehlov.task_07.proxy.domain.impl;

import com.epam.lab.shchehlov.task_07.proxy.domain.Tool;

/**
 * Implementation of interface Tool
 *
 * @author B. Shchehlov
 */
public class ToolImpl implements Tool {
    private long id;
    private String name;
    private int price;
    private int power;

    public ToolImpl(long id, String name, int power) {
        this.id = id;
        this.name = name;
        this.power = power;
    }

    public ToolImpl(long id, String name, int price, int power) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.power = power;
    }

    @Override
    public long getId() {
        return id;
    }

    @Override
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

    public int getPower() {
        return power;
    }

    public void setPower(int power) {
        this.power = power;
    }

    @Override
    public String toString() {
        return "ToolImpl{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", power=" + power +
                '}';
    }
}
