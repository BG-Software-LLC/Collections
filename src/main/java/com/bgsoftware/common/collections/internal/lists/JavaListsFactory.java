package com.bgsoftware.common.collections.internal.lists;

import com.bgsoftware.common.collections.ints.IntList;
import com.bgsoftware.common.collections.ints.java.JavaIntList;
import com.bgsoftware.common.collections.longs.LongList;
import com.bgsoftware.common.collections.longs.java.JavaLongList;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class JavaListsFactory implements ListsFactory {

    public static final JavaListsFactory INSTANCE = new JavaListsFactory();

    private JavaListsFactory() {

    }


    @Override
    public <E> List<E> newList(ListStrategy strategy) {
        switch (strategy) {
            case ARRAY_LIST:
                return new ArrayList<>();
            case LINKED_LIST:
                return new LinkedList<>();
        }

        throw new IllegalArgumentException("strategy: " + strategy);
    }

    @Override
    public IntList newIntArrayList() {
        return JavaIntList.create(new ArrayList<>());
    }

    @Override
    public LongList newLongArrayList() {
        return JavaLongList.create(new ArrayList<>());
    }
}
