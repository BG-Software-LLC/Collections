package com.bgsoftware.common.collections.longs.transform;

import com.bgsoftware.common.collections.longs.LongIterator;
import com.bgsoftware.common.collections.transform.LongTransformer;

import java.util.Iterator;

public class TransformedLongIterator<E> implements Iterator<E> {

    private final LongIterator iterator;
    private final LongTransformer<E> transformer;

    public TransformedLongIterator(LongIterator iterator, LongTransformer<E> transformer) {
        this.iterator = iterator;
        this.transformer = transformer;
    }

    @Override
    public boolean hasNext() {
        return this.iterator.hasNext();
    }

    @Override
    public E next() {
        return this.transformer.transformLong(this.iterator.next());
    }

    @Override
    public void remove() {
        this.iterator.remove();
    }

}
