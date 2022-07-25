package com.epam.lab.shchehlov.task_03.hash;

import com.epam.lab.shchehlov.task_03.hash.key.HashFirstFourCharsObject;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

public class HashFirstFourCharsObjectTest {
    private HashFirstFourCharsObject object1;
    private HashFirstFourCharsObject object2;
    private HashFirstFourCharsObject object3;

    @Before
    public void before() {
        object1 = new HashFirstFourCharsObject("Hello");
        object2 = new HashFirstFourCharsObject("Bye");
        object3 = new HashFirstFourCharsObject("Hello World");
    }

    @Test
    public void shouldReturnTheSameHashCodeWhenFirstFourCharsEquals() {
        assertEquals(object1.hashCode(), object3.hashCode());
    }

    @Test
    public void shouldCountHashCodeIfStringHasLess4Chars() {
        int expectedHashcode = 'B' + 'y' + 'e';

        assertEquals(expectedHashcode, object2.hashCode());
    }

    @Test
    public void shouldReturnNotEqualsWithDifferentStringValue() {
        assertNotEquals(object1, object3);
    }

    @Test
    public void shouldReturnStringValueWhenToString() {
        String expectedString = "{stringValue = Hello}";

        assertEquals(expectedString, object1.toString());
    }
}
