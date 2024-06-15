package com.bgsoftware.common.collections.longs.empty;

import com.bgsoftware.common.collections.longs.LongList;

import java.util.Collections;
import java.util.List;

public class EmptyLongList extends EmptyLongCollection implements LongList {

    public static final EmptyLongList INSTANCE = new EmptyLongList();

    private EmptyLongList() {

    }

    @Override
    public long getAt(int index) {
        throw new ArrayIndexOutOfBoundsException(index);
    }

    @Override
    public long removeAt(int index) {
        throw new UnsupportedOperationException();
    }

    @Override
    public int indexOf(long element) {
        return -1;
    }

    @Override
    public List<Long> handle() {
        return Collections.emptyList();
    }

}
