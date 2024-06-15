package com.bgsoftware.common.collections.longs.java;

import com.bgsoftware.common.annotations.NotNull;
import com.bgsoftware.common.collections.longs.Long2LongMap;
import com.bgsoftware.common.collections.longs.LongCollection;
import com.bgsoftware.common.collections.longs.LongIterator;
import com.bgsoftware.common.collections.longs.LongSet;

import java.util.AbstractSet;
import java.util.Iterator;
import java.util.Map;
import java.util.OptionalLong;
import java.util.Set;

public class JavaLong2LongMap implements Long2LongMap {

    private final Map<Long, Long> handle;

    private EntrySet entrySet;
    private LongSet keySet;
    private LongCollection values;

    public JavaLong2LongMap(Map<Long, Long> handle) {
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
    public LongSet keySet() {
        return this.keySet == null ? (this.keySet = new JavaLongSet(this.handle.keySet())) : this.keySet;
    }

    @Override
    public LongIterator keysIterator() {
        return new JavaLongIterator(this.handle.keySet().iterator());
    }

    @Override
    public LongCollection values() {
        return this.values == null ? (this.values = new JavaLongCollection(this.handle.values())) : this.values;
    }

    @Override
    public LongIterator valuesIterator() {
        return new JavaLongIterator(this.handle.values().iterator());
    }

    @Override
    public OptionalLong put(long key, long value) {
        Long oldValue = this.handle.put(key, value);
        return oldValue == null ? OptionalLong.empty() : OptionalLong.of(oldValue);
    }

    @Override
    public OptionalLong get(long key) {
        Long value = this.handle.get(key);
        return value == null ? OptionalLong.empty() : OptionalLong.of(value);
    }

    @Override
    public OptionalLong remove(long key) {
        Long oldValue = this.handle.remove(key);
        return oldValue == null ? OptionalLong.empty() : OptionalLong.of(oldValue);
    }

    @Override
    public Map<Long, Long> handle() {
        return this.handle;
    }

    @Override
    public String toString() {
        return this.handle.toString();
    }

    private class EntrySet extends AbstractSet<Entry> {

        @Override
        public int size() {
            return JavaLong2LongMap.this.size();
        }

        @Override
        public boolean isEmpty() {
            return JavaLong2LongMap.this.isEmpty();
        }

        @Override
        public boolean contains(Object o) {
            return o instanceof Entry && JavaLong2LongMap.this.containsKey(((Entry) o).getKey());
        }

        @NotNull
        @Override
        public Iterator<Entry> iterator() {
            return new EntriesItr();
        }

        @Override
        public void clear() {
            JavaLong2LongMap.this.clear();
        }

    }

    private class EntriesItr implements Iterator<Entry> {

        private final Iterator<Map.Entry<Long, Long>> handle =
                JavaLong2LongMap.this.handle.entrySet().iterator();

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

        private final Map.Entry<Long, Long> handle;

        public EntryImpl(Map.Entry<Long, Long> handle) {
            this.handle = handle;
        }

        @Override
        public long getKey() {
            return this.handle.getKey();
        }

        @Override
        public long getValue() {
            return this.handle.getValue();
        }

        @Override
        public long setValue(long newValue) {
            return this.handle.setValue(newValue);
        }
    }

}
