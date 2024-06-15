package com.bgsoftware.common.collections.longs.java;

import com.bgsoftware.common.collections.longs.LongIterator;

import java.util.Iterator;

public class JavaLongIterator implements LongIterator {

    private final Iterator<Long> handle;

    public JavaLongIterator(Iterator<Long> handle) {
        this.handle = handle;
    }

    @Override
    public boolean hasNext() {
        return this.handle.hasNext();
    }

    @Override
    public long next() {
        return this.handle.next();
    }

    @Override
    public void remove() {
        this.handle.remove();
    }

    @Override
    public Iterator<Long> handle() {
        return this.handle;
    }

    @Override
    public String toString() {
        return this.handle.toString();
    }

}
