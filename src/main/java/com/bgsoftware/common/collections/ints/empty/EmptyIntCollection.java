package com.bgsoftware.common.collections.ints.empty;

import com.bgsoftware.common.collections.ints.IntCollection;
import com.bgsoftware.common.collections.ints.IntIterator;

import java.util.Collection;
import java.util.Collections;

public class EmptyIntCollection implements IntCollection {

    public static final EmptyIntCollection INSTANCE = new EmptyIntCollection();

    private static final int[] EMPTY_ARR = new int[0];

    protected EmptyIntCollection() {

    }

    @Override
    public int size() {
        return 0;
    }

    @Override
    public boolean contains(int element) {
        return false;
    }

    @Override
    public IntIterator iterator() {
        return EmptyIntIterator.INSTANCE;
    }

    @Override
    public boolean add(int element) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean remove(int element) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void clear() {
        throw new UnsupportedOperationException();
    }

    @Override
    public int[] toArray() {
        return EMPTY_ARR;
    }

    @Override
    public int[] toArray(int[] a) {
        return a;
    }

    @Override
    public Collection<Integer> handle() {
        return Collections.emptyList();
    }

    @Override
    public boolean isEmpty() {
        return false;
    }

}
