package com.bgsoftware.common.collections.transform;

public abstract class Transformer<A, B> {

    private Transformer<B, A> opposite;

    public abstract B transformA(A value);

    public abstract A transformB(B value);

    public Transformer<B, A> opposite() {
        if (opposite == null) {
            Transformer<A, B> instance = this;
            opposite = new Transformer<B, A>() {
                @Override
                public A transformA(B value) {
                    return instance.transformB(value);
                }

                @Override
                public B transformB(A value) {
                    return instance.transformA(value);
                }

                @Override
                public Transformer<A, B> opposite() {
                    return instance;
                }
            };
        }

        return opposite;
    }

}
