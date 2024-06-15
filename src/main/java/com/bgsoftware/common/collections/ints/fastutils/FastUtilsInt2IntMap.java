package com.bgsoftware.common.collections.ints.fastutils;

import com.bgsoftware.common.annotations.NotNull;
import com.bgsoftware.common.collections.ints.Int2IntFunction;
import com.bgsoftware.common.collections.ints.Int2IntMap;
import com.bgsoftware.common.collections.ints.IntCollection;
import com.bgsoftware.common.collections.ints.IntIterator;
import com.bgsoftware.common.collections.ints.IntSet;

import java.util.AbstractSet;
import java.util.Iterator;
import java.util.Map;
import java.util.OptionalInt;
import java.util.Set;

public class FastUtilsInt2IntMap implements Int2IntMap {

    private final it.unimi.dsi.fastutil.ints.Int2IntMap handle;

    private EntrySet entrySet;
    private IntSet keySet;
    private IntCollection values;

    public FastUtilsInt2IntMap(it.unimi.dsi.fastutil.ints.Int2IntMap handle) {
        this.handle = handle;
    }

    @Override
    public int size() {
        return this.handle.size();
    }

    @Override
    public void clear() {
        this.handle.clear();
    }

    @Override
    public Set<Entry> entrySet() {
        return this.entrySet == null ? (this.entrySet = new EntrySet()) : this.entrySet;
    }

    @Override
    public Iterator<Entry> entriesIterator() {
        return new EntriesItr();
    }

    @Override
    public IntSet keySet() {
        return this.keySet == null ? (this.keySet = new FastUtilsIntSet(this.handle.keySet())) : this.keySet;
    }

    @Override
    public IntIterator keysIterator() {
        return new FastUtilsIntIterator(this.handle.keySet().iterator());
    }

    @Override
    public IntCollection values() {
        return this.values == null ? (this.values = new FastUtilsIntCollection(this.handle.values())) : this.values;
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
    public Map<Integer, Integer> handle() {
        return this.handle;
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

    @Override
    public String toString() {
        return this.handle.toString();
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
