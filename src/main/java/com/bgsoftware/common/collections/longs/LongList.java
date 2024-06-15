package com.bgsoftware.common.collections.longs;

import java.util.List;

public interface LongList extends LongCollection {

    long getAt(int index);

    long removeAt(int index);

    int indexOf(long element);

    List<Long> handle();

}
