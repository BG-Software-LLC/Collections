package com.bgsoftware.common.collections.longs;

import java.util.Iterator;
import java.util.Map;
import java.util.OptionalLong;
import java.util.Set;
import java.util.function.ToLongFunction;

public interface Object2LongMap<K> {

    int size();

    void clear();

    Set<Entry<K>> entrySet();

    Iterator<Entry<K>> entriesIterator();

    Set<K> keySet();

    Iterator<K> keysIterator();

    LongCollection values();

    LongIterator valuesIterator();

    OptionalLong put(K key, long value);

    OptionalLong get(K key);

    OptionalLong remove(K key);

    Map<K, Long> handle();

    default boolean isEmpty() {
        return size() <= 0;
    }

    default boolean containsKey(K key) {
        return get(key).isPresent();
    }

    default long getOrDefault(K key, long def) {
        return get(key).orElse(def);
    }

    default long computeIfAbsent(K key, ToLongFunction<K> mappingFunction) {
        OptionalLong valueOptional = get(key);
        if (valueOptional.isPresent())
            return valueOptional.getAsLong();
        long value = mappingFunction.applyAsLong(key);
        put(key, value);
        return value;
    }

    interface Entry<K> {

        K getKey();

        long getValue();

        long setValue(long newValue);

    }

}
