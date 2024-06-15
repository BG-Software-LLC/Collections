package com.bgsoftware.common.collections.ints.fastutils;

import com.bgsoftware.common.collections.ints.IntList;

import java.util.List;
import java.util.RandomAccess;

public class FastUtilsIntList extends FastUtilsIntCollection implements IntList {

    public static FastUtilsIntList create(it.unimi.dsi.fastutil.ints.IntList handle) {
        return handle instanceof RandomAccess ? new RandomAccessFastUtilsIntList(handle) :
                new FastUtilsIntList(handle);
    }

    private static class RandomAccessFastUtilsIntList extends FastUtilsIntList implements RandomAccess {

        RandomAccessFastUtilsIntList(it.unimi.dsi.fastutil.ints.IntList handle) {
            super(handle);
        }

    }

    private FastUtilsIntList(it.unimi.dsi.fastutil.ints.IntList handle) {
        super(handle);
    }

    @Override
    public int getAt(int index) {
        return ((it.unimi.dsi.fastutil.ints.IntList) this.handle).getInt(index);
    }

    @Override
    public int removeAt(int index) {
        return ((it.unimi.dsi.fastutil.ints.IntList) this.handle).removeInt(index);
    }

    @Override
    public int indexOf(int element) {
        return ((it.unimi.dsi.fastutil.ints.IntList) this.handle).indexOf(element);
    }

    @Override
    public List<Integer> handle() {
        return (it.unimi.dsi.fastutil.ints.IntList) this.handle;
    }

}
