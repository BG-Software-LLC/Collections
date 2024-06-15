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

    boolean isEmpty();

    boolean containsKey(int key);

    int getOrDefault(int key, int def);

    int computeIfAbsent(int key, Int2IntFunction mappingFunction);

    interface Entry {

        int getKey();

        int getValue();

        int setValue(int newValue);

    }

}
