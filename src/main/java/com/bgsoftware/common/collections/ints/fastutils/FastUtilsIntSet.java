package com.bgsoftware.common.collections.ints.fastutils;

import com.bgsoftware.common.collections.ints.IntIterator;
import com.bgsoftware.common.collections.ints.IntSet;

import java.util.Set;

public class FastUtilsIntSet extends FastUtilsIntCollection implements IntSet {

    public FastUtilsIntSet(it.unimi.dsi.fastutil.ints.IntSet handle) {
        super(handle);
    }

    @Override
    public Set<Integer> handle() {
        return (it.unimi.dsi.fastutil.ints.IntSet) this.handle;
    }

}
