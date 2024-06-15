package com.bgsoftware.common.collections.longs.immutable;

import com.bgsoftware.common.collections.internal.immutable.AbstractUnmodifiableMap;
import com.bgsoftware.common.collections.internal.immutable.UnmodifiableIterator;
import com.bgsoftware.common.collections.internal.immutable.UnmodifiableMap;
import com.bgsoftware.common.collections.internal.immutable.UnmodifiableSet;
import com.bgsoftware.common.collections.longs.Long2LongFunction;
import com.bgsoftware.common.collections.longs.Long2LongMap;
import com.bgsoftware.common.collections.longs.LongCollection;
import com.bgsoftware.common.collections.longs.LongIterator;
import com.bgsoftware.common.collections.longs.LongSet;

import java.util.Iterator;
import java.util.Map;
import java.util.OptionalLong;
import java.util.Set;

public class UnmodifiableLong2LongMap extends AbstractUnmodifiableMap<Long2LongMap,
        Map<Long, Long>, Set<Long2LongMap.Entry>, LongSet, LongCollection> implements Long2LongMap {

    public static UnmodifiableLong2LongMap create(Long2LongMap handle) {
        return handle instanceof UnmodifiableLong2LongMap ?
                (UnmodifiableLong2LongMap) handle :
                new UnmodifiableLong2LongMap(handle);
    }

    private UnmodifiableLong2LongMap(Long2LongMap handle) {
        super(handle);
    }

    @Override
    protected Set<Entry> createEntrySet() {
        return UnmodifiableSet.create(this.handle.entrySet());
    }

    @Override
    protected LongSet createKeySet() {
        return UnmodifiableLongSet.create(this.handle.keySet());
    }

    @Override
    protected LongCollection createValues() {
        return UnmodifiableLongCollection.create(this.handle.values());
    }

    @Override
    protected Map<Long, Long> createUnmodifiableHandle() {
        return UnmodifiableMap.create(this.handle.handle());
    }

    @Override
    public int size() {
        return this.handle.size();
    }

    @Override
    public void clear() {
        throw new UnsupportedOperationException();
    }

    @Override
    public Iterator<Entry> entriesIterator() {
        return UnmodifiableIterator.create(this.handle.entriesIterator());
    }

    @Override
    public LongIterator keysIterator() {
        return UnmodifiableLongIterator.create(this.handle.keysIterator());
    }

    @Override
    public LongIterator valuesIterator() {
        return UnmodifiableLongIterator.create(this.handle.valuesIterator());
    }

    @Override
    public OptionalLong put(long key, long value) {
        throw new UnsupportedOperationException();
    }

    @Override
    public OptionalLong get(long key) {
        return this.handle.get(key);
    }

    @Override
    public OptionalLong remove(long key) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean isEmpty() {
        return this.handle.isEmpty();
    }

    @Override
    public boolean containsKey(long key) {
        return this.handle.containsKey(key);
    }

    @Override
    public long getOrDefault(long key, long def) {
        return this.handle.getOrDefault(key, def);
    }

    @Override
    public long computeIfAbsent(long key, Long2LongFunction mappingFunction) {
        throw new UnsupportedOperationException();
    }

}
