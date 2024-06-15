package com.bgsoftware.common.collections.longs.fastutils;

import com.bgsoftware.common.collections.longs.LongCollection;
import com.bgsoftware.common.collections.longs.LongIterator;

import java.util.Collection;

public class FastUtilsLongCollection implements LongCollection {

    protected final it.unimi.dsi.fastutil.longs.LongCollection handle;

    public FastUtilsLongCollection(it.unimi.dsi.fastutil.longs.LongCollection handle) {
        this.handle = handle;
    }

    @Override
    public int size() {
        return this.handle.size();
    }

    @Override
    public boolean contains(long element) {
        return this.handle.contains(element);
    }

    @Override
    public LongIterator iterator() {
        return new FastUtilsLongIterator(this.handle.iterator());
    }

    @Override
    public boolean add(long element) {
        return this.handle.add(element);
    }

    @Override
    public boolean remove(long element) {
        return this.handle.rem(element);
    }

    @Override
    public void clear() {
        this.handle.clear();
    }

    @Override
    public long[] toArray() {
        return this.handle.toLongArray();
    }

    @Override
    public long[] toArray(long[] arr) {
        return this.handle.toArray(arr);
    }

    @Override
    public Collection<Long> handle() {
        return this.handle;
    }

    @Override
    public String toString() {
        return this.handle.toString();
    }

}
