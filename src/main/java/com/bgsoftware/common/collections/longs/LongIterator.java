package com.bgsoftware.common.collections.longs;

import java.util.Iterator;
import java.util.function.LongConsumer;

public interface LongIterator {

    boolean hasNext();

    long next();

    void remove();

    Iterator<Long> handle();

    default void forEachRemaining(LongConsumer consumer) {
        while (hasNext())
            consumer.accept(next());
    }

}
