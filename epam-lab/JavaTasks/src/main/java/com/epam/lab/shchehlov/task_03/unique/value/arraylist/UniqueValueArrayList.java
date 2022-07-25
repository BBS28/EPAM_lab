package com.epam.lab.shchehlov.task_03.unique.value.arraylist;

import com.epam.lab.shchehlov.task_03.unique.value.arraylist.exception.DuplicatedValueException;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.ListIterator;
import java.util.Set;

/**
 * An ArrayList implementation that only stores unique elements.
 *
 * @author B.Shchehlov
 */
public class UniqueValueArrayList<T> extends ArrayList<T> {

    /**
     * Replaces the element at the specified position of this list with the specified element.
     *
     * @param index   index of the element to replace
     * @param element element to be stored at the specified position
     * @return the element previously at the specified position
     * @throws DuplicatedValueException if list already contains this element
     */
    @Override
    public T set(int index, T element) {
        checkDoubleValue(element);
        return super.set(index, element);
    }

    /**
     * Appends the specified element to the end of this list.
     *
     * @param element element to be appended to this list
     * @return {@code true} (as specified by {@link Collection#add})
     * @throws DuplicatedValueException if list already contains this element
     */
    @Override
    public boolean add(T element) {
        checkDoubleValue(element);
        return super.add(element);
    }

    /**
     * Inserts the specified element at the specified position of this list.
     *
     * @param index   index at which the specified element is to be inserted
     * @param element element to be inserted
     * @throws DuplicatedValueException if list already contains this element
     */
    @Override
    public void add(int index, T element) {
        checkDoubleValue(element);
        super.add(index, element);
    }

    /**
     * Appends all the elements in the specified collection to the end of this list, in the order
     * that they are returned by the specified collection's iterator.
     *
     * @param collection collection containing elements to be added to this list
     * @return {@code true} if this list changed as a result of the call
     * @throws DuplicatedValueException if collection already contains element from this list
     */
    @Override
    public boolean addAll(Collection<? extends T> collection) {
        validateCollection(collection);
        checkDoubleValue(collection);
        return super.addAll(collection);
    }

    /**
     * Inserts all the elements in the specified collection into this list at the specified position.
     *
     * @param index      index at which to insert the first element from the
     *                   specified collection
     * @param collection collection containing elements to be added to this list
     * @return {@code true} if this list changed as a result of the call
     * @throws DuplicatedValueException if collection already contains element from this list
     */
    @Override
    public boolean addAll(int index, Collection<? extends T> collection) {
        validateCollection(collection);
        checkDoubleValue(collection);
        return super.addAll(index, collection);
    }

    /**
     * Checks if this element already exists in the list.
     *
     * @param object element to be checked
     * @throws DuplicatedValueException if list already contains this element
     */
    private void checkDoubleValue(T object) {
        if (contains(object)) {
            throw new DuplicatedValueException("Element already exists");
        }
    }

    /**
     * Checks if this collection already contains element from this list.
     *
     * @param collection collection to be checked
     * @throws DuplicatedValueException if collection already contains element from this list
     */
    private void checkDoubleValue(Collection<? extends T> collection) {
        for (Object object : collection) {
            if (contains(object)) {
                throw new DuplicatedValueException("Collection has element that already exists in this list");
            }
        }
    }

    /**
     * Checks if this collection contains repeating elements.
     *
     * @param collection collection to be checked
     * @throws DuplicatedValueException if collection contains repeating elements
     */
    private void validateCollection(Collection<? extends T> collection) {
        Set set = new HashSet(collection);
        if (set.size() < collection.size()) {
            throw new DuplicatedValueException("Collection has duplicates");
        }
    }

    /**
     * Unsupported operation.
     *
     * @throws UnsupportedOperationException when this method is called
     */
    @Override
    public ListIterator<T> listIterator(int index) {
        throw  new UnsupportedOperationException();
    }

    /**
     * Unsupported operation.
     *
     * @throws UnsupportedOperationException when this method is called
     */
    @Override
    public ListIterator<T> listIterator() {
        throw  new UnsupportedOperationException();
    }
}
