package com.bgsoftware.common.collections.ints;

import java.util.Collection;

public interface IntCollection {

    int size();

    boolean contains(int element);

    IntIterator iterator();

    boolean add(int element);

    boolean remove(int element);

    void clear();

    int[] toArray();

    int[] toArray(int[] arr);

    Collection<Integer> handle();

    default boolean isEmpty() {
        return size() <= 0;
    }

}
