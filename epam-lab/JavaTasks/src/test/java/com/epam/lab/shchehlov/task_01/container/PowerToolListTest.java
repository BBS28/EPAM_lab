package com.epam.lab.shchehlov.task_01.container;

import com.epam.lab.shchehlov.task_01.entity.Drill;
import com.epam.lab.shchehlov.task_01.entity.PowerTool;
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

public class PowerToolListTest {
    private PowerToolList toolList;

    @Before
    public void before() {
        toolList = new PowerToolList();
        toolList.add(new Drill(1L, "Bosch 10", 2098, 1000, 2400));
        toolList.add(new Drill(2L, "Bosch 20", 2598, 1400, 2800));
        toolList.add(new Drill(3L, "Bosch 30", 3000, 1200, 2900));
        toolList.add(new Drill(4L, "Bosch 40", 1500, 1000, 2000));
        toolList.add(new Drill(5L, "Bosch 50", 1098, 800, 1800));
        toolList.add(new Drill(6L, "Bosch 60", 3500, 1200, 3200));
        toolList.add(new Drill(7L, "Bosch 70", 1999, 1500, 3000));
    }

    @Test
    public void shouldReturnTrueWhenAddedNewTool() {
        PowerTool drill = new Drill(10L, "Bosch 100", 2098, 1000, 2400);

        assertTrue(toolList.add(drill));
    }

    @Test
    public void shouldReturnNullWhenAddedNull() {
        toolList.add(null);

        assertNull(toolList.get(7));
    }

    @Test
    public void shouldReturnElementAddedByIndex() {
        PowerTool drill = new Drill(10L, "Bosch 100", 2098, 1000, 2400);

        toolList.add(2, drill);

        assertEquals(drill, toolList.get(2));
    }

    @Test
    public void shouldReturnNullWhenNullAddedByIndex() {
        toolList.add(2, null);

        assertNull(toolList.get(2));
    }

    @Test
    public void shouldReturnElementAddedByIndexInTheBeginning() {
        PowerTool drill = new Drill(10L, "Bosch 100", 2098, 1000, 2400);

        toolList.add(0, drill);

        assertEquals(drill, toolList.get(0));
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
    public void shouldNotReturnElementAfterRemovedByIndex() {
        PowerTool drill = toolList.get(4);

        toolList.remove(4);

        assertNotEquals(drill, toolList.get(4));
    }

    @Test
    public void shouldNotReturnElementAfterRemovedByFirstIndex() {
        PowerTool drill = toolList.get(0);

        toolList.remove(0);

        assertNotEquals(drill, toolList.get(0));
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
    public void shouldReturnFalseAfterRemovedByObject() {
        Drill drill = new Drill(5L, "Bosch 50", 1098, 800, 1800);

        toolList.remove(drill);

        assertFalse(toolList.contains(drill));
    }

    @Test
    public void shouldReturnListContainsAllObjectsAfterIteration() {
        Iterator<PowerTool> iterator = toolList.iterator();
        List<PowerTool> tools = new PowerToolList();

        while (iterator.hasNext()) {
            tools.add(iterator.next());
        }

        assertEquals(7, tools.size());
    }

    @Test(expected = IllegalStateException.class)
    public void shouldThrowExceptionWhenRemoveWithoutHasNext() {
        Iterator<PowerTool> iterator = toolList.iterator();

        iterator.remove();
    }

    @Test
    public void shouldReturnFalseWhenRemovedWithCondition() {
        PowerTool tool = toolList.get(5);
        Predicate<PowerTool> p = (PowerTool powerTool) -> powerTool.getId() == 6L;
        Iterator<PowerTool> iterator = toolList.iteratorWithCondition(p);

        while (iterator.hasNext()) {
            toolList.remove(iterator.next());
        }

        assertFalse(toolList.contains(tool));
    }

    @Test
    public void shouldReturnListContainsThreeObjectsAfterIterationWithCondition() {
        Predicate<PowerTool> p = (PowerTool powerTool) -> powerTool.getPrice() > 2500;
        Iterator<PowerTool> iterator = toolList.iteratorWithCondition(p);
        List<PowerTool> tools = new PowerToolList();

        while (iterator.hasNext()) {
            tools.add(iterator.next());
        }

        assertEquals(3, tools.size());
    }

    @Test
    public void shouldRemovePreviousElementByIndexWhenSetByIndex() {
        PowerTool oldDrill = toolList.get(5);
        PowerTool drill = new Drill(15L, "Bosch 150", 2498, 1100, 2500);

        toolList.set(5, drill);

        assertFalse(toolList.contains(oldDrill));
    }

    @Test
    public void shouldSetElementByIndex() {
        PowerTool drill = new Drill(15L, "Bosch 150", 2498, 1100, 2500);

        toolList.set(5, drill);

        assertEquals(drill, toolList.get(5));
    }

    @Test
    public void sizeShouldReturnZeroAfterClear() {
        toolList.clear();

        assertEquals(0, toolList.size());
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
