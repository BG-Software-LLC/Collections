package com.bgsoftware.common.collections.ints.fastutils;

import com.bgsoftware.common.collections.ints.IntCollection;
import com.bgsoftware.common.collections.ints.IntIterator;

import java.util.Collection;

public class FastUtilsIntCollection implements IntCollection {

    protected final it.unimi.dsi.fastutil.ints.IntCollection handle;

    public FastUtilsIntCollection(it.unimi.dsi.fastutil.ints.IntCollection handle) {
        this.handle = handle;
    }

    @Override
    public int size() {
        return this.handle.size();
    }

    @Override
    public boolean contains(int element) {
        return this.handle.contains(element);
    }

    @Override
    public IntIterator iterator() {
        return new FastUtilsIntIterator(this.handle.iterator());
    }

    @Override
    public boolean add(int element) {
        return this.handle.add(element);
    }

    @Override
    public boolean remove(int element) {
        return this.handle.rem(element);
    }

    @Override
    public void clear() {
        this.handle.clear();
    }

    @Override
    public int[] toArray() {
        return this.handle.toIntArray();
    }

    @Override
    public int[] toArray(int[] arr) {
        return this.handle.toArray(arr);
    }

    @Override
    public Collection<Integer> handle() {
        return this.handle;
    }

    @Override
    public String toString() {
        return this.handle.toString();
    }

}
