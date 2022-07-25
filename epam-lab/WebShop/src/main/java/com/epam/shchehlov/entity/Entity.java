package com.epam.shchehlov.entity;

import java.io.Serializable;

/**
 * Parent class for all entities.
 *
 * @author B.Shchehlov.
 */
public class Entity implements Serializable {

    protected long id;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
}
