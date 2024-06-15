package com.bgsoftware.common.collections.ints.transform;

import com.bgsoftware.common.collections.ints.IntSet;
import com.bgsoftware.common.collections.transform.IntTransformer;
import com.bgsoftware.common.collections.transform.Transformer;

import java.util.RandomAccess;
import java.util.Set;

public class TransformedIntSet<E> extends TransformedIntCollection<E> implements Set<E> {

    public static <E> TransformedIntSet<E> create(IntSet handle, IntTransformer<E> transformer) {
        return handle instanceof RandomAccess ? new RandomAccessTransformedSet<>(handle, transformer) :
                new TransformedIntSet<>(handle, transformer);
    }

    private static class RandomAccessTransformedSet<E> extends TransformedIntSet<E> implements RandomAccess {

        private RandomAccessTransformedSet(IntSet handle, IntTransformer<E> transformer) {
            super(handle, transformer);
        }

    }

    protected TransformedIntSet(IntSet handle, IntTransformer<E> transformer) {
        super(handle, transformer);
    }

}
