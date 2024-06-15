package com.bgsoftware.common.collections.longs.transform;

import com.bgsoftware.common.collections.longs.LongSet;
import com.bgsoftware.common.collections.transform.LongTransformer;

import java.util.Set;

public class TransformedLongSet<E> extends TransformedLongCollection<E> implements Set<E> {

    public static <E> TransformedLongSet<E> create(LongSet handle, LongTransformer<E> transformer) {
        return new TransformedLongSet<>(handle, transformer);
    }

    protected TransformedLongSet(LongSet handle, LongTransformer<E> transformer) {
        super(handle, transformer);
    }

}
