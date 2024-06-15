package com.bgsoftware.common.collections.longs.immutable;

import com.bgsoftware.common.collections.internal.immutable.UnmodifiableCollection;
import com.bgsoftware.common.collections.longs.LongCollection;
import com.bgsoftware.common.collections.longs.LongIterator;

import java.util.Collection;

public class UnmodifiableLongCollection implements LongCollection {

    protected final LongCollection handle;

    protected Collection<Long> unmodifiableHandle;

    public static UnmodifiableLongCollection create(LongCollection handle) {
        return handle instanceof UnmodifiableLongCollection ?
                (UnmodifiableLongCollection) handle :
                new UnmodifiableLongCollection(handle);
    }

    protected UnmodifiableLongCollection(LongCollection handle) {
        this.handle = handle;
    }

    @Override
    public int size() {
        return this.handle.size();
    }

    @Override
    public boolean contains(long element) {
        return this.handle.contains(element);
    }

    @Override
    public LongIterator iterator() {
        return UnmodifiableLongIterator.create(this.handle.iterator());
    }

    @Override
    public boolean add(long element) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean remove(long element) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void clear() {
        throw new UnsupportedOperationException();
    }

    @Override
    public long[] toArray() {
        return this.handle.toArray();
    }

    @Override
    public long[] toArray(long[] arr) {
        return this.handle.toArray(arr);
    }

    @Override
    public Collection<Long> handle() {
        return this.unmodifiableHandle == null ?
                (this.unmodifiableHandle = UnmodifiableCollection.create(this.handle.handle())) :
                this.unmodifiableHandle;
    }

    @Override
    public boolean isEmpty() {
        return this.handle.isEmpty();
    }

}
