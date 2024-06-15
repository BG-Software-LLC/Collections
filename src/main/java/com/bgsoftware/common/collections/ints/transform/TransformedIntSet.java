package com.bgsoftware.common.collections.ints.transform;

import com.bgsoftware.common.collections.ints.IntSet;
import com.bgsoftware.common.collections.transform.IntTransformer;

import java.util.Set;

public class TransformedIntSet<E> extends TransformedIntCollection<E> implements Set<E> {

    public static <E> TransformedIntSet<E> create(IntSet handle, IntTransformer<E> transformer) {
        return new TransformedIntSet<>(handle, transformer);
    }

    protected TransformedIntSet(IntSet handle, IntTransformer<E> transformer) {
        super(handle, transformer);
    }

}
