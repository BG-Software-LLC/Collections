package com.bgsoftware.common.collections.ints.empty;

import com.bgsoftware.common.collections.ints.IntCollection;
import com.bgsoftware.common.collections.ints.IntIterator;
import com.bgsoftware.common.collections.ints.Object2IntMap;

import java.util.Collections;
import java.util.Iterator;
import java.util.Map;
import java.util.OptionalInt;
import java.util.Set;
import java.util.function.ToIntFunction;

public class EmptyObject2IntMap<K> implements Object2IntMap<K> {

    public static final EmptyObject2IntMap INSTANCE = new EmptyObject2IntMap();

    private EmptyObject2IntMap() {

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
    public IntCollection values() {
        return EmptyIntCollection.INSTANCE;
    }

    @Override
    public IntIterator valuesIterator() {
        return EmptyIntIterator.INSTANCE;
    }

    @Override
    public OptionalInt put(K key, int value) {
        throw new UnsupportedOperationException();
    }

    @Override
    public OptionalInt get(K key) {
        return OptionalInt.empty();
    }

    @Override
    public OptionalInt remove(K key) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Map<K, Integer> handle() {
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
    public int getOrDefault(K key, int def) {
        return def;
    }

    @Override
    public int computeIfAbsent(K key, ToIntFunction<K> mappingFunction) {
        throw new UnsupportedOperationException();
    }
}
