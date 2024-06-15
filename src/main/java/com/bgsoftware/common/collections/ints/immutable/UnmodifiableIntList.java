package com.bgsoftware.common.collections.ints.immutable;

import com.bgsoftware.common.collections.internal.immutable.UnmodifiableList;
import com.bgsoftware.common.collections.ints.IntList;

import java.util.List;
import java.util.RandomAccess;

public class UnmodifiableIntList extends UnmodifiableIntCollection implements IntList {

    public static UnmodifiableIntList create(IntList handle) {
        return handle instanceof UnmodifiableIntList ?
                (UnmodifiableIntList) handle :
                handle instanceof RandomAccess ? new RandomAccessUnmodifiableIntList(handle) :
                        new UnmodifiableIntList(handle);
    }

    private static class RandomAccessUnmodifiableIntList extends UnmodifiableIntList implements RandomAccess {

        RandomAccessUnmodifiableIntList(IntList handle) {
            super(handle);
        }

    }

    private UnmodifiableIntList(IntList handle) {
        super(handle);
    }

    @Override
    public List<Integer> handle() {
        return (List<Integer>) (this.unmodifiableHandle == null ?
                (this.unmodifiableHandle = UnmodifiableList.create(((IntList) this.handle).handle())) :
                this.unmodifiableHandle);
    }

    @Override
    public int getAt(int index) {
        return ((IntList) this.handle).getAt(index);
    }

    @Override
    public int removeAt(int index) {
        throw new UnsupportedOperationException();
    }

    @Override
    public int indexOf(int element) {
        return ((IntList) this.handle).indexOf(element);
    }

}
