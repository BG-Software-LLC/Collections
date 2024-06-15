package com.bgsoftware.common.collections.ints.java;

import com.bgsoftware.common.collections.ints.IntList;

import java.util.List;
import java.util.RandomAccess;

public class JavaIntList extends JavaIntCollection implements IntList {

    public static JavaIntList create(List<Integer> handle) {
        return handle instanceof RandomAccess ? new RandomAccessJavaIntList(handle) :
                new JavaIntList(handle);
    }

    private static class RandomAccessJavaIntList extends JavaIntList implements RandomAccess {

        RandomAccessJavaIntList(List<Integer> handle) {
            super(handle);
        }

    }

    private JavaIntList(List<Integer> handle) {
        super(handle);
    }

    @Override
    public int getAt(int index) {
        return ((List<Integer>) this.handle).get(index);
    }

    @Override
    public int removeAt(int index) {
        return ((List<Integer>) this.handle).remove(index);
    }

    @Override
    public int indexOf(int element) {
        return ((List<Integer>) this.handle).indexOf(element);
    }

    @Override
    public List<Integer> handle() {
        return (List<Integer>) this.handle;
    }

}
