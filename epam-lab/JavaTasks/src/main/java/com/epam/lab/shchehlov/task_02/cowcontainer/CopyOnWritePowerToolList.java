package com.epam.lab.shchehlov.task_02.cowcontainer;

import com.epam.lab.shchehlov.task_01.entity.PowerTool;

import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.NoSuchElementException;
import java.util.function.Predicate;

/**
 * Resizable-array implementation of the {@code List} interface.  Implements
 * all necessary list operations and permits all elements inherited from {@code PowerTool.class}, including
 * {@code null}
 *
 * @author B.Shchehlov
 */

public class CopyOnWritePowerToolList implements List<PowerTool> {

    private static final String ILLEGAL_CAPACITY = "Illegal Capacity";
    private static final String INDEX_SIZE = "Index - %d, size - %d";
    private static final int DEFAULT_CAPACITY = 10;
    private static final int DEFAULT_ELEMENT = -1;

    private int size = 0;
    private int capacity;
    private PowerTool[] toolArray;

    public CopyOnWritePowerToolList() {
        this.capacity = DEFAULT_CAPACITY;
        this.toolArray = new PowerTool[this.capacity];
    }

    public CopyOnWritePowerToolList(int initCapacity) {
        if (initCapacity >= 0) {
            this.capacity = initCapacity;
            this.toolArray = new PowerTool[this.capacity];
        } else {
            throw new IllegalArgumentException(ILLEGAL_CAPACITY);
        }
    }

    /**
     * Returns the number of elements in this list.
     *
     * @return the number of elements in this list
     */
    @Override
    public int size() {
        return size;
    }

    /**
     * Returns {@code true} if this list contains no elements.
     *
     * @return {@code true} if this list contains no elements
     */
    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * Returns {@code true} if this list contains the specified element.
     *
     * @param object element whose presence in this list is to be tested
     * @return {@code true} if this list contains the specified element
     */
    @Override
    public boolean contains(Object object) {
        return indexOf(object) >= 0;
    }

    /**
     * Returns an iterator over the elements in this list in proper sequence.
     * The iterator is resistant to external changes.
     *
     * @return an iterator over the elements in this list in proper sequence
     */
    @Override
    public Iterator<PowerTool> iterator() {
        return new IteratorWithCondition(elements -> true);
    }

    /**
     * Returns an iterator over the elements in this list in proper sequence
     * that satisfy a certain condition.The iterator is resistant to external changes.
     *
     * @param predicate condition that an element must satisfy
     * @return an iterator over the elements in this list in proper sequence
     * that satisfy a certain condition.
     */
    public Iterator<PowerTool> iteratorWithCondition(Predicate<PowerTool> predicate) {
        return new IteratorWithCondition(predicate);
    }

    private class IteratorWithCondition implements Iterator<PowerTool> {
        private int cursor = DEFAULT_ELEMENT;
        private final Predicate<PowerTool> predicate;
        private final PowerTool[] iteratorArray;

        public IteratorWithCondition(Predicate<PowerTool> predicate) {
            this.predicate = predicate;
            iteratorArray = toArray();
        }

        @Override
        public boolean hasNext() {
            for (int next = cursor + 1; next < iteratorArray.length; next++) {
                if (predicate.test(iteratorArray[next])) {
                    return true;
                }
            }
            return false;
        }

        @Override
        public PowerTool next() {
            do {
                if (cursor > iteratorArray.length - 1) {
                    throw new NoSuchElementException();
                }
                cursor++;
            } while (!predicate.test(iteratorArray[cursor]));
            return iteratorArray[cursor];
        }
    }

    /**
     * Returns an array containing all of the elements in this list
     * in proper sequence (from first to last element).
     *
     * @return an array containing all of the elements in this list in
     * proper sequence
     */
    @Override
    public PowerTool[] toArray() {
        return Arrays.copyOf(this.toolArray, this.size);
    }

    /**
     * Returns an array containing all of the elements in this list in
     * proper sequence (from first to last element).
     *
     * @param array the array into which the elements of this list are to
     *              be stored.
     * @return an array containing the elements of this list.
     */
    @Override
    public <T> T[] toArray(T[] array) {
        if (array.length < size) {
            return (T[]) Arrays.copyOf(this.toolArray, this.size, array.getClass());
        }
        System.arraycopy(toolArray, 0, array, 0, size);
        return array;
    }

    /**
     * Appends the specified element to the end of this list.
     *
     * @param powerTool element to be appended to this list
     * @return {@code true} (as specified by {@link Collection#add})
     */
    @Override
    public boolean add(PowerTool powerTool) {
        add(size, powerTool);
        return true;
    }

    /**
     * Removes the first occurrence of the specified element from this list,
     * if it is present.
     *
     * @param object element to be removed from this list, if present
     * @return {@code true} if this list contained the specified element
     */
    @Override
    public boolean remove(Object object) {
        if (!contains(object)) {
            return false;
        }
        remove(indexOf(object));
        return true;
    }

    /**
     * Returns {@code true} if this list contains all of the elements of the
     * specified collection.
     *
     * @param collection collection to be checked for containment in this list
     * @return {@code true} if this list contains all of the elements of the
     * specified collection
     */
    @Override
    public boolean containsAll(Collection<?> collection) {
        boolean result;
        result = true;
        for (Object element : collection) {
            if (!contains(element)) {
                result = false;
                break;
            }
        }
        return result;
    }

    /**
     * Appends all of the elements in the specified collection to the end of
     * this list, in the order that they are returned by the specified
     * collection's iterator.
     *
     * @param collection collection containing elements to be added to this list
     * @return {@code true} if this list changed as a result of the call
     */
    @Override
    public boolean addAll(Collection<? extends PowerTool> collection) {
        return addAll(size, collection);
    }

    /**
     * Inserts all of the elements in the specified collection into this
     * list at the specified position.
     *
     * @param index      index at which to insert the first element from the
     *                   specified collection
     * @param collection collection containing elements to be added to this list
     * @return {@code true} if this list changed as a result of the call
     */
    @Override
    public boolean addAll(int index, Collection<? extends PowerTool> collection) {
        if (collection == null) {
            return false;
        }
        PowerTool[] array = getCurrentElements();
        validateIndex(index);
        int collectionSize = collection.size();
        System.arraycopy(array, index, array, index + collectionSize, size - index);
        System.arraycopy(collection.toArray(), 0, array, index, collectionSize);
        size += collectionSize;
        setChangedElements(array);
        return true;
    }

    /**
     * Removes from this list all of its elements that are contained in the
     * specified collection.
     *
     * @param collection collection containing elements to be removed from this list
     * @return {@code true} if this list changed as a result of the call
     */
    @Override
    public boolean removeAll(Collection<?> collection) {
        return removeRetainProcess(true, collection);
    }

    /**
     * Retains only the elements in this list that are contained in the
     * specified collection.
     *
     * @param collection collection containing elements to be retained in this list
     * @return {@code true} if this list changed as a result of the call
     */
    @Override
    public boolean retainAll(Collection<?> collection) {
        return removeRetainProcess(false, collection);
    }

    /**
     * Helper method for methods {@code removeAll} (Removes from this list all of its elements
     * that are contained in the specified collection) and {@code retainAll} (Retains only the
     * elements in this list that are contained in the specified collection).
     *
     * @param isRemove   {@code true} for method {@code removeAll},  {@code false} for method {@code retainAll}
     * @param collection collection containing elements to be retained in this list
     * @return {@code true} if deleted for method {@code removeAll} and if retained for method {@code retainAll}
     */
    private boolean removeRetainProcess(boolean isRemove, Collection<?> collection) {
        boolean result = false;
        for (int i = 0; i < size; i++) {
            if (collection.contains(get(i)) == isRemove) {
                remove(i);
                i--;
                result = true;
            }
        }
        return result;
    }

    /**
     * Removes all of the elements from this list.
     * The list will be empty after this call returns.
     */
    @Override
    public void clear() {
        PowerTool[] array = getCurrentElements();
        for (int i = 0; i < size; i++) {
            array[i] = null;
        }
        setChangedElements(array);
        size = 0;
    }

    /**
     * Returns the element at the specified position in this list.
     *
     * @param index index of the element to return
     * @return the element at the specified position in this list
     * @throws IndexOutOfBoundsException if the index is out of range
     *                                   ({@code index < 0 || index >= size()})
     */
    @Override
    public PowerTool get(int index) {
        validateIndex(index);
        PowerTool[] array = getCurrentElements();
        return array[index];
    }

    /**
     * Replaces the element at the specified position in this list with the
     * specified element.
     *
     * @param index   index of the element to replace
     * @param element element to be stored at the specified position
     * @return the element previously at the specified position
     * @throws IndexOutOfBoundsException if the index is out of range
     *                                   ({@code index < 0 || index >= size()})
     */
    @Override
    public PowerTool set(int index, PowerTool element) {
        validateIndex(index);
        PowerTool[] array = getCurrentElements();
        PowerTool result = get(index);
        array[index] = element;
        setChangedElements(array);
        return result;
    }

    /**
     * Inserts the specified element at the specified position in this list.
     *
     * @param index   index at which the specified element is to be inserted
     * @param element element to be inserted
     * @throws IndexOutOfBoundsException if the index is out of range
     *                                   ({@code index < 0 || index > size()})
     */
    @Override
    public void add(int index, PowerTool element) {
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException(String.format(INDEX_SIZE, index, size));
        }
        if (size == capacity) {
            increaseCapacity();
        }
        PowerTool[] array = getCurrentElements();
        if (index == size) {
            array[size] = element;
        } else {
            System.arraycopy(array, index, array, index + 1, size - index);
            array[index] = element;
        }
        setChangedElements(array);
        size++;
    }

    /**
     * Removes the element at the specified position in this list.
     *
     * @param index the index of the element to be removed
     * @return the element previously at the specified position
     * @throws IndexOutOfBoundsException if the index is out of range
     *                                   ({@code index < 0 || index >= size()})
     */
    @Override
    public PowerTool remove(int index) {
        validateIndex(index);
        PowerTool[] array = getCurrentElements();
        PowerTool result = array[index];
        System.arraycopy(array, index + 1, array, index, size - index - 1);
        array[--size] = null;
        setChangedElements(array);
        return result;
    }

    /**
     * Returns the index of the first occurrence of the specified element
     * in this list.
     *
     * @param object element to search for
     * @return the index of the first occurrence of the specified element in
     * this list, or -1 if this list does not contain the element
     */
    @Override
    public int indexOf(Object object) {
        int result = DEFAULT_ELEMENT;
        PowerTool[] array = getCurrentElements();
        for (int i = 0; i < size; i++) {
            if (array[i].equals(object)) {
                result = i;
                break;
            }
        }
        setChangedElements(array);
        return result;
    }

    /**
     * Returns the index of the last occurrence of the specified element
     * in this list.
     *
     * @param object element to search for
     * @return the index of the last occurrence of the specified element in
     * this list, or -1 if this list does not contain the element
     */
    @Override
    public int lastIndexOf(Object object) {
        int result = DEFAULT_ELEMENT;
        PowerTool[] array = getCurrentElements();
        for (int i = size - 1; i > 0; i--) {
            if (array[i].equals(object)) {
                result = i;
                break;
            }
        }
        setChangedElements(array);
        return result;
    }

    /**
     * Not implemented method
     *
     * @throws UnsupportedOperationException
     */
    @Override
    public ListIterator<PowerTool> listIterator() {
        throw new UnsupportedOperationException();
    }

    /**
     * Not implemented method
     *
     * @throws UnsupportedOperationException
     */
    @Override
    public ListIterator<PowerTool> listIterator(int index) {
        throw new UnsupportedOperationException();
    }

    /**
     * Not implemented method
     *
     * @throws UnsupportedOperationException
     */
    @Override
    public List<PowerTool> subList(int fromIndex, int toIndex) {
        throw new UnsupportedOperationException();
    }

    /**
     * Increases {@code capacity} increases capacity of {@code toolArray} by about two-thirds
     *
     * @return {@code toolArray} containing the same elements in the same order,
     * but with increased capacity
     */
    private void increaseCapacity() {
        capacity = capacity * 3 / 2 + 1;
        PowerTool[] newToolArray = new PowerTool[capacity];
        System.arraycopy(toolArray, 0, newToolArray, 0, size);
        toolArray = newToolArray;
    }

    /**
     * Checks if the index specified in the parameter is within the limits of the current collection
     *
     * @param index the index to be checked
     * @throws IndexOutOfBoundsException if the index is out of range
     *                                   ({@code index < 0 || index >= size()})
     */
    private void validateIndex(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException(String.format(INDEX_SIZE, index, size));
        }
    }

    /**
     * Creates copy of all elements to avoid changing it during the execution of the method
     *
     * @return an array containing the elements of this list
     */
    private PowerTool [] getCurrentElements(){
        return Arrays.copyOf(toolArray, toolArray.length);
    }

    /**
     * Updates this list with method changes.
     *
     * @param array resulting from method actions
     */
    private void setChangedElements (PowerTool [] array){
        toolArray = array;
    }
}
