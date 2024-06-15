package com.bgsoftware.common.collections.longs.java;

import com.bgsoftware.common.collections.ints.IntIterator;
import com.bgsoftware.common.collections.ints.IntSet;
import com.bgsoftware.common.collections.ints.java.JavaIntIterator;
import com.bgsoftware.common.collections.longs.LongIterator;
import com.bgsoftware.common.collections.longs.LongSet;

import java.util.Set;

public class JavaLongSet extends JavaLongCollection implements LongSet {

    public JavaLongSet(Set<Long> handle) {
        super(handle);
    }

    @Override
    public Set<Long> handle() {
        return (Set<Long>) this.handle;
    }
}
