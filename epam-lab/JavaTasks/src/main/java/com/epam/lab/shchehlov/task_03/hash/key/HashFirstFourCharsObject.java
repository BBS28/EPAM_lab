package com.epam.lab.shchehlov.task_03.hash.key;

import java.util.Objects;

/**
 * A class that uses first 4 chars of {@code stringValue} field to determine the value of {@code hashcode()}
 *
 * @author B.Shchehlov
 */
public class HashFirstFourCharsObject {
    private static final int STRING_LENGTH = 4;
    private String stringValue;

    public HashFirstFourCharsObject(String stringValue) {
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
        HashFirstFourCharsObject that = (HashFirstFourCharsObject) object;
        return Objects.equals(stringValue, that.stringValue);
    }

    @Override
    public int hashCode() {
        if (stringValue == null) {
            return 0;
        }
        int hashValue = 0;
        int charsNumber = Math.min(stringValue.length(), STRING_LENGTH);
        for (int i = 0; i < charsNumber; i++) {
            hashValue += stringValue.charAt(i);
        }
        return hashValue;
    }

    @Override
    public String toString() {
        return "{stringValue = " + stringValue + '}';
    }
}
