package com.epam.lab.shchehlov.task_02.part.unmodifiable.container;

import com.epam.lab.shchehlov.task_01.container.PowerToolList;
import com.epam.lab.shchehlov.task_01.entity.Drill;
import com.epam.lab.shchehlov.task_01.entity.PowerTool;
import com.epam.lab.shchehlov.task_02.cowcontainer.CopyOnWritePowerToolList;
import com.epam.lab.shchehlov.task_02.part.unmodifiable.container.exception.UnmodifiablePartModificationException;
import org.junit.Before;
import org.junit.Test;

import java.util.Iterator;
import java.util.List;
import java.util.function.Predicate;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

public class PartiallyUnmodifiedPowerToolListTest {
    private PartiallyUnmodifiablePowerToolList toolList;

    @Before
    public void before() {
        PowerToolList unmodifiedToolList = new PowerToolList();
        unmodifiedToolList.add(new Drill(1L, "Bosch 10", 2098, 1000, 2400));
        unmodifiedToolList.add(new Drill(2L, "Bosch 20", 2598, 1400, 2800));
        unmodifiedToolList.add(new Drill(3L, "Bosch 30", 3000, 1200, 2900));
        unmodifiedToolList.add(new Drill(4L, "Bosch 40", 1500, 1000, 2000));
        unmodifiedToolList.add(new Drill(5L, "Bosch 50", 1098, 800, 1800));
        unmodifiedToolList.add(new Drill(6L, "Bosch 60", 3500, 1200, 3200));
        unmodifiedToolList.add(new Drill(7L, "Bosch 70", 1999, 1500, 3000));

        toolList = new PartiallyUnmodifiablePowerToolList(unmodifiedToolList);
        toolList.add(new Drill(11L, "Bosch 110", 2098, 1000, 2400));
        toolList.add(new Drill(12L, "Bosch 120", 2598, 1400, 2800));
        toolList.add(new Drill(13L, "Bosch 130", 3000, 1200, 2900));
        toolList.add(new Drill(14L, "Bosch 140", 1500, 1000, 2000));
        toolList.add(new Drill(15L, "Bosch 150", 1098, 800, 1800));
        toolList.add(new Drill(16L, "Bosch 160", 3500, 1200, 3200));
        toolList.add(new Drill(17L, "Bosch 170", 1999, 1500, 3000));
    }

    @Test
    public void shouldReturnTrueWhenAddedNewTool() {
        PowerTool drill = new Drill(10L, "Bosch 100", 2098, 1000, 2400);

        assertTrue(toolList.add(drill));
    }

    @Test
    public void shouldReturnElementFromUnmodifiablePart() {
        PowerTool element = new Drill(5L, "Bosch 50", 1098, 800, 1800);
        int indexUnModifiable = 4;

        assertEquals(element, toolList.get(indexUnModifiable));
    }

    @Test
    public void shouldReturnNullWhenAddedNull() {
        int listSize = toolList.size();
        toolList.add(null);

        assertNull(toolList.get(listSize));
    }

    @Test
    public void shouldReturnElementAddedByIndex() {
        PowerTool drill = new Drill(10L, "Bosch 100", 2098, 1000, 2400);
        int indexModifiable = 10;

        toolList.add(indexModifiable, drill);

        assertEquals(drill, toolList.get(indexModifiable));
    }

    @Test(expected = UnmodifiablePartModificationException.class)
    public void shouldReturnExceptionWhenAddedByIndexToUnmodifiablePart() {
        PowerTool drill = new Drill(10L, "Bosch 100", 2098, 1000, 2400);

        toolList.add(3, drill);
    }

    @Test
    public void shouldReturnNullWhenNullAddedByIndex() {
        int indexModifiable = 9;
        toolList.add(indexModifiable, null);

        assertNull(toolList.get(indexModifiable));
    }

    @Test
    public void shouldReturnElementAddedByIndexInTheEnd() {
        PowerTool drill = new Drill(10L, "Bosch 100", 2098, 1000, 2400);

        toolList.add(toolList.size(), drill);
        int lastIndex = toolList.size() - 1;

        assertEquals(drill, toolList.get(lastIndex));
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void shouldReturnExceptionWhenAddedAtWrongIndex() {
        PowerTool drill = new Drill(10L, "Bosch 100", 2098, 1000, 2400);
        int wrongIndex = toolList.size() + 1;

        toolList.add(wrongIndex, drill);
    }

    @Test
    public void shouldNotReturnElementAfterRemovedByIndexFromModifiablePart() {
        int indexModifiable = 10;
        PowerTool drill = toolList.get(indexModifiable);

        toolList.remove(indexModifiable);

        assertNotEquals(drill, toolList.get(indexModifiable));
    }

    @Test(expected = UnmodifiablePartModificationException.class)
    public void shouldReturnExceptionWhenRemoveByIndexFromUnmodifiablePart() {
        int indexUnModifiable = 4;

        toolList.remove(indexUnModifiable);
    }

    @Test(expected = UnmodifiablePartModificationException.class)
    public void shouldReturnExceptionWhenRemoveObjectFromUnmodifiablePart() {
        PowerTool tool = new Drill(3L, "Bosch 30", 3000, 1200, 2900);

        toolList.remove(tool);
    }

    @Test
    public void shouldReturnFalseWhenRemovedByLastIndex() {
        int lastIndex = toolList.size() - 1;
        PowerTool drill = toolList.get(lastIndex);

        toolList.remove(lastIndex);

        assertFalse(toolList.contains(drill));
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void shouldReturnExceptionWhenRemovedByWrongIndex() {
        int wrongIndex = toolList.size() + 1;

        toolList.remove(wrongIndex);
    }

    @Test
    public void shouldReturnFalseAfterRemovedByObjectFromModifiable() {
        int indexModifiable = 12;
        PowerTool tool = toolList.get(indexModifiable);

        toolList.remove(tool);

        assertFalse(toolList.contains(tool));
    }

    @Test
    public void shouldReturnTrueWhenContainsAllCollectionInTwoParts() {
        PowerToolList toolCollection = new PowerToolList();
        toolCollection.add(toolList.get(2));
        toolCollection.add(toolList.get(5));
        toolCollection.add(toolList.get(8));
        toolCollection.add(toolList.get(11));

        assertTrue(toolList.containsAll(toolCollection));
    }

    @Test
    public void shouldRetainAllInUnmodifiableAndPartInModifiable() {
        PowerToolList toolCollection = new PowerToolList();
        toolCollection.add(toolList.get(0));
        toolCollection.add(toolList.get(1));
        toolCollection.add(toolList.get(2));
        toolCollection.add(toolList.get(3));
        toolCollection.add(toolList.get(4));
        toolCollection.add(toolList.get(5));
        toolCollection.add(toolList.get(6));
        toolCollection.add(toolList.get(8));
        toolCollection.add(toolList.get(10));
        toolCollection.add(toolList.get(11));
        PowerTool element = new Drill(17L, "Bosch 170", 1999, 1500, 3000);

        toolList.retainAll(toolCollection);

        assertTrue(toolList.containsAll(toolCollection));
        assertFalse(toolList.contains(element));
    }

    @Test(expected = UnmodifiablePartModificationException.class)
    public void shouldReturnExceptionWhenRemoveAllHasElementsFromUnmodifiable() {
        PowerToolList toolCollection = new PowerToolList();
        toolCollection.add(toolList.get(1));
        toolCollection.add(toolList.get(3));
        toolCollection.add(toolList.get(8));
        toolCollection.add(toolList.get(10));
        toolCollection.add(toolList.get(11));

        toolList.removeAll(toolCollection);
    }

    @Test(expected = UnmodifiablePartModificationException.class)
    public void shouldReturnExceptionWhenAddAllHasElementsFromUnmodifiable() {
        PowerToolList toolCollection = new PowerToolList();
        toolCollection.add(toolList.get(1));
        toolCollection.add(toolList.get(3));
        toolCollection.add(toolList.get(8));
        toolCollection.add(toolList.get(10));
        toolCollection.add(toolList.get(11));
        int indexUnmodifiable = 3;

        toolList.addAll(indexUnmodifiable, toolCollection);
    }

    @Test(expected = UnmodifiablePartModificationException.class)
    public void shouldReturnExceptionWhenRetainAllHasElementsFromUnmodifiable() {
        PowerToolList toolCollection = new PowerToolList();
        toolCollection.add(toolList.get(1));
        toolCollection.add(toolList.get(3));
        toolCollection.add(toolList.get(8));
        toolCollection.add(toolList.get(10));
        toolCollection.add(toolList.get(11));

        toolList.retainAll(toolCollection);
    }

    @Test
    public void shouldReturnListTheSameSizeAfterIteration() {
        Iterator<PowerTool> iterator = toolList.iterator();
        List<PowerTool> tools = new CopyOnWritePowerToolList();

        while (iterator.hasNext()) {
            tools.add(iterator.next());
        }

        assertEquals(toolList.size(), tools.size());
    }

    @Test
    public void shouldReturnListContainsThreeObjectsAfterIterationWithCondition() {
        Predicate<PowerTool> p = (PowerTool powerTool) -> powerTool.getPrice() > 2500;
        Iterator<PowerTool> iterator = toolList.iteratorWithCondition(p);
        List<PowerTool> tools = new CopyOnWritePowerToolList();
        int objectsExpected = 6;

        while (iterator.hasNext()) {
            tools.add(iterator.next());
        }

        assertEquals(objectsExpected, tools.size());
    }

    @Test
    public void shouldRemovePreviousElementByIndexWhenSetByIndexIntoModifiablePart() {
        int indexModifiable = 11;
        PowerTool oldDrill = toolList.get(indexModifiable);
        PowerTool drill = new Drill(15L, "Bosch 150", 2498, 1100, 2500);

        toolList.set(indexModifiable, drill);

        assertFalse(toolList.contains(oldDrill));
    }

    @Test(expected = UnmodifiablePartModificationException.class)
    public void shouldReturnExceptionWhenSetElementByIndexIntoUnmodifiable() {
        int indexUnmodifiable = 5;
        PowerTool drill = new Drill(15L, "Bosch 150", 2498, 1100, 2500);

        toolList.set(indexUnmodifiable, drill);
    }

    @Test
    public void shouldReturnIndexOfElementThatNotContains() {
        PowerTool drill = new Drill(20L, "Bosch 200", 2498, 1100, 2500);
        int indexExpected = -1;

        assertEquals(indexExpected, toolList.indexOf(drill));
    }

    @Test
    public void shouldReturnLastIndexOfDoubledElement() {
        PowerTool drill = new Drill(17L, "Bosch 170", 1999, 1500, 3000);
        toolList.add(drill);
        int indexExpected = toolList.size() - 1;

        assertEquals(indexExpected, toolList.lastIndexOf(drill));
    }

    @Test
    public void shouldReturnArraySameSizeAsCollectionWhenToArray() {
        PowerTool[] array = toolList.toArray();

        assertEquals(toolList.size(), array.length);
    }

    @Test(expected = UnmodifiablePartModificationException.class)
    public void shouldReturnExceptionAfterClearWithUnmodifiablePartHasElements() {
        toolList.clear();

    }

    @Test(expected = UnsupportedOperationException.class)
    public void shouldReturnUnsupportedOperationExceptionWhenSublist() {
        toolList.subList(1, 3);
    }

    @Test(expected = UnsupportedOperationException.class)
    public void shouldReturnUnsupportedOperationExceptionWhenListIterator() {
        toolList.listIterator();
    }

    @Test(expected = UnsupportedOperationException.class)
    public void shouldReturnUnsupportedOperationExceptionWhenListIteratorByIndex() {
        toolList.listIterator(3);
    }

}
