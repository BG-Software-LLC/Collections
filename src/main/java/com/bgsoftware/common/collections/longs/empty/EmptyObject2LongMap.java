package com.bgsoftware.common.collections.longs.empty;

import com.bgsoftware.common.collections.longs.LongCollection;
import com.bgsoftware.common.collections.longs.LongIterator;
import com.bgsoftware.common.collections.longs.Object2LongMap;

import java.util.Collections;
import java.util.Iterator;
import java.util.Map;
import java.util.OptionalLong;
import java.util.Set;
import java.util.function.ToLongFunction;

public class EmptyObject2LongMap<K> implements Object2LongMap<K> {

    public static final EmptyObject2LongMap INSTANCE = new EmptyObject2LongMap();

    private EmptyObject2LongMap() {

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
    public Set<Entry<K>> entrySet() {
        return Collections.emptySet();
    }

    @Override
    public Iterator<Entry<K>> entriesIterator() {
        return Collections.emptyIterator();
    }

    @Override
    public Set<K> keySet() {
        return Collections.emptySet();
    }

    @Override
    public Iterator<K> keysIterator() {
        return Collections.emptyIterator();
    }

    @Override
    public LongCollection values() {
        return EmptyLongCollection.INSTANCE;
    }

    @Override
    public LongIterator valuesIterator() {
        return EmptyLongIterator.INSTANCE;
    }

    @Override
    public OptionalLong put(K key, long value) {
        throw new UnsupportedOperationException();
    }

    @Override
    public OptionalLong get(K key) {
        return OptionalLong.empty();
    }

    @Override
    public OptionalLong remove(K key) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Map<K, Long> handle() {
        return Collections.emptyMap();
    }

    @Override
    public boolean isEmpty() {
        return true;
    }

    @Override
    public boolean containsKey(K key) {
        return false;
    }

    @Override
    public long getOrDefault(K key, long def) {
        return def;
    }

    @Override
    public long computeIfAbsent(K key, ToLongFunction<K> mappingFunction) {
        throw new UnsupportedOperationException();
    }

}
