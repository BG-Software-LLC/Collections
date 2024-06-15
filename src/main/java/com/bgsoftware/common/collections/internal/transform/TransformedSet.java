package com.bgsoftware.common.collections.internal.transform;

import com.bgsoftware.common.collections.transform.Transformer;

import java.util.Set;

public class TransformedSet<A, B> extends TransformedCollection<A, B> implements Set<B> {

    public static <A, B> TransformedSet<A, B> create(Set<A> handle, Transformer<A, B> transformer) {
        return new TransformedSet<>(handle, transformer);
    }

    protected TransformedSet(Set<A> handle, Transformer<A, B> transformer) {
        super(handle, transformer);
    }

}
