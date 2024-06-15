package com.bgsoftware.common.collections.internal.lists;

import com.bgsoftware.common.collections.ints.IntList;
import com.bgsoftware.common.collections.ints.fastutils.FastUtilsIntList;
import com.bgsoftware.common.collections.longs.LongList;
import com.bgsoftware.common.collections.longs.fastutils.FastUtilsLongList;
import it.unimi.dsi.fastutil.ints.IntArrayList;
import it.unimi.dsi.fastutil.longs.LongArrayList;
import it.unimi.dsi.fastutil.objects.ObjectArrayList;

import java.util.LinkedList;
import java.util.List;

public class FastUtilsListsFactory implements ListsFactory {

    public static final FastUtilsListsFactory INSTANCE = new FastUtilsListsFactory();

    private FastUtilsListsFactory() {

    }

    @Override
    public <E> List<E> newList(ListStrategy strategy) {
        switch (strategy) {
            case ARRAY_LIST:
                return new ObjectArrayList<>();
            case LINKED_LIST:
                return new LinkedList<>();
        }

        throw new IllegalArgumentException("strategy: " + strategy);
    }

    @Override
    public IntList newIntArrayList() {
        return FastUtilsIntList.create(new IntArrayList());
    }

    @Override
    public LongList newLongArrayList() {
        return FastUtilsLongList.create(new LongArrayList());
    }

}
