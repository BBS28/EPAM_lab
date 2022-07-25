package com.epam.lab.shchehlov.task_07.proxy.domain;

/**
 * Product interface
 *
 * @author B. Shchehlov
 */
public interface Tool {
    long getId();

    void setId(long id);

    String getName();

    void setName(String name);

    int getPrice();

    void setPrice(int price);

    int getPower();

    void setPower(int power);
}
