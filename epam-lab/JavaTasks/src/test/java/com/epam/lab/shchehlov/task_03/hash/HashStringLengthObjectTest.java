package com.epam.lab.shchehlov.task_03.hash;

import com.epam.lab.shchehlov.task_03.hash.key.HashStringLengthObject;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class HashStringLengthObjectTest {
    private HashStringLengthObject object;

    @Before
    public void before() {
        object = new HashStringLengthObject("Hello");
    }


    @Test
    public void shouldReturnHashCode5() {
        int expectedHash = 5;

        assertEquals(expectedHash, object.hashCode());
    }

    @Test
    public void shouldReturnTrueWhenEquals() {
        HashStringLengthObject newObject = new HashStringLengthObject("Hello");

        assertEquals(object, newObject);
    }

    @Test
    public void shouldReturnStringValueWhenToString() {
        String expectedString = "{stringValue = Hello}";

        assertEquals(expectedString, object.toString());
    }
}
