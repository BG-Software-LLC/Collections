package com.bgsoftware.common.collections.ints.fastutils;

import com.bgsoftware.common.annotations.NotNull;
import com.bgsoftware.common.collections.internal.maps.AbstractMap;
import com.bgsoftware.common.collections.ints.Int2IntFunction;
import com.bgsoftware.common.collections.ints.Int2IntMap;
import com.bgsoftware.common.collections.ints.IntCollection;
import com.bgsoftware.common.collections.ints.IntIterator;
import com.bgsoftware.common.collections.ints.IntSet;

import java.util.AbstractSet;
import java.util.Iterator;
import java.util.OptionalInt;
import java.util.Set;

public class FastUtilsInt2IntMap extends AbstractMap<Integer, Integer, it.unimi.dsi.fastutil.ints.Int2IntMap,
        Set<Int2IntMap.Entry>, IntSet, IntCollection> implements Int2IntMap {

    public FastUtilsInt2IntMap(it.unimi.dsi.fastutil.ints.Int2IntMap handle) {
        super(handle);
    }

    @Override
    protected Set<Entry> createEntrySet() {
        return new EntrySet();
    }

    @Override
    public Iterator<Entry> entriesIterator() {
        return new EntriesItr();
    }

    @Override
    protected IntSet createKeySet() {
        return new FastUtilsIntSet(this.handle.keySet());
    }

    @Override
    public IntIterator keysIterator() {
        return new FastUtilsIntIterator(this.handle.keySet().iterator());
    }

    @Override
    protected IntCollection createValues() {
        return new FastUtilsIntCollection(this.handle.values());
    }

    @Override
    public IntIterator valuesIterator() {
        return new FastUtilsIntIterator(this.handle.values().iterator());
    }

    @Override
    public OptionalInt put(int key, int value) {
        boolean containsKey = containsKey(key);
        int oldValue = this.handle.put(key, value);
        return containsKey ? OptionalInt.of(oldValue) : OptionalInt.empty();
    }

    @Override
    public OptionalInt get(int key) {
        return containsKey(key) ? OptionalInt.of(this.handle.get(key)) : OptionalInt.empty();
    }

    @Override
    public OptionalInt remove(int key) {
        return containsKey(key) ? OptionalInt.of(this.handle.remove(key)) : OptionalInt.empty();
    }

    @Override
    public boolean isEmpty() {
        return this.handle.isEmpty();
    }

    @Override
    public boolean containsKey(int key) {
        return this.handle.containsKey(key);
    }

    @Override
    public int getOrDefault(int key, int def) {
        return this.handle.getOrDefault(key, def);
    }

    @Override
    public int computeIfAbsent(int key, Int2IntFunction mappingFunction) {
        return this.handle.computeIfAbsent(key, mappingFunction::apply);
    }

    private class EntrySet extends AbstractSet<Entry> {

        @Override
        public int size() {
            return FastUtilsInt2IntMap.this.size();
        }

        @Override
        public boolean isEmpty() {
            return FastUtilsInt2IntMap.this.isEmpty();
        }

        @Override
        public boolean contains(Object o) {
            return o instanceof Entry && FastUtilsInt2IntMap.this.containsKey(((Entry) o).getKey());
        }

        @NotNull
        @Override
        public Iterator<Entry> iterator() {
            return new EntriesItr();
        }

        @Override
        public void clear() {
            FastUtilsInt2IntMap.this.clear();
        }

    }

    private class EntriesItr implements Iterator<Entry> {

        private final Iterator<it.unimi.dsi.fastutil.ints.Int2IntMap.Entry> handle =
                FastUtilsInt2IntMap.this.handle.int2IntEntrySet().iterator();

        @Override
        public boolean hasNext() {
            return this.handle.hasNext();
        }

        @Override
        public Entry next() {
            return new EntryImpl(this.handle.next());
        }

        @Override
        public void remove() {
            this.handle.remove();
        }
    }

    private static class EntryImpl implements Entry {

        private final it.unimi.dsi.fastutil.ints.Int2IntMap.Entry handle;

        public EntryImpl(it.unimi.dsi.fastutil.ints.Int2IntMap.Entry handle) {
            this.handle = handle;
        }

        @Override
        public int getKey() {
            return this.handle.getIntKey();
        }

        @Override
        public int getValue() {
            return this.handle.getIntValue();
        }

        @Override
        public int setValue(int newValue) {
            return this.handle.setValue(newValue);
        }
    }

}
