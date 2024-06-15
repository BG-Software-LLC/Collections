package com.bgsoftware.common.collections.ints.empty;

import com.bgsoftware.common.collections.ints.Int2IntFunction;
import com.bgsoftware.common.collections.ints.Int2IntMap;
import com.bgsoftware.common.collections.ints.IntCollection;
import com.bgsoftware.common.collections.ints.IntIterator;
import com.bgsoftware.common.collections.ints.IntSet;

import java.util.Collections;
import java.util.Iterator;
import java.util.Map;
import java.util.OptionalInt;
import java.util.Set;

public class EmptyInt2IntMap implements Int2IntMap {

    public static final EmptyInt2IntMap INSTANCE = new EmptyInt2IntMap();

    private EmptyInt2IntMap() {

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
    public Set<Entry> entrySet() {
        return Collections.emptySet();
    }

    @Override
    public Iterator<Entry> entriesIterator() {
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
    public IntCollection values() {
        return EmptyIntCollection.INSTANCE;
    }

    @Override
    public IntIterator valuesIterator() {
        return EmptyIntIterator.INSTANCE;
    }

    @Override
    public OptionalInt put(int key, int value) {
        throw new UnsupportedOperationException();
    }

    @Override
    public OptionalInt get(int key) {
        return OptionalInt.empty();
    }

    @Override
    public OptionalInt remove(int key) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Map<Integer, Integer> handle() {
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
    public int getOrDefault(int key, int def) {
        return def;
    }

    @Override
    public int computeIfAbsent(int key, Int2IntFunction mappingFunction) {
        throw new UnsupportedOperationException();
    }

}
