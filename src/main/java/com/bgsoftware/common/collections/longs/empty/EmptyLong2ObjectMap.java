package com.bgsoftware.common.collections.longs.empty;

import com.bgsoftware.common.collections.longs.Long2ObjectMap;
import com.bgsoftware.common.collections.longs.LongIterator;
import com.bgsoftware.common.collections.longs.LongSet;

import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.function.LongFunction;

public class EmptyLong2ObjectMap<V> implements Long2ObjectMap<V> {

    public static final EmptyLong2ObjectMap INSTANCE = new EmptyLong2ObjectMap();

    private EmptyLong2ObjectMap() {

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
    public Set<Entry<V>> entrySet() {
        return Collections.emptySet();
    }

    @Override
    public Iterator<Entry<V>> entriesIterator() {
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
    public Collection<V> values() {
        return Collections.emptyList();
    }

    @Override
    public Iterator<V> valuesIterator() {
        return Collections.emptyIterator();
    }

    @Override
    public V put(long key, V value) {
        throw new UnsupportedOperationException();
    }

    @Override
    public V get(long key) {
        return null;
    }

    @Override
    public V remove(long key) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Map<Long, V> handle() {
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
    public V getOrDefault(long key, V def) {
        return def;
    }

    @Override
    public V computeIfAbsent(long key, LongFunction<V> mappingFunction) {
        throw new UnsupportedOperationException();
    }

}
