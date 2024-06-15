package com.bgsoftware.common.collections.ints;

import java.util.Iterator;
import java.util.Map;
import java.util.OptionalInt;
import java.util.Set;
import java.util.function.ToIntFunction;

public interface Object2IntMap<K> {

    int size();

    void clear();

    Set<Entry<K>> entrySet();

    Iterator<Entry<K>> entriesIterator();

    Set<K> keySet();

    Iterator<K> keysIterator();

    IntCollection values();

    IntIterator valuesIterator();

    OptionalInt put(K key, int value);

    OptionalInt get(K key);

    OptionalInt remove(K key);

    Map<K, Integer> handle();

    default boolean isEmpty() {
        return size() <= 0;
    }

    default boolean containsKey(K key) {
        return get(key).isPresent();
    }

    default int getOrDefault(K key, int def) {
        return get(key).orElse(def);
    }

    default int computeIfAbsent(K key, ToIntFunction<K> mappingFunction) {
        OptionalInt valueOptional = get(key);
        if (valueOptional.isPresent())
            return valueOptional.getAsInt();
        int value = mappingFunction.applyAsInt(key);
        put(key, value);
        return value;
    }

    interface Entry<K> {

        K getKey();

        int getValue();

        int setValue(int newValue);

    }

}
