package com.bgsoftware.common.collections.longs.immutable;

import com.bgsoftware.common.collections.internal.immutable.UnmodifiableList;
import com.bgsoftware.common.collections.longs.LongList;

import java.util.List;
import java.util.RandomAccess;

public class UnmodifiableLongList extends UnmodifiableLongCollection implements LongList {

    public static UnmodifiableLongList create(LongList handle) {
        return handle instanceof UnmodifiableLongList ?
                (UnmodifiableLongList) handle :
                handle instanceof RandomAccess ? new RandomAccessUnmodifiableLongList(handle) :
                        new UnmodifiableLongList(handle);
    }

    private static class RandomAccessUnmodifiableLongList extends UnmodifiableLongList implements RandomAccess {

        RandomAccessUnmodifiableLongList(LongList handle) {
            super(handle);
        }

    }

    private UnmodifiableLongList(LongList handle) {
        super(handle);
    }

    @Override
    public List<Long> handle() {
        return (List<Long>) (this.unmodifiableHandle == null ?
                (this.unmodifiableHandle = UnmodifiableList.create(((LongList) this.handle).handle())) :
                this.unmodifiableHandle);
    }

    @Override
    public long getAt(int index) {
        return ((LongList) this.handle).getAt(index);
    }

    @Override
    public long removeAt(int index) {
        throw new UnsupportedOperationException();
    }

    @Override
    public int indexOf(long element) {
        return ((LongList) this.handle).indexOf(element);
    }

}
