package com.epam.lab.shchehlov.task_01.container;

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

public class PowerToolList implements List<PowerTool> {

    private static final String ILLEGAL_CAPACITY = "Illegal Capacity";
    private static final String INDEX_SIZE = "Index - %d, size - %d";
    private static final int DEFAULT_CAPACITY = 10;
    private static final int DEFAULT_ELEMENT = -1;

    private int size = 0;
    private int capacity;
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
     * @throws ClassCastException if the type of the specified element
     *                            is incompatible with this list
     */
    @Override
    public boolean contains(Object object) {
        return indexOf(object) >= 0;
    }

    /**
     * Returns an iterator over the elements in this list in proper sequence.
     *
     * @return an iterator over the elements in this list in proper sequence
     */
    @Override
    public Iterator<PowerTool> iterator() {
        return new IteratorWithCondition(elements -> true);
    }

    /**
     * Returns an iterator over the elements in this list in proper sequence
     * that satisfy a certain condition.
     *
     * @param predicate condition that an element must satisfy
     * @return an iterator over the elements in this list in proper sequence
     * that satisfy a certain condition.
     * @throws NoSuchElementException if the {@code cursor} is out of range
     * @throws IllegalStateException  if the method {@code remove} is called without first calling {@code hasNext}
     */
    public Iterator<PowerTool> iteratorWithCondition(Predicate<PowerTool> predicate) {
        return new IteratorWithCondition(predicate);
    }

    private class IteratorWithCondition implements Iterator<PowerTool> {
        private int cursor = DEFAULT_ELEMENT;
        private int lastElement = DEFAULT_ELEMENT;
        private final Predicate<PowerTool> predicate;

        public IteratorWithCondition(Predicate<PowerTool> predicate) {
            this.predicate = predicate;
        }

        @Override
        public boolean hasNext() {
            for (int next = cursor + 1; next < size; next++) {
                if (predicate.test(toolArray[next])) {
                    return true;
                }
            }
            return false;
        }

        @Override
        public PowerTool next() {
            do {
                if (cursor > size - 1) {
                    throw new NoSuchElementException();
                }
                cursor++;
            } while (!predicate.test(toolArray[cursor]));
            return toolArray[cursor];
        }

        @Override
        public void remove() {
            if (lastElement < 0) {
                throw new IllegalStateException();
            }
            PowerToolList.this.remove(lastElement);
            cursor = lastElement;
            lastElement = DEFAULT_ELEMENT;
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
     * proper sequence (from first to last element); the runtime type of
     * the returned array is that of the specified array.  If the list fits
     * in the specified array, it is returned therein.  Otherwise, a new
     * array is allocated with the runtime type of the specified array and
     * the size of this list.
     *
     * @param array the array into which the elements of this list are to
     *              be stored, if it is big enough; otherwise, a new array of the
     *              same runtime type is allocated for this purpose.
     * @return an array containing the elements of this list
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
     * if it is present.  If this list does not contain
     * the element, it is unchanged.
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
     * list at the specified position (optional operation).  Shifts the
     * element currently at that position (if any) and any subsequent
     * elements to the right (increases their indices).  The new elements
     * will appear in this list in the order that they are returned by the
     * specified collection's iterator.  The behavior of this operation is
     * undefined if the specified collection is modified while the
     * operation is in progress.
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
        validateIndex(index);
        int collectionSize = collection.size();
        System.arraycopy(toolArray, index, toolArray, index + collectionSize, size - index);
        System.arraycopy(collection.toArray(), 0, toolArray, index, collectionSize);
        size += collectionSize;
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
        for (int i = 0; i < size; i++) {
            toolArray[i] = null;
        }
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
        return toolArray[index];
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
        PowerTool result = get(index);
        toolArray[index] = element;
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
        if (index == size) {
            toolArray[size] = element;
        } else {
            System.arraycopy(toolArray, index, toolArray, index + 1, size - index);
            toolArray[index] = element;
        }
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
        PowerTool result = toolArray[index];
        System.arraycopy(toolArray, index + 1, toolArray, index, size - index - 1);
        toolArray[--size] = null;
        return result;
    }

    /**
     * Returns the index of the first occurrence of the specified element
     * in this list, or -1 if this list does not contain the element.
     *
     * @param object element to search for
     * @return the index of the first occurrence of the specified element in
     * this list, or -1 if this list does not contain the element
     */
    @Override
    public int indexOf(Object object) {
        int result = -1;
        for (int i = 0; i < size; i++) {
            if (toolArray[i].equals(object)) {
                result = i;
                break;
            }
        }
        return result;
    }

    /**
     * Returns the index of the last occurrence of the specified element
     * in this list, or -1 if this list does not contain the element.
     *
     * @param object element to search for
     * @return the index of the last occurrence of the specified element in
     * this list, or -1 if this list does not contain the element
     */
    @Override
    public int lastIndexOf(Object object) {
        int result = DEFAULT_ELEMENT;
        for (int i = size - 1; i > 0; i--) {
            if (toolArray[i].equals(object)) {
                result = i;
                break;
            }
        }
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
}
