package com.epam.lab.shchehlov.task_03.hash.key;

import java.util.Objects;

/**
 * A class that uses the length of the {@code stringValue} field to determine the value of {@code hashcode()}
 *
 * @author B.Shchehlov
 */
public class HashStringLengthObject {
    private String stringValue;

    public HashStringLengthObject(String stringValue) {
        this.stringValue = stringValue;
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) {
            return true;
        }
        if (object == null || getClass() != object.getClass()) {
            return false;
        }
        HashStringLengthObject that = (HashStringLengthObject) object;
        return Objects.equals(stringValue, that.stringValue);
    }

    @Override
    public int hashCode() {
        if (stringValue == null) {
            return 0;
        }
        return stringValue.length();
    }

    @Override
    public String toString() {
        return "{stringValue = " + stringValue + '}';
    }
}
