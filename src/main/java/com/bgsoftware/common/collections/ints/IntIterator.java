package com.bgsoftware.common.collections.ints;

import java.util.Iterator;
import java.util.function.IntConsumer;

public interface IntIterator {

    boolean hasNext();

    int next();

    void remove();

    Iterator<Integer> handle();

    default void forEachRemaining(IntConsumer consumer) {
        while (hasNext())
            consumer.accept(next());
    }

}
