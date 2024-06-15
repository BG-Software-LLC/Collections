package com.bgsoftware.common.collections.longs;

import java.util.Collection;

public interface LongCollection {

    int size();

    boolean contains(long element);

    LongIterator iterator();

    boolean add(long element);

    boolean remove(long element);

    void clear();

    long[] toArray();

    long[] toArray(long[] arr);

    Collection<Long> handle();

    default boolean isEmpty() {
        return size() <= 0;
    }

}
