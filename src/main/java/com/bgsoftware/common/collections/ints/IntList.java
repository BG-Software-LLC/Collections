package com.bgsoftware.common.collections.ints;

import java.util.List;

public interface IntList extends IntCollection {

    int getAt(int index);

    int removeAt(int index);

    int indexOf(int element);

    List<Integer> handle();

}
