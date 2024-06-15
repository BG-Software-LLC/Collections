package com.bgsoftware.common.collections.transform;

public interface LongTransformer<E> {

    long transform(E value);

    E transformLong(long value);

}
