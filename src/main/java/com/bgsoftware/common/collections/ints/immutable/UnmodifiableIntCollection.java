package com.bgsoftware.common.collections.ints.immutable;

import com.bgsoftware.common.collections.internal.immutable.UnmodifiableCollection;
import com.bgsoftware.common.collections.ints.IntCollection;
import com.bgsoftware.common.collections.ints.IntIterator;

import java.util.Collection;

public class UnmodifiableIntCollection implements IntCollection {

    protected final IntCollection handle;

    protected Collection<Integer> unmodifiableHandle;

    public static UnmodifiableIntCollection create(IntCollection handle) {
        return handle instanceof UnmodifiableIntCollection ?
                (UnmodifiableIntCollection) handle :
                new UnmodifiableIntCollection(handle);
    }

    protected UnmodifiableIntCollection(IntCollection handle) {
        this.handle = handle;
    }

    @Override
    public int size() {
        return this.handle.size();
    }

    @Override
    public boolean contains(int element) {
        return this.handle.contains(element);
    }

    @Override
    public IntIterator iterator() {
        return UnmodifiableIntIterator.create(this.handle.iterator());
    }

    @Override
    public boolean add(int element) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean remove(int element) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void clear() {
        throw new UnsupportedOperationException();
    }

    @Override
    public int[] toArray() {
        return this.handle.toArray();
    }

    @Override
    public int[] toArray(int[] arr) {
        return this.handle.toArray(arr);
    }

    @Override
    public Collection<Integer> handle() {
        return this.unmodifiableHandle == null ?
                (this.unmodifiableHandle = UnmodifiableCollection.create(this.handle.handle())) :
                this.unmodifiableHandle;
    }

    @Override
    public boolean isEmpty() {
        return this.handle.isEmpty();
    }

}
