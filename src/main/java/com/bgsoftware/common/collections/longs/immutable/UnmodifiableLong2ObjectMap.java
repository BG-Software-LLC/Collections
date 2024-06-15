package com.bgsoftware.common.collections.longs.immutable;

import com.bgsoftware.common.collections.internal.immutable.AbstractUnmodifiableMap;
import com.bgsoftware.common.collections.internal.immutable.UnmodifiableCollection;
import com.bgsoftware.common.collections.internal.immutable.UnmodifiableIterator;
import com.bgsoftware.common.collections.internal.immutable.UnmodifiableMap;
import com.bgsoftware.common.collections.internal.immutable.UnmodifiableSet;
import com.bgsoftware.common.collections.longs.Long2ObjectMap;
import com.bgsoftware.common.collections.longs.LongIterator;
import com.bgsoftware.common.collections.longs.LongSet;

import java.util.Collection;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.function.LongFunction;

public class UnmodifiableLong2ObjectMap<V> extends AbstractUnmodifiableMap<Long2ObjectMap<V>,
        Map<Long, V>, Set<Long2ObjectMap.Entry<V>>, LongSet, Collection<V>> implements Long2ObjectMap<V> {

    public static <V> UnmodifiableLong2ObjectMap<V> create(Long2ObjectMap<V> handle) {
        return handle instanceof UnmodifiableLong2ObjectMap ?
                (UnmodifiableLong2ObjectMap<V>) handle :
                new UnmodifiableLong2ObjectMap<>(handle);
    }

    private UnmodifiableLong2ObjectMap(Long2ObjectMap<V> handle) {
        super(handle);
    }

    @Override
    protected Set<Entry<V>> createEntrySet() {
        return UnmodifiableSet.create(this.handle.entrySet());
    }

    @Override
    protected LongSet createKeySet() {
        return UnmodifiableLongSet.create(this.handle.keySet());
    }

    @Override
    protected Collection<V> createValues() {
        return UnmodifiableCollection.create(this.handle.values());
    }

    @Override
    protected Map<Long, V> createUnmodifiableHandle() {
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
    public Iterator<Entry<V>> entriesIterator() {
        return UnmodifiableIterator.create(this.handle.entriesIterator());
    }

    @Override
    public LongIterator keysIterator() {
        return UnmodifiableLongIterator.create(this.handle.keysIterator());
    }

    @Override
    public Iterator<V> valuesIterator() {
        return UnmodifiableIterator.create(this.handle.valuesIterator());
    }

    @Override
    public V put(long key, V value) {
        throw new UnsupportedOperationException();
    }

    @Override
    public V get(long key) {
        return this.handle.get(key);
    }

    @Override
    public V remove(long key) {
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
    public V getOrDefault(long key, V def) {
        return this.handle.getOrDefault(key, def);
    }

    @Override
    public V computeIfAbsent(long key, LongFunction<V> mappingFunction) {
        throw new UnsupportedOperationException();
    }

}
