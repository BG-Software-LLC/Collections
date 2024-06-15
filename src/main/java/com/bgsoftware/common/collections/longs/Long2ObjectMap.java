package com.bgsoftware.common.collections.longs;

import com.bgsoftware.common.annotations.Nullable;

import java.util.Collection;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.function.LongFunction;

public interface Long2ObjectMap<V> {

    int size();

    void clear();

    Set<Entry<V>> entrySet();

    Iterator<Entry<V>> entriesIterator();

    LongSet keySet();

    LongIterator keysIterator();

    Collection<V> values();

    Iterator<V> valuesIterator();

    @Nullable
    V put(long key, V value);

    @Nullable
    V get(long key);

    @Nullable
    V remove(long key);

    Map<Long, V> handle();

    default boolean isEmpty() {
        return size() <= 0;
    }

    default boolean containsKey(long key) {
        return get(key) != null;
    }

    default V getOrDefault(long key, V def) {
        V value = get(key);
        return value == null ? def : value;
    }

    default V computeIfAbsent(long key, LongFunction<V> mappingFunction) {
        V value = get(key);
        if (value != null)
            return value;
        value = mappingFunction.apply(key);
        put(key, value);
        return value;
    }

    interface Entry<V> {

        long getKey();

        V getValue();

        V setValue(V newValue);

    }

}
