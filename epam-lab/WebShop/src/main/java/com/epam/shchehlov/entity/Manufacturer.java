package com.epam.shchehlov.entity;

import java.util.Objects;

/**
 * Class for entity Manufacturer.
 *
 * @author B.Shchehlov.
 */
public class Manufacturer extends Entity {

    private String name;

    public Manufacturer() {
    }

    public Manufacturer(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) {
            return true;
        }
        if (!(object instanceof Manufacturer)) {
            return false;
        }
        Manufacturer that = (Manufacturer) object;
        return name.equals(that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }

    @Override
    public String toString() {
        return "Manufacturer{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}