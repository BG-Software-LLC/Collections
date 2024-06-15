package com.bgsoftware.common.collections.longs;

import java.util.Collection;
import java.util.Iterator;
import java.util.Map;
import java.util.OptionalLong;
import java.util.Set;

public interface Long2LongMap {

    int size();

    void clear();

    Set<Entry> entrySet();

    Iterator<Entry> entriesIterator();

    LongSet keySet();

    LongIterator keysIterator();

    LongCollection values();

    LongIterator valuesIterator();

    OptionalLong put(long key, long value);

    OptionalLong get(long key);

    OptionalLong remove(long key);

    Map<Long, Long> handle();

    default boolean isEmpty() {
        return size() <= 0;
    }

    default boolean containsKey(long key) {
        return get(key).isPresent();
    }

    default long getOrDefault(long key, long def) {
        OptionalLong value = get(key);
        return value.orElse(def);
    }

    default long computeIfAbsent(long key, Long2LongFunction mappingFunction) {
        OptionalLong valueOptional = get(key);
        if (valueOptional.isPresent())
            return valueOptional.getAsLong();
        long value = mappingFunction.apply(key);
        put(key, value);
        return value;
    }

    interface Entry {

        long getKey();

        long getValue();

        long setValue(long newValue);

    }

}
