package com.bgsoftware.common.collections.longs.fastutils;

import com.bgsoftware.common.collections.longs.LongList;

import java.util.List;
import java.util.RandomAccess;

public class FastUtilsLongList extends FastUtilsLongCollection implements LongList {

    public static FastUtilsLongList create(it.unimi.dsi.fastutil.longs.LongList handle) {
        return handle instanceof RandomAccess ? new RandomAccessFastUtilsLongList(handle) :
                new FastUtilsLongList(handle);
    }

    private static class RandomAccessFastUtilsLongList extends FastUtilsLongList implements RandomAccess {

        RandomAccessFastUtilsLongList(it.unimi.dsi.fastutil.longs.LongList handle) {
            super(handle);
        }

    }

    private FastUtilsLongList(it.unimi.dsi.fastutil.longs.LongList handle) {
        super(handle);
    }

    @Override
    public long getAt(int index) {
        return ((it.unimi.dsi.fastutil.longs.LongList) this.handle).getLong(index);
    }

    @Override
    public long removeAt(int index) {
        return ((it.unimi.dsi.fastutil.longs.LongList) this.handle).removeLong(index);
    }

    @Override
    public int indexOf(long element) {
        return ((it.unimi.dsi.fastutil.longs.LongList) this.handle).indexOf(element);
    }

    @Override
    public List<Long> handle() {
        return (it.unimi.dsi.fastutil.longs.LongList) this.handle;
    }
}
