package com.bgsoftware.common.collections.ints.immutable;

import com.bgsoftware.common.collections.internal.immutable.UnmodifiableIterator;
import com.bgsoftware.common.collections.ints.IntIterator;

import java.util.Iterator;
import java.util.function.IntConsumer;

public class UnmodifiableIntIterator implements IntIterator {

    private final IntIterator handle;

    private Iterator<Integer> unmodifiableHandle;

    public static UnmodifiableIntIterator create(IntIterator handle) {
        return handle instanceof UnmodifiableIntIterator ?
                (UnmodifiableIntIterator) handle :
                new UnmodifiableIntIterator(handle);
    }

    private UnmodifiableIntIterator(IntIterator handle) {
        this.handle = handle;
    }

    @Override
    public boolean hasNext() {
        return this.handle.hasNext();
    }

    @Override
    public int next() {
        return this.handle.next();
    }

    @Override
    public void remove() {
        throw new UnsupportedOperationException();
    }

    @Override
    public Iterator<Integer> handle() {
        return this.unmodifiableHandle == null ?
                (this.unmodifiableHandle = UnmodifiableIterator.create(this.handle.handle())) :
                this.unmodifiableHandle;
    }

    @Override
    public void forEachRemaining(IntConsumer consumer) {
        this.handle.forEachRemaining(consumer);
    }

}
