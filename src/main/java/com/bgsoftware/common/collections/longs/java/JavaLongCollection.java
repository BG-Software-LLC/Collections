package com.bgsoftware.common.collections.longs.java;

import com.bgsoftware.common.collections.longs.LongCollection;
import com.bgsoftware.common.collections.longs.LongIterator;
import com.bgsoftware.common.collections.longs.empty.EmptyLongCollection;

import java.util.Arrays;
import java.util.Collection;

public class JavaLongCollection implements LongCollection {

    protected final Collection<Long> handle;

    public JavaLongCollection(Collection<Long> handle) {
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
        return new JavaLongIterator(this.handle.iterator());
    }

    @Override
    public boolean add(long element) {
        return this.handle.add(element);
    }

    @Override
    public boolean remove(long element) {
        return this.handle.remove(element);
    }

    @Override
    public void clear() {
        this.handle.clear();
    }

    @Override
    public long[] toArray() {
        if (isEmpty()) return EmptyLongCollection.INSTANCE.toArray();
        return populateArray(new long[this.size()]);
    }

    @Override
    public long[] toArray(long[] a) {
        int size = this.size();
        if (a.length < size)
            a = Arrays.copyOf(a, size);
        return populateArray(a);
    }

    private long[] populateArray(long[] arr) {
        Object[] elements = this.handle.toArray();
        for (int i = 0; i < elements.length; ++i)
            arr[i] = (Long) elements[i];
        return arr;
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
