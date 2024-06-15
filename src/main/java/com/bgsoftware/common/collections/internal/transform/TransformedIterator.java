package com.bgsoftware.common.collections.internal.transform;

import com.bgsoftware.common.collections.transform.Transformer;

import java.util.Iterator;

public class TransformedIterator<A, B> implements Iterator<B> {

    private final Iterator<? extends A> iterator;
    private final Transformer<A, B> transformer;

    public TransformedIterator(Iterator<? extends A> iterator, Transformer<A, B> transformer) {
        this.iterator = iterator;
        this.transformer = transformer;
    }

    @Override
    public boolean hasNext() {
        return this.iterator.hasNext();
    }

    @Override
    public B next() {
        return this.transformer.transformA(this.iterator.next());
    }

    @Override
    public void remove() {
        this.iterator.remove();
    }

}
