package com.bgsoftware.common.collections.ints.empty;

import com.bgsoftware.common.collections.ints.IntIterator;

import java.util.Collections;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class EmptyIntIterator implements IntIterator {

    public static final EmptyIntIterator INSTANCE = new EmptyIntIterator();

    private EmptyIntIterator() {

    }

    @Override
    public boolean hasNext() {
        return false;
    }

    @Override
    public int next() {
        throw new NoSuchElementException();
    }

    @Override
    public void remove() {
        throw new UnsupportedOperationException();
    }

    @Override
    public Iterator<Integer> handle() {
        return Collections.emptyIterator();
    }
}
