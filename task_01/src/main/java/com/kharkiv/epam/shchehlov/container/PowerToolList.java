package com.kharkiv.epam.shchehlov.container;

import com.kharkiv.epam.shchehlov.entity.PowerTool;

import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.NoSuchElementException;
import java.util.function.Predicate;

public class PowerToolList<T extends PowerTool> implements List<PowerTool> {

    private int size = 0;
    private int capacity;
    private int DEFAULT_CAPACITY = 10;
    private final String ILLEGAL_CAPACITY = "Illegal Capacity";
    private final String ILLEGAL_INDEX = "Illegal Index";
    private final String UNSUPPORTED = "Unsupported method";
    private final String NOT_NULL = "Argument can't be null";
    private PowerTool[] toolArray;

    public PowerToolList() {
        this.capacity = DEFAULT_CAPACITY;
        this.toolArray = new PowerTool[this.capacity];
    }

    public PowerToolList(int initCapacity) {
        if (initCapacity >= 0) {
            this.capacity = initCapacity;
            this.toolArray = new PowerTool[this.capacity];
        } else {
            throw new IllegalArgumentException(ILLEGAL_CAPACITY);
        }
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public boolean contains(Object o) {
        return indexOf(o) >= 0;
    }

    @Override
    public Iterator<PowerTool> iterator() {
        return new IteratorImpl();
    }

    private class IteratorImpl implements Iterator<PowerTool> {
        private int cursor;

        @Override
        public boolean hasNext() {
            return cursor < size;
        }

        @Override
        public PowerTool next() {
            if (cursor < 0 || cursor > size - 1) {
                throw new NoSuchElementException();
            }
            return toolArray[cursor++];
        }
    }

    public Iterator<T> IteratorWithCondition(Predicate<T> predicate) {
        return new IteratorWithCondition(predicate);
    }

    private class IteratorWithCondition implements Iterator<T> {
        private int cursor;
        private Predicate<T> predicate;

        public IteratorWithCondition(Predicate<T> predicate) {
            this.predicate = predicate;
        }

        @Override
        public boolean hasNext() {
            for (int next = cursor + 1; next < size; next++) {
                if (predicate.test((T) toolArray[next])) {
                    return true;
                }
            }
            return false;
        }

        @Override
        public T next() {
            do {
                if (cursor > size - 1) {
                    throw new NoSuchElementException();
                }
                cursor++;
            } while (!predicate.test((T) toolArray[cursor]));
            return (T) toolArray[cursor];
        }
    }

    @Override
    public Object[] toArray() {
        return Arrays.copyOf(this.toolArray, this.size);
    }

    @Override
    public <T> T[] toArray(T[] a) {
        if (a.length < size) {
            return (T[]) toArray();
        }
        System.arraycopy(toolArray, 0, a, 0, size);
        return a;
    }

    @Override
    public boolean add(PowerTool powerTool) {
        checkForNull(powerTool);
        if (size == capacity) {
            increaseCapacity();
        }
        toolArray[size++] = powerTool;
        return true;
    }

    @Override
    public boolean remove(Object o) {
        checkForNull(o);
        boolean result;
        if (!contains(o)) {
            result = false;
        } else {
            System.arraycopy(toolArray, indexOf(o) + 1, toolArray, indexOf(o), size - indexOf(o) - 1);
            toolArray[--size] = null;
            result = true;
        }
        return result;
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        checkForNull(c);
        boolean result;
        Object temp;
        result = true;
        for (int i = 0; i < c.size(); i++) {
            temp = c.iterator().next();
            if (!contains(temp)) {
                result = false;
                break;
            }
        }
        return result;
    }

    @Override
    public boolean addAll(Collection<? extends PowerTool> c) {
        return false;
    }

    @Override
    public boolean addAll(int index, Collection<? extends PowerTool> c) {
        checkForNull(c);
        validateIndex(index);
        Object[] temp = c.toArray();
        for (int i = 0; i < c.size(); i++) {
            add(index + i, (T) temp[i]);
        }
        return true;
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        checkForNull(c);
        boolean result = false;
        for (int i = 0; i < size; i++) {
            if (c.contains(get(i))) {
                remove(i);
                i--;
                result = true;
            }
        }
        return result;
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        checkForNull(c);
        boolean result = false;
        for (int i = 0; i < size; i++) {
            if (!c.contains(get(i))) {
                remove(i);
                i--;
                result = true;
            }
        }
        return result;
    }

    @Override
    public void clear() {
        toolArray = new PowerTool[DEFAULT_CAPACITY];
        size = 0;
    }

    @Override
    public PowerTool get(int index) {
        validateIndex(index);
        return toolArray[index];
    }

    @Override
    public PowerTool set(int index, PowerTool element) {
        validateIndex(index);
        PowerTool result = toolArray[index];
        toolArray[index] = element;
        return result;
    }

    @Override
    public void add(int index, PowerTool element) {
        validateIndex(index);
        checkForNull(element);
        if (size == capacity) {
            increaseCapacity();
        }
        System.arraycopy(toolArray, index, toolArray, index + 1, size - index);
    }

    @Override
    public PowerTool remove(int index) {
        validateIndex(index);
        PowerTool result = toolArray[index];
        System.arraycopy(toolArray, index + 1, toolArray, index, size - index - 1);
        toolArray[--size] = null;
        return result;
    }

    @Override
    public int indexOf(Object o) {
        checkForNull(o);
        int result = -1;
        for (int i = 0; i < size; i++) {
            if (toolArray[i].equals(o)) {
                result = i;
                break;
            }
        }
        return result;
    }

    @Override
    public int lastIndexOf(Object o) {
        checkForNull(o);
        int result = -1;
        for (int i = size - 1; i > 0; i--) {
            if (toolArray[i].equals(o)) {
                result = i;
                break;
            }
        }
        return result;
    }

    @Override
    public ListIterator<PowerTool> listIterator() {
        throw new UnsupportedOperationException(UNSUPPORTED);
    }

    @Override
    public ListIterator<PowerTool> listIterator(int index) {
        throw new UnsupportedOperationException(UNSUPPORTED);
    }

    @Override
    public List<PowerTool> subList(int fromIndex, int toIndex) {
        throw new UnsupportedOperationException(UNSUPPORTED);
    }

    private void increaseCapacity() {
        capacity = capacity * 3 / 2 + 1;
        T[] newToolArray = (T[]) new Object[capacity];
        System.arraycopy(toolArray, 0, newToolArray, 0, size);
        toolArray = newToolArray;
    }

    private void validateIndex(int index) {
        if (index < 0 || index >= size) {
            throw new IllegalArgumentException(ILLEGAL_INDEX);
        }
    }

    private void checkForNull(Object o) {
        if (o == null) {
            throw new NullPointerException(NOT_NULL);
        }
    }
}
