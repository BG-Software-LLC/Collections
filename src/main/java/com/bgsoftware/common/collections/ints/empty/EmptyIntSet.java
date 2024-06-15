package com.bgsoftware.common.collections.ints.empty;

import com.bgsoftware.common.collections.ints.IntSet;

import java.util.Collections;
import java.util.Set;

public class EmptyIntSet extends EmptyIntCollection implements IntSet {

    public static final EmptyIntSet INSTANCE = new EmptyIntSet();

    private EmptyIntSet() {

    }

    @Override
    public Set<Integer> handle() {
        return Collections.emptySet();
    }

}
