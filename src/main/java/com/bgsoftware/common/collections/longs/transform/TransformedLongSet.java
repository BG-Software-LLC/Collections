package com.bgsoftware.common.collections.longs.transform;

import com.bgsoftware.common.collections.longs.LongSet;
import com.bgsoftware.common.collections.transform.LongTransformer;

import java.util.RandomAccess;
import java.util.Set;

public class TransformedLongSet<E> extends TransformedLongCollection<E> implements Set<E> {

    public static <E> TransformedLongSet<E> create(LongSet handle, LongTransformer<E> transformer) {
        return handle instanceof RandomAccess ? new RandomAccessTransformedSet<>(handle, transformer) :
                new TransformedLongSet<>(handle, transformer);
    }

    private static class RandomAccessTransformedSet<E> extends TransformedLongSet<E> implements RandomAccess {

        private RandomAccessTransformedSet(LongSet handle, LongTransformer<E> transformer) {
            super(handle, transformer);
        }

    }

    protected TransformedLongSet(LongSet handle, LongTransformer<E> transformer) {
        super(handle, transformer);
    }

}
