package com.bgsoftware.common.collections.longs.empty;

import com.bgsoftware.common.collections.longs.LongCollection;
import com.bgsoftware.common.collections.longs.LongIterator;

import java.util.Collection;
import java.util.Collections;

public class EmptyLongCollection implements LongCollection {

    public static final EmptyLongCollection INSTANCE = new EmptyLongCollection();

    private static final long[] EMPTY_ARR = new long[0];

    protected EmptyLongCollection() {

    }

    @Override
    public int size() {
        return 0;
    }

    @Override
    public boolean contains(long element) {
        return false;
    }

    @Override
    public LongIterator iterator() {
        return EmptyLongIterator.INSTANCE;
    }

    @Override
    public boolean add(long element) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean remove(long element) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void clear() {
        throw new UnsupportedOperationException();
    }

    @Override
    public long[] toArray() {
        return EMPTY_ARR;
    }

    @Override
    public long[] toArray(long[] arr) {
        return arr;
    }

    @Override
    public Collection<Long> handle() {
        return Collections.emptyList();
    }

    @Override
    public boolean isEmpty() {
        return true;
    }

}
