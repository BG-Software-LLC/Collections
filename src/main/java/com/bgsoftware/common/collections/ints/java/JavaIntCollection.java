package com.bgsoftware.common.collections.ints.java;

import com.bgsoftware.common.collections.ints.IntCollection;
import com.bgsoftware.common.collections.ints.IntIterator;
import com.bgsoftware.common.collections.ints.empty.EmptyIntCollection;
import com.bgsoftware.common.collections.longs.empty.EmptyLongCollection;

import java.util.Arrays;
import java.util.Collection;

public class JavaIntCollection implements IntCollection {

    protected final Collection<Integer> handle;

    public JavaIntCollection(Collection<Integer> handle) {
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
        return new JavaIntIterator(this.handle.iterator());
    }

    @Override
    public boolean add(int element) {
        return this.handle.add(element);
    }

    @Override
    public boolean remove(int element) {
        return this.handle.remove(element);
    }

    @Override
    public void clear() {
        this.handle.clear();
    }

    @Override
    public int[] toArray() {
        if(isEmpty()) return EmptyIntCollection.INSTANCE.toArray();
        return populateArray(new int[this.size()]);
    }

    @Override
    public int[] toArray(int[] a) {
        int size = this.size();
        if (a.length < size)
            a = Arrays.copyOf(a, size);
        return populateArray(a);
    }

    private int[] populateArray(int[] arr) {
        Object[] elements = this.handle.toArray();
        for (int i = 0; i < elements.length; ++i)
            arr[i] = (Integer) elements[i];
        return arr;
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
