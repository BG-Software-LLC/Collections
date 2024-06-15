package com.bgsoftware.common.collections.longs.empty;

import com.bgsoftware.common.collections.longs.LongIterator;

import java.util.Collections;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class EmptyLongIterator implements LongIterator {

    public static final EmptyLongIterator INSTANCE = new EmptyLongIterator();

    private EmptyLongIterator() {

    }

    @Override
    public boolean hasNext() {
        return false;
    }

    @Override
    public long next() {
        throw new NoSuchElementException();
    }

    @Override
    public void remove() {
        throw new UnsupportedOperationException();
    }

    @Override
    public Iterator<Long> handle() {
        return Collections.emptyIterator();
    }
}
