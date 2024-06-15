package com.bgsoftware.common.collections.internal.transform;

import com.bgsoftware.common.collections.transform.Transformer;

import java.util.RandomAccess;
import java.util.Set;

public class TransformedSet<A, B> extends TransformedCollection<A, B> implements Set<B> {

    public static <A, B> TransformedSet<A, B> create(Set<A> handle, Transformer<A, B> transformer) {
        return handle instanceof RandomAccess ? new RandomAccessTransformedSet<>(handle, transformer) :
                new TransformedSet<>(handle, transformer);
    }

    private static class RandomAccessTransformedSet<A, B> extends TransformedSet<A, B> implements RandomAccess {

        private RandomAccessTransformedSet(Set<A> handle, Transformer<A, B> transformer) {
            super(handle, transformer);
        }

    }

    protected TransformedSet(Set<A> handle, Transformer<A, B> transformer) {
        super(handle, transformer);
    }

}
