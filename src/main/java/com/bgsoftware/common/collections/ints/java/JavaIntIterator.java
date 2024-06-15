package com.bgsoftware.common.collections.ints.java;

import com.bgsoftware.common.collections.ints.IntIterator;

import java.util.Iterator;

public class JavaIntIterator implements IntIterator {

    private final Iterator<Integer> handle;

    public JavaIntIterator(Iterator<Integer> handle) {
        this.handle = handle;
    }

    @Override
    public boolean hasNext() {
        return this.handle.hasNext();
    }

    @Override
    public int next() {
        return this.handle.next();
    }

    @Override
    public void remove() {
        this.handle.remove();
    }

    @Override
    public Iterator<Integer> handle() {
        return this.handle;
    }

    @Override
    public String toString() {
        return this.handle.toString();
    }

}
