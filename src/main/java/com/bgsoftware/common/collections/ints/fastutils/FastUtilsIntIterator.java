package com.bgsoftware.common.collections.ints.fastutils;

import com.bgsoftware.common.collections.ints.IntIterator;

import java.util.Iterator;

public class FastUtilsIntIterator implements IntIterator {

    private final it.unimi.dsi.fastutil.ints.IntIterator handle;

    public FastUtilsIntIterator(it.unimi.dsi.fastutil.ints.IntIterator handle) {
        this.handle = handle;
    }

    @Override
    public boolean hasNext() {
        return this.handle.hasNext();
    }

    @Override
    public int next() {
        return this.handle.nextInt();
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
