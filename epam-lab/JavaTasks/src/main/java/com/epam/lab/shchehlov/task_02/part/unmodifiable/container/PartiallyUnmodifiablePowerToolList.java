package com.epam.lab.shchehlov.task_02.part.unmodifiable.container;

import com.epam.lab.shchehlov.task_01.container.PowerToolList;
import com.epam.lab.shchehlov.task_01.entity.PowerTool;
import com.epam.lab.shchehlov.task_02.part.unmodifiable.container.exception.UnmodifiablePartModificationException;

import java.util.*;
import java.util.function.Predicate;

/**
 * Resizable-array implementation of the {@code List} interface.  Implements
 * all necessary list operations and permits all elements inherited from {@code PowerTool.class}, including
 * {@code null}. Has two parts, 1-st - unmodifiable, 2-nd - modifiable.
 *
 * @author B.Shchehlov
 */

public class PartiallyUnmodifiablePowerToolList implements List<PowerTool> {

    private static final String INDEX_SIZE = "Index - %d, size - %d";
    private static final String REMOVE_UNMODIFIED = "Removal from the unmodified part";
    private static final String UNMODIFIED_HAS_ELEMENT = "Unmodifiable part contains element - %s";
    private static final String UNMODIFIED_HAS_ELEMENTS = "Unmodifiable part contains elements";
    private static final String UNMODIFIED_INDEX_SIZE = "Index - %d, unmodifiable part size - %d";
    private static final int DEFAULT_ELEMENT = -1;

    private final List<PowerTool> unmodifiablePart;
    private List<PowerTool> modifiablePart;

    public PartiallyUnmodifiablePowerToolList(List<PowerTool> unmodifiable) {
        unmodifiablePart = Collections.unmodifiableList(unmodifiable);
        modifiablePart = new PowerToolList();
    }

    /**
     * Returns the number of elements in this list.
     *
     * @return the number of elements in this list
     */
    @Override
    public int size() {
        return unmodifiablePart.size() + modifiablePart.size();
    }

    /**
     * Returns {@code true} if this list contains no elements.
     *
     * @return {@code true} if this list contains no elements
     */
    @Override
    public boolean isEmpty() {
        return size() == 0;
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
     */
    public Iterator<PowerTool> iteratorWithCondition(Predicate<PowerTool> predicate) {
        return new IteratorWithCondition(predicate);
    }

    private class IteratorWithCondition implements Iterator<PowerTool> {
        private int cursor = DEFAULT_ELEMENT;
        private final Predicate<PowerTool> predicate;

        public IteratorWithCondition(Predicate<PowerTool> predicate) {
            this.predicate = predicate;
        }

        @Override
        public boolean hasNext() {
            for (int next = cursor + 1; next < size(); next++) {
                if (next < unmodifiablePart.size()) {
                    predicate.test(unmodifiablePart.get(next));
                    return true;
                } else if (predicate.test(modifiablePart.get(next - unmodifiablePart.size()))) {
                    return true;
                }
            }
            return false;
        }

        @Override
        public PowerTool next() {
            do {
                if (cursor > size() - 1) {
                    throw new NoSuchElementException();
                }
                cursor++;
            } while (!predicate.test(get(cursor)));
            return get(cursor);
        }
    }

    /**
     * Returns an array containing all of the elements in this list
     * in proper sequence.
     *
     * @return an array containing all of the elements in this list in
     * proper sequence
     */
    @Override
    public PowerTool[] toArray() {
        PowerTool[] toolArray = new PowerTool[size()];
        System.arraycopy(unmodifiablePart.toArray(), 0, toolArray, 0, unmodifiablePart.size());
        System.arraycopy(modifiablePart.toArray(), 0, toolArray, unmodifiablePart.size(), modifiablePart.size());
        return toolArray;
    }

    /**
     * Returns an array containing all of the elements in this list in
     * proper sequence.
     *
     * @param array the array into which the elements of this list are to
     *              be stored.
     * @return an array containing the elements of this list
     */
    @Override
    public <T> T[] toArray(T[] array) {
        if (array.length < size()) {
            return (T[]) Arrays.copyOf(toArray(), size(), array.getClass());
        }
        System.arraycopy(toArray(), 0, array, 0, size());
        return array;
    }

    /**
     * Appends the specified element to the end of this list (in the end
     * of modifiable part).
     *
     * @param powerTool element to be appended to this list
     * @return {@code true} (as specified by {@link Collection#add})
     */
    @Override
    public boolean add(PowerTool powerTool) {
        modifiablePart.add(powerTool);
        return true;
    }

    /**
     * Removes the first occurrence of the specified element from the modifiable
     * part of this list, if it is present.
     *
     * @param object element to be removed from this list, if present
     * @return {@code true} if this list contained the specified element
     * @throws UnmodifiablePartModificationException if the element belongs
     *                                               to unmodifiable part
     */
    @Override
    public boolean remove(Object object) {
        if (unmodifiablePart.contains(object)) {
            throw new UnmodifiablePartModificationException(REMOVE_UNMODIFIED);
        }
        return modifiablePart.remove(object);
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
        for (Object element : collection) {
            if (!unmodifiablePart.contains(element) && !modifiablePart.contains(element)) {
                return false;
            }
        }
        return true;
    }

    /**
     * Appends all of the elements in the specified collection to the end of
     * the modifiable part of this list, in the order that they are returned
     * by the specified collection's iterator.
     *
     * @param collection collection containing elements to be added to this list
     * @return {@code true} if this list changed as a result of the call
     */
    @Override
    public boolean addAll(Collection<? extends PowerTool> collection) {
        return addAll(size(), collection);
    }

    /**
     * Inserts all of the elements in the specified collection into the
     * modifiable part of this list at the specified position
     *
     * @param index      index at which to insert the first element from the
     *                   specified collection
     * @param collection collection containing elements to be added to this list
     * @return {@code true} if this list changed as a result of the call
     * @throws UnmodifiablePartModificationException if {@code index} belongs
     *                                               to the unmodifiable part
     * @throws IndexOutOfBoundsException             if the index is out of range
     *                                               ({@code index < 0 || index >= size()})
     */
    @Override
    public boolean addAll(int index, Collection<? extends PowerTool> collection) {
        validateIndex(index);
        validateUnmodifiablePartIndex(index);
        return modifiablePart.addAll(index - unmodifiablePart.size(), collection);
    }

    /**
     * Removes from the modifiable part of this list all of its elements that are
     * contained in the specified collection.
     *
     * @param collection collection containing elements to be removed from this list
     * @return {@code true} if this list changed as a result of the call
     * @throws UnmodifiablePartModificationException if at least one element of specified
     *                                               collection contained in the unmodifiable
     *                                               part
     */
    @Override
    public boolean removeAll(Collection<?> collection) {
        return removeRetainProcess(true, collection);
    }

    /**
     * Retains all elements in unmodifiable part and only the elements in the
     * modifiable part of this list that are contained in the specified collection.
     *
     * @param collection collection containing elements to be retained in this list
     * @return {@code true} if this list changed as a result of the call
     * @throws UnmodifiablePartModificationException if at least one element of
     *                                               unmodifiable part doesn't
     *                                               contains in the specified
     *                                               collection
     */
    @Override
    public boolean retainAll(Collection<?> collection) {
        return removeRetainProcess(false, collection);
    }

    /**
     * Helper method for methods {@code removeAll} (Removes from the modifiable part of this list all of its elements
     * that are contained in the specified collection) and {@code retainAll} (Retains only the
     * elements in this list that are contained in the specified collection).
     *
     * @param isRemove   {@code true} for method {@code removeAll},  {@code false} for method {@code retainAll}
     * @param collection collection containing elements to be retained in this list
     * @return {@code true} if deleted for method {@code removeAll} and if retained for method {@code retainAll}
     * @throws UnmodifiablePartModificationException if at least one element of unmodifiable part doesn't
     *                                               contains in the specified collection
     */
    private boolean removeRetainProcess(boolean isRemove, Collection<?> collection) {
        for (PowerTool element : unmodifiablePart) {
            if (collection.contains(element) == isRemove) {
                throw new UnmodifiablePartModificationException(String.format(UNMODIFIED_HAS_ELEMENT, element));
            }
        }
        if (isRemove) {
            return modifiablePart.removeAll(collection);
        }
        return modifiablePart.retainAll(collection);
    }

    /**
     * Removes all of the elements from this list if it doesn't have elements at the unmodifiable part.
     * The list will be empty after this call returns.
     *
     * @throws UnmodifiablePartModificationException in case unmodifiable part is not empty
     */
    @Override
    public void clear() {
        if (!unmodifiablePart.isEmpty()) {
            throw new UnmodifiablePartModificationException(UNMODIFIED_HAS_ELEMENTS);
        }
        modifiablePart.clear();
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
        if (index < unmodifiablePart.size()) {
            return unmodifiablePart.get(index);
        }
        return modifiablePart.get(index - unmodifiablePart.size());
    }

    /**
     * Replaces the element at the specified position in the modifiable part of
     * this list with the specified element.
     *
     * @param index   index of the element to replace
     * @param element element to be stored at the specified position
     * @return the element previously at the specified position
     * @throws IndexOutOfBoundsException             if the index is out of range
     *                                               ({@code index < 0 || index >= size()})
     * @throws UnmodifiablePartModificationException if {@code index} belongs
     *                                               to the unmodifiable part
     */
    @Override
    public PowerTool set(int index, PowerTool element) {
        validateIndex(index);
        validateUnmodifiablePartIndex(index);
        return modifiablePart.set(index - unmodifiablePart.size(), element);
    }

    /**
     * Inserts the specified element at the specified position in the
     * modifiable part of this list.
     *
     * @param index   index at which the specified element is to be inserted
     * @param element element to be inserted
     * @throws IndexOutOfBoundsException             if the index is out of range
     *                                               ({@code index < 0 || index > size()})
     * @throws UnmodifiablePartModificationException if {@code index} belongs
     *                                               to the unmodifiable part
     */
    @Override
    public void add(int index, PowerTool element) {
        if (index < 0 || index > size()) {
            throw new IndexOutOfBoundsException(String.format(INDEX_SIZE, index, size()));
        }
        validateUnmodifiablePartIndex(index);
        modifiablePart.add(index - unmodifiablePart.size(), element);
    }

    /**
     * Removes the element from the specified position in the modifiable part of
     * this list.
     *
     * @param index the index of the element to be removed
     * @return the element previously at the specified position
     * @throws IndexOutOfBoundsException             if the index is out of range
     *                                               ({@code index < 0 || index >= size()})
     * @throws UnmodifiablePartModificationException if {@code index} belongs
     *                                               to the unmodifiable part
     */
    @Override
    public PowerTool remove(int index) {
        validateIndex(index);
        validateUnmodifiablePartIndex(index);
        return modifiablePart.remove(index - unmodifiablePart.size());
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
        if (unmodifiablePart.indexOf(object) >= 0) {
            return unmodifiablePart.indexOf(object);
        }
        if (modifiablePart.indexOf(object) >= 0) {
            return modifiablePart.indexOf(object) + unmodifiablePart.size();
        }
        return DEFAULT_ELEMENT;
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
        if (modifiablePart.lastIndexOf(object) >= 0) {
            return modifiablePart.lastIndexOf(object) + unmodifiablePart.size();
        }
        if (unmodifiablePart.lastIndexOf(object) >= 0) {
            return unmodifiablePart.lastIndexOf(object);
        }
        return DEFAULT_ELEMENT;
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
     * Checks if the index specified in the parameter is within the limits of the current collection
     *
     * @param index the index to be checked
     * @throws IndexOutOfBoundsException if the index is out of range
     *                                   ({@code index < 0 || index >= size()})
     */
    private void validateIndex(int index) {
        if (index < 0 || index >= size()) {
            throw new IndexOutOfBoundsException(String.format(INDEX_SIZE, index, size()));
        }
    }

    /**
     * Checks if the index specified in the parameter is within the limits of the unmodifiable part
     *
     * @param index the index to be checked
     * @throws IndexOutOfBoundsException             if the index is out of range
     *                                               ({@code index < 0 || index >= size()})
     *
     * @throws UnmodifiablePartModificationException if {@code index} belongs
     *                                               to the unmodifiable part
     */
    private void validateUnmodifiablePartIndex(int index) {
        if (index < unmodifiablePart.size()) {
            throw new UnmodifiablePartModificationException(String.format(UNMODIFIED_INDEX_SIZE, index, unmodifiablePart.size()));
        }
    }
}
