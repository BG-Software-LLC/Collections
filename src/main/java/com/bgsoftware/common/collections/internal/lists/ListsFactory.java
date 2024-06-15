package com.bgsoftware.common.collections.internal.lists;

import com.bgsoftware.common.collections.ints.IntList;
import com.bgsoftware.common.collections.longs.LongList;

import java.util.List;

public interface ListsFactory {

    <E> List<E> newList(ListStrategy strategy);

    IntList newIntArrayList();

    LongList newLongArrayList();

}
