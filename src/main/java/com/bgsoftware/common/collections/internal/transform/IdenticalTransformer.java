package com.bgsoftware.common.collections.internal.transform;

import com.bgsoftware.common.collections.transform.Transformer;

public class IdenticalTransformer<A, B> extends Transformer<A, B> {

    public static final IdenticalTransformer INSTANCE = new IdenticalTransformer();

    private IdenticalTransformer() {

    }

    @Override
    public B transformA(A value) {
        return (B) value;
    }

    @Override
    public A transformB(B value) {
        return (A) value;
    }

    @Override
    public Transformer<B, A> opposite() {
        return INSTANCE;
    }

}
