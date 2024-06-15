package com.bgsoftware.common.collections.ints.empty;

import com.bgsoftware.common.collections.ints.Int2ObjectMap;
import com.bgsoftware.common.collections.ints.IntIterator;
import com.bgsoftware.common.collections.ints.IntSet;

import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.function.IntFunction;

public class EmptyInt2ObjectMap<V> implements Int2ObjectMap<V> {

    public static final EmptyInt2ObjectMap INSTANCE = new EmptyInt2ObjectMap();

    private EmptyInt2ObjectMap() {

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
    public IntSet keySet() {
        return EmptyIntSet.INSTANCE;
    }

    @Override
    public IntIterator keysIterator() {
        return EmptyIntIterator.INSTANCE;
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
    public V put(int key, V value) {
        throw new UnsupportedOperationException();
    }

    @Override
    public V get(int key) {
        return null;
    }

    @Override
    public V remove(int key) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Map<Integer, V> handle() {
        return Collections.emptyMap();
    }

    @Override
    public boolean isEmpty() {
        return true;
    }

    @Override
    public boolean containsKey(int key) {
        return false;
    }

    @Override
    public V getOrDefault(int key, V def) {
        return def;
    }

    @Override
    public V computeIfAbsent(int key, IntFunction<V> mappingFunction) {
        throw new UnsupportedOperationException();
    }

}
