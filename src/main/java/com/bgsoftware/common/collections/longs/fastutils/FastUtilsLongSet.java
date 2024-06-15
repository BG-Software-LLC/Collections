package com.bgsoftware.common.collections.longs.fastutils;

import com.bgsoftware.common.collections.longs.LongSet;

import java.util.Set;

public class FastUtilsLongSet extends FastUtilsLongCollection implements LongSet {

    public FastUtilsLongSet(it.unimi.dsi.fastutil.longs.LongSet handle) {
        super(handle);
    }

    @Override
    public Set<Long> handle() {
        return (it.unimi.dsi.fastutil.longs.LongSet) this.handle;
    }
}
