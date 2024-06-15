package com.bgsoftware.common.collections.ints;

import com.bgsoftware.common.annotations.Nullable;

import java.util.Collection;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.function.IntFunction;

public interface Int2ObjectMap<V> {

    int size();

    void clear();

    Set<Entry<V>> entrySet();

    Iterator<Entry<V>> entriesIterator();

    IntSet keySet();

    IntIterator keysIterator();

    Collection<V> values();

    Iterator<V> valuesIterator();

    @Nullable
    V put(int key, V value);

    @Nullable
    V get(int key);

    @Nullable
    V remove(int key);

    Map<Integer, V> handle();

    boolean isEmpty();

    boolean containsKey(int key);

    V getOrDefault(int key, V def);

    V computeIfAbsent(int key, IntFunction<V> mappingFunction);

    interface Entry<V> {

        int getKey();

        V getValue();

        V setValue(V newValue);

    }

}
