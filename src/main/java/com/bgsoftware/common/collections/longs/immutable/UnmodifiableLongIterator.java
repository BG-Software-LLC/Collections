package com.bgsoftware.common.collections.longs.immutable;

import com.bgsoftware.common.collections.internal.immutable.UnmodifiableIterator;
import com.bgsoftware.common.collections.longs.LongIterator;

import java.util.Iterator;
import java.util.function.LongConsumer;

public class UnmodifiableLongIterator implements LongIterator {

    private final LongIterator handle;

    private Iterator<Long> unmodifiableHandle;

    public static UnmodifiableLongIterator create(LongIterator handle) {
        return handle instanceof UnmodifiableLongIterator ?
                (UnmodifiableLongIterator) handle :
                new UnmodifiableLongIterator(handle);
    }

    private UnmodifiableLongIterator(LongIterator handle) {
        this.handle = handle;
    }

    @Override
    public boolean hasNext() {
        return this.handle.hasNext();
    }

    @Override
    public long next() {
        return this.handle.next();
    }

    @Override
    public void remove() {
        throw new UnsupportedOperationException();
    }

    @Override
    public Iterator<Long> handle() {
        return this.unmodifiableHandle == null ?
                (this.unmodifiableHandle = UnmodifiableIterator.create(this.handle.handle())) :
                this.unmodifiableHandle;
    }

    @Override
    public void forEachRemaining(LongConsumer consumer) {
        this.handle.forEachRemaining(consumer);
    }

}
