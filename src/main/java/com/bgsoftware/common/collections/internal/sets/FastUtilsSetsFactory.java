package com.bgsoftware.common.collections.internal.sets;

import com.bgsoftware.common.collections.ints.IntSet;
import com.bgsoftware.common.collections.ints.fastutils.FastUtilsIntSet;
import com.bgsoftware.common.collections.longs.LongSet;
import com.bgsoftware.common.collections.longs.fastutils.FastUtilsLongSet;
import it.unimi.dsi.fastutil.ints.IntArraySet;
import it.unimi.dsi.fastutil.ints.IntLinkedOpenHashSet;
import it.unimi.dsi.fastutil.ints.IntOpenHashSet;
import it.unimi.dsi.fastutil.longs.LongArraySet;
import it.unimi.dsi.fastutil.longs.LongLinkedOpenHashSet;
import it.unimi.dsi.fastutil.longs.LongOpenHashSet;
import it.unimi.dsi.fastutil.objects.ObjectArraySet;
import it.unimi.dsi.fastutil.objects.ObjectLinkedOpenHashSet;
import it.unimi.dsi.fastutil.objects.ObjectOpenHashSet;

import java.util.Set;

public class FastUtilsSetsFactory implements SetsFactory {

    public static final FastUtilsSetsFactory INSTANCE = new FastUtilsSetsFactory();

    private FastUtilsSetsFactory() {

    }

    @Override
    public <E> Set<E> newSet(SetStrategy strategy) {
        switch (strategy) {
            case ARRAY_SET:
                return new ObjectArraySet<>();
            case HASH_SET:
                return new ObjectOpenHashSet<>();
            case LINKED_HASH_SET:
                return new ObjectLinkedOpenHashSet<>();
        }

        throw new IllegalArgumentException("strategy: " + strategy);
    }

    @Override
    public IntSet newIntSet(SetStrategy strategy) {
        it.unimi.dsi.fastutil.ints.IntSet handle;
        switch (strategy) {
            case ARRAY_SET:
                handle = new IntArraySet();
                break;
            case HASH_SET:
                handle = new IntOpenHashSet();
            case LINKED_HASH_SET:
                handle = new IntLinkedOpenHashSet();
                break;
            default:
                throw new IllegalArgumentException("strategy: " + strategy);
        }

        return new FastUtilsIntSet(handle);
    }

    @Override
    public LongSet newLongSet(SetStrategy strategy) {
        it.unimi.dsi.fastutil.longs.LongSet handle;
        switch (strategy) {
            case ARRAY_SET:
                handle = new LongArraySet();
                break;
            case HASH_SET:
                handle = new LongOpenHashSet();
            case LINKED_HASH_SET:
                handle = new LongLinkedOpenHashSet();
                break;
            default:
                throw new IllegalArgumentException("strategy: " + strategy);
        }

        return new FastUtilsLongSet(handle);
    }

}
