package com.epam.lab.shchehlov.task_03.unique.value.arraylist;

import com.epam.lab.shchehlov.task_03.unique.value.arraylist.exception.DuplicatedValueException;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class UniqueValueArrayListTest {
    private List<String> elements;
    private List<String> uniqueList;
    private List<String> notUniqueList;

    @Before
    public void before() {
        elements = new UniqueValueArrayList<>();
        elements.add("One");
        elements.add("Two");
        elements.add("Three");
        elements.add("Four");
        elements.add("Five");
        elements.add("Six");
        elements.add("Seven");

        uniqueList = new ArrayList<>();
        uniqueList.add("Eight");
        uniqueList.add("Nine");
        uniqueList.add("Ten");
        uniqueList.add("Zero");

        notUniqueList = new ArrayList<>();
        notUniqueList.add("Eight");
        notUniqueList.add("Nine");
        notUniqueList.add("Ten");
        notUniqueList.add("Zero");
        notUniqueList.add("Four");
        notUniqueList.add("Seven");

    }

    @Test
    public void shouldSetUniqueElement() {
        String element = "Eleven";
        int index = 4;

        elements.set(index, element);

        assertEquals(element, elements.get(index));
    }

    @Test(expected = DuplicatedValueException.class)
    public void shouldThrowExceptionWhenSetNotUniqueElement() {
        String element = "Five";
        int index = 4;

        elements.set(index, element);
    }

    @Test
    public void shouldReturnTrueWhenAddUniqueElement() {
        String element = "Eight";

        elements.add(element);

        assertTrue(elements.contains(element));
    }

    @Test(expected = DuplicatedValueException.class)
    public void shouldThrowExceptionWhenAddedElementThatExists() {
        String element = "One";

        elements.add(element);
    }

    @Test
    public void shouldReturnTrueWhenAddByIndexUniqueElement() {
        String element = "Eight";

        elements.add(element);

        assertTrue(elements.contains(element));
    }

    @Test(expected = DuplicatedValueException.class)
    public void shouldThrowExceptionWhenAddedByIndexElementThatExists() {
        String element = "One";

        elements.add(element);
    }

    @Test
    public void shouldAddAllCollectionWithUniqueElements() {
        int elementsSize = elements.size();
        int uniqueListSize = uniqueList.size();
        int finalLength = elementsSize + uniqueListSize;

        elements.addAll(uniqueList);

        assertEquals(finalLength, elements.size());
    }

    @Test(expected = DuplicatedValueException.class)
    public void shouldThrowExceptionWhenAddAllCollectionWithNotUniqueElements() {
        elements.addAll(notUniqueList);
    }

    @Test
    public void shouldAddAllByIndexCollectionWithUniqueElements() {
        int elementsSize = elements.size();
        int uniqueListSize = uniqueList.size();
        int finalLength = elementsSize + uniqueListSize;
        int index = 5;

        elements.addAll(index, uniqueList);

        assertEquals(finalLength, elements.size());
    }

    @Test(expected = DuplicatedValueException.class)
    public void shouldThrowExceptionWhenAddAllByIndexCollectionWithNotUniqueElements() {
        int index = 5;

        elements.addAll(index, notUniqueList);
    }

    @Test(expected = DuplicatedValueException.class)
    public void shouldThrowExceptionWhenAddedTwoElementsNullValue() {
        elements.add(null);
        elements.add(null);
    }

    @Test(expected = UnsupportedOperationException.class)
    public void shouldThrowExceptionWhenListIteratorCalls() {
        ListIterator<String> iterator = elements.listIterator();
    }

    @Test(expected = UnsupportedOperationException.class)
    public void shouldThrowExceptionWhenListIteratorWithIndexCalls() {
        int index = 3;
        ListIterator<String> iterator = elements.listIterator(3);
    }
}
