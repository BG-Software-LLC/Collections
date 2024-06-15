package com.bgsoftware.common.collections.internal.immutable;

import java.util.Iterator;
import java.util.function.Consumer;

public class UnmodifiableIterator<E> implements Iterator<E> {

    private final Iterator<E> handle;

    public static <E> UnmodifiableIterator<E> create(Iterator<E> handle) {
        return handle instanceof UnmodifiableIterator ?
                (UnmodifiableIterator<E>) handle :
                new UnmodifiableIterator<>(handle);
    }

    private UnmodifiableIterator(Iterator<E> handle) {
        this.handle = handle;
    }

    @Override
    public boolean hasNext() {
        return this.handle.hasNext();
    }

    @Override
    public E next() {
        return this.handle.next();
    }

    @Override
    public void remove() {
        throw new UnsupportedOperationException();
    }

    @Override
    public void forEachRemaining(Consumer<? super E> action) {
        this.handle.forEachRemaining(action);
    }
}
