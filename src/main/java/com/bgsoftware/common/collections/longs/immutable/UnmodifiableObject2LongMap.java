package com.bgsoftware.common.collections.longs.immutable;

import com.bgsoftware.common.collections.internal.immutable.AbstractUnmodifiableMap;
import com.bgsoftware.common.collections.internal.immutable.UnmodifiableIterator;
import com.bgsoftware.common.collections.internal.immutable.UnmodifiableMap;
import com.bgsoftware.common.collections.internal.immutable.UnmodifiableSet;
import com.bgsoftware.common.collections.longs.LongCollection;
import com.bgsoftware.common.collections.longs.LongIterator;
import com.bgsoftware.common.collections.longs.Object2LongMap;

import java.util.Iterator;
import java.util.Map;
import java.util.OptionalLong;
import java.util.Set;
import java.util.function.ToLongFunction;

public class UnmodifiableObject2LongMap<K> extends AbstractUnmodifiableMap<Object2LongMap<K>,
        Map<K, Long>, Set<Object2LongMap.Entry<K>>, Set<K>, LongCollection> implements Object2LongMap<K> {

    public static <K> UnmodifiableObject2LongMap<K> create(Object2LongMap<K> handle) {
        return handle instanceof UnmodifiableObject2LongMap ?
                (UnmodifiableObject2LongMap<K>) handle :
                new UnmodifiableObject2LongMap<>(handle);
    }

    private UnmodifiableObject2LongMap(Object2LongMap<K> handle) {
        super(handle);
    }

    @Override
    protected Set<Entry<K>> createEntrySet() {
        return UnmodifiableSet.create(this.handle.entrySet());
    }

    @Override
    protected Set<K> createKeySet() {
        return UnmodifiableSet.create(this.handle.keySet());
    }

    @Override
    protected LongCollection createValues() {
        return UnmodifiableLongCollection.create(this.handle.values());
    }

    @Override
    protected Map<K, Long> createUnmodifiableHandle() {
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
    public Iterator<Entry<K>> entriesIterator() {
        return UnmodifiableIterator.create(this.handle.entriesIterator());
    }

    @Override
    public Iterator<K> keysIterator() {
        return UnmodifiableIterator.create(this.handle.keysIterator());
    }

    @Override
    public LongIterator valuesIterator() {
        return UnmodifiableLongIterator.create(this.handle.valuesIterator());
    }

    @Override
    public OptionalLong put(K key, long value) {
        throw new UnsupportedOperationException();
    }

    @Override
    public OptionalLong get(K key) {
        return this.handle.get(key);
    }

    @Override
    public OptionalLong remove(K key) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean isEmpty() {
        return this.handle.isEmpty();
    }

    @Override
    public boolean containsKey(K key) {
        return this.handle.containsKey(key);
    }

    @Override
    public long getOrDefault(K key, long def) {
        return this.handle.getOrDefault(key, def);
    }

    @Override
    public long computeIfAbsent(K key, ToLongFunction<K> mappingFunction) {
        throw new UnsupportedOperationException();
    }

}
