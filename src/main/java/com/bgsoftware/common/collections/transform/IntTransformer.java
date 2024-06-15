package com.bgsoftware.common.collections.transform;

public interface IntTransformer<E> {

    int transform(E value);

    E transformInt(int value);

}
