package com.bgsoftware.common.collections.ints.java;

import com.bgsoftware.common.annotations.NotNull;
import com.bgsoftware.common.collections.ints.Int2IntMap;
import com.bgsoftware.common.collections.ints.IntCollection;
import com.bgsoftware.common.collections.ints.IntIterator;
import com.bgsoftware.common.collections.ints.IntSet;

import java.util.AbstractSet;
import java.util.Iterator;
import java.util.Map;
import java.util.OptionalInt;
import java.util.Set;

public class JavaInt2IntMap implements Int2IntMap {

    private final Map<Integer, Integer> handle;

    private EntrySet entrySet;
    private IntSet keySet;
    private IntCollection values;

    public JavaInt2IntMap(Map<Integer, Integer> handle) {
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
        return this.keySet == null ? (this.keySet = new JavaIntSet(this.handle.keySet())) : this.keySet;
    }

    @Override
    public IntIterator keysIterator() {
        return new JavaIntIterator(this.handle.keySet().iterator());
    }

    @Override
    public IntCollection values() {
        return this.values == null ? (this.values = new JavaIntCollection(this.handle.values())) : this.values;
    }

    @Override
    public IntIterator valuesIterator() {
        return new JavaIntIterator(this.handle.values().iterator());
    }

    @Override
    public OptionalInt put(int key, int value) {
        Integer oldValue = this.handle.put(key, value);
        return oldValue == null ? OptionalInt.empty() : OptionalInt.of(oldValue);
    }

    @Override
    public OptionalInt get(int key) {
        Integer value = this.handle.get(key);
        return value == null ? OptionalInt.empty() : OptionalInt.of(value);
    }

    @Override
    public OptionalInt remove(int key) {
        Integer oldValue = this.handle.remove(key);
        return oldValue == null ? OptionalInt.empty() : OptionalInt.of(oldValue);
    }

    @Override
    public Map<Integer, Integer> handle() {
        return this.handle;
    }

    @Override
    public String toString() {
        return this.handle.toString();
    }

    private class EntrySet extends AbstractSet<Entry> {

        @Override
        public int size() {
            return JavaInt2IntMap.this.size();
        }

        @Override
        public boolean isEmpty() {
            return JavaInt2IntMap.this.isEmpty();
        }

        @Override
        public boolean contains(Object o) {
            return o instanceof Entry && JavaInt2IntMap.this.containsKey(((Entry) o).getKey());
        }

        @NotNull
        @Override
        public Iterator<Entry> iterator() {
            return new EntriesItr();
        }

        @Override
        public void clear() {
            JavaInt2IntMap.this.clear();
        }

    }

    private class EntriesItr implements Iterator<Entry> {

        private final Iterator<Map.Entry<Integer, Integer>> handle =
                JavaInt2IntMap.this.handle.entrySet().iterator();

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

        private final Map.Entry<Integer, Integer> handle;

        public EntryImpl(Map.Entry<Integer, Integer> handle) {
            this.handle = handle;
        }

        @Override
        public int getKey() {
            return this.handle.getKey();
        }

        @Override
        public int getValue() {
            return this.handle.getValue();
        }

        @Override
        public int setValue(int newValue) {
            return this.handle.setValue(newValue);
        }
    }

}
