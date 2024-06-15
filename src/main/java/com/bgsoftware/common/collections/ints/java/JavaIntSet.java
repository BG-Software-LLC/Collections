package com.bgsoftware.common.collections.ints.java;

import com.bgsoftware.common.collections.ints.IntIterator;
import com.bgsoftware.common.collections.ints.IntSet;

import java.util.Set;

public class JavaIntSet extends JavaIntCollection implements IntSet {

    public JavaIntSet(Set<Integer> handle) {
        super(handle);
    }

    @Override
    public Set<Integer> handle() {
        return (Set<Integer>) this.handle;
    }
}
