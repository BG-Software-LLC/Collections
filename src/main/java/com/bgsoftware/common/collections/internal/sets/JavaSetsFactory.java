package com.bgsoftware.common.collections.internal.sets;

import com.bgsoftware.common.collections.ints.IntSet;
import com.bgsoftware.common.collections.ints.java.JavaIntSet;
import com.bgsoftware.common.collections.longs.LongSet;
import com.bgsoftware.common.collections.longs.java.JavaLongSet;

import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;

public class JavaSetsFactory implements SetsFactory {

    public static final JavaSetsFactory INSTANCE = new JavaSetsFactory();

    private JavaSetsFactory() {

    }

    @Override
    public <E> Set<E> newSet(SetStrategy strategy) {
        switch (strategy) {
            case ARRAY_SET:
            case HASH_SET:
                return new HashSet<>();
            case LINKED_HASH_SET:
                return new LinkedHashSet<>();
        }

        throw new IllegalArgumentException("strategy: " + strategy);
    }

    @Override
    public IntSet newIntSet(SetStrategy strategy) {
        Set<Integer> handle;
        switch (strategy) {
            case ARRAY_SET:
            case HASH_SET:
                handle = new HashSet<>();
                break;
            case LINKED_HASH_SET:
                handle = new LinkedHashSet<>();
                break;
            default:
                throw new IllegalArgumentException("strategy: " + strategy);
        }

        return new JavaIntSet(handle);
    }

    @Override
    public LongSet newLongSet(SetStrategy strategy) {
        Set<Long> handle;
        switch (strategy) {
            case ARRAY_SET:
            case HASH_SET:
                handle = new HashSet<>();
                break;
            case LINKED_HASH_SET:
                handle = new LinkedHashSet<>();
                break;
            default:
                throw new IllegalArgumentException("strategy: " + strategy);
        }

        return new JavaLongSet(handle);
    }

}
