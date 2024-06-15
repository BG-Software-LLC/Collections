package com.bgsoftware.common.collections.ints;

import java.util.Iterator;
import java.util.Map;
import java.util.OptionalInt;
import java.util.Set;

public interface Int2IntMap {

    int size();

    void clear();

    Set<Entry> entrySet();

    Iterator<Entry> entriesIterator();

    IntSet keySet();

    IntIterator keysIterator();

    IntCollection values();

    IntIterator valuesIterator();

    OptionalInt put(int key, int value);

    OptionalInt get(int key);

    OptionalInt remove(int key);

    Map<Integer, Integer> handle();

    default boolean isEmpty() {
        return size() <= 0;
    }

    default boolean containsKey(int key) {
        return get(key).isPresent();
    }

    default int getOrDefault(int key, int def) {
        OptionalInt value = get(key);
        return value.orElse(def);
    }

    default int computeIfAbsent(int key, Int2IntFunction mappingFunction) {
        OptionalInt valueOptional = get(key);
        if (valueOptional.isPresent())
            return valueOptional.getAsInt();
        int value = mappingFunction.apply(key);
        put(key, value);
        return value;
    }

    interface Entry {

        int getKey();

        int getValue();

        int setValue(int newValue);

    }

}
