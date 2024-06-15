package com.bgsoftware.common.collections.longs.fastutils;

import com.bgsoftware.common.collections.longs.LongIterator;

import java.util.Iterator;

public class FastUtilsLongIterator implements LongIterator {

    private final it.unimi.dsi.fastutil.longs.LongIterator handle;

    public FastUtilsLongIterator(it.unimi.dsi.fastutil.longs.LongIterator handle) {
        this.handle = handle;
    }

    @Override
    public boolean hasNext() {
        return this.handle.hasNext();
    }

    @Override
    public long next() {
        return this.handle.nextLong();
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
