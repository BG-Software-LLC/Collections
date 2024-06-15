package com.bgsoftware.common.collections.internal.sets;

import com.bgsoftware.common.collections.ints.IntSet;
import com.bgsoftware.common.collections.longs.LongSet;

import java.util.Set;

public interface SetsFactory {

    <E> Set<E> newSet(SetStrategy strategy);

    IntSet newIntSet(SetStrategy strategy);

    LongSet newLongSet(SetStrategy strategy);

}
