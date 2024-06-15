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

    default boolean isEmpty() {
        return size() <= 0;
    }

    default boolean containsKey(int key) {
        return get(key) != null;
    }

    default V getOrDefault(int key, V def) {
        V value = get(key);
        return value == null ? def : value;
    }

    default V computeIfAbsent(int key, IntFunction<V> mappingFunction) {
        V value = get(key);
        if (value != null)
            return value;
        value = mappingFunction.apply(key);
        put(key, value);
        return value;
    }

    interface Entry<V> {

        int getKey();

        V getValue();

        V setValue(V newValue);

    }

}
