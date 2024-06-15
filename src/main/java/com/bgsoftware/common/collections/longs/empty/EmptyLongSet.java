package com.bgsoftware.common.collections.longs.empty;

import com.bgsoftware.common.collections.longs.LongSet;

import java.util.Collections;
import java.util.Set;

public class EmptyLongSet extends EmptyLongCollection implements LongSet {

    public static final EmptyLongSet INSTANCE = new EmptyLongSet();

    private EmptyLongSet() {

    }

    @Override
    public Set<Long> handle() {
        return Collections.emptySet();
    }

}
