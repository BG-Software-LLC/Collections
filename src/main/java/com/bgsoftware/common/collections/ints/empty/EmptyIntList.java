package com.bgsoftware.common.collections.ints.empty;

import com.bgsoftware.common.collections.ints.IntList;

import java.util.Collections;
import java.util.List;

public class EmptyIntList extends EmptyIntCollection implements IntList {

    public static final EmptyIntList INSTANCE = new EmptyIntList();

    private EmptyIntList() {

    }

    @Override
    public int getAt(int index) {
        throw new ArrayIndexOutOfBoundsException(index);
    }

    @Override
    public int removeAt(int index) {
        throw new UnsupportedOperationException();
    }

    @Override
    public int indexOf(int element) {
        return -1;
    }

    @Override
    public List<Integer> handle() {
        return Collections.emptyList();
    }

}
