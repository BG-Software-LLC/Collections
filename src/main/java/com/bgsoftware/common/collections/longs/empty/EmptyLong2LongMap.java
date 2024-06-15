package com.bgsoftware.common.collections.longs.empty;

import com.bgsoftware.common.collections.longs.Long2LongFunction;
import com.bgsoftware.common.collections.longs.Long2LongMap;
import com.bgsoftware.common.collections.longs.LongIterator;
import com.bgsoftware.common.collections.longs.LongSet;

import java.util.Collections;
import java.util.Iterator;
import java.util.Map;
import java.util.OptionalLong;
import java.util.Set;

public class EmptyLong2LongMap implements Long2LongMap {

    public static final EmptyLong2LongMap INSTANCE = new EmptyLong2LongMap();

    private EmptyLong2LongMap() {

    }

    @Override
    public int size() {
        return 0;
    }

    @Override
    public void clear() {
        throw new UnsupportedOperationException();
    }

    @Override
    public Set<Entry> entrySet() {
        return Collections.emptySet();
    }

    @Override
    public Iterator<Entry> entriesIterator() {
        return Collections.emptyIterator();
    }

    @Override
    public LongSet keySet() {
        return EmptyLongSet.INSTANCE;
    }

    @Override
    public LongIterator keysIterator() {
        return EmptyLongIterator.INSTANCE;
    }

    @Override
    public LongSet values() {
        return EmptyLongSet.INSTANCE;
    }

    @Override
    public LongIterator valuesIterator() {
        return EmptyLongIterator.INSTANCE;
    }

    @Override
    public OptionalLong put(long key, long value) {
        throw new UnsupportedOperationException();
    }

    @Override
    public OptionalLong get(long key) {
        return OptionalLong.empty();
    }

    @Override
    public OptionalLong remove(long key) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Map<Long, Long> handle() {
        return Collections.emptyMap();
    }

    @Override
    public boolean isEmpty() {
        return true;
    }

    @Override
    public boolean containsKey(long key) {
        return false;
    }

    @Override
    public long getOrDefault(long key, long def) {
        return def;
    }

    @Override
    public long computeIfAbsent(long key, Long2LongFunction mappingFunction) {
        throw new UnsupportedOperationException();
    }

}
