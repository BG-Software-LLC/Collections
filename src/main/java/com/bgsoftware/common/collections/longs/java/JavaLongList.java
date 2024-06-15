package com.bgsoftware.common.collections.longs.java;

import com.bgsoftware.common.collections.longs.LongList;

import java.util.Collection;
import java.util.List;
import java.util.RandomAccess;

public class JavaLongList extends JavaLongCollection implements LongList {

    public static JavaLongList create(Collection<Long> handle) {
        return handle instanceof RandomAccess ? new RandomAccessJavaLongList(handle) :
                new JavaLongList(handle);
    }

    private static class RandomAccessJavaLongList extends JavaLongList implements RandomAccess {

        RandomAccessJavaLongList(Collection<Long> handle) {
            super(handle);
        }

    }

    private JavaLongList(Collection<Long> handle) {
        super(handle);
    }

    @Override
    public long getAt(int index) {
        return ((List<Long>) this.handle).get(index);
    }

    @Override
    public long removeAt(int index) {
        return ((List<Long>) this.handle).remove(index);
    }

    @Override
    public int indexOf(long element) {
        return ((List<Long>) this.handle).indexOf(element);
    }

    @Override
    public List<Long> handle() {
        return (List<Long>) this.handle;
    }
}
