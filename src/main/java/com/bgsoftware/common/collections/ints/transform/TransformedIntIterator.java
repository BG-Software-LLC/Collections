package com.bgsoftware.common.collections.ints.transform;

import com.bgsoftware.common.collections.ints.IntIterator;
import com.bgsoftware.common.collections.transform.IntTransformer;

import java.util.Iterator;

public class TransformedIntIterator<E> implements Iterator<E> {

    private final IntIterator iterator;
    private final IntTransformer<E> transformer;

    public TransformedIntIterator(IntIterator iterator, IntTransformer<E> transformer) {
        this.iterator = iterator;
        this.transformer = transformer;
    }

    @Override
    public boolean hasNext() {
        return this.iterator.hasNext();
    }

    @Override
    public E next() {
        return this.transformer.transformInt(this.iterator.next());
    }

    @Override
    public void remove() {
        this.iterator.remove();
    }

}
