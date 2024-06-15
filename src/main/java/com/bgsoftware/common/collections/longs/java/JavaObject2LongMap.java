package com.bgsoftware.common.collections.longs.java;

import com.bgsoftware.common.annotations.NotNull;
import com.bgsoftware.common.collections.longs.LongCollection;
import com.bgsoftware.common.collections.longs.LongIterator;
import com.bgsoftware.common.collections.longs.Object2LongMap;

import java.util.AbstractSet;
import java.util.Iterator;
import java.util.Map;
import java.util.OptionalLong;
import java.util.Set;

public class JavaObject2LongMap<K> implements Object2LongMap<K> {

    private final Map<K, Long> handle;

    private EntrySet entrySet;
    private LongCollection values;

    public JavaObject2LongMap(Map<K, Long> handle) {
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
    public Set<Entry<K>> entrySet() {
        return this.entrySet == null ? (this.entrySet = new EntrySet()) : this.entrySet;
    }

    @Override
    public Iterator<Entry<K>> entriesIterator() {
        return new EntriesItr();
    }

    @Override
    public Set<K> keySet() {
        return this.handle.keySet();
    }

    @Override
    public Iterator<K> keysIterator() {
        return this.handle.keySet().iterator();
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
    public OptionalLong put(K key, long value) {
        Long oldValue = this.handle.put(key, value);
        return oldValue == null ? OptionalLong.empty() : OptionalLong.of(oldValue);
    }

    @Override
    public OptionalLong get(K key) {
        Long value = this.handle.get(key);
        return value == null ? OptionalLong.empty() : OptionalLong.of(value);
    }

    @Override
    public OptionalLong remove(K key) {
        Long oldValue = this.handle.remove(key);
        return oldValue == null ? OptionalLong.empty() : OptionalLong.of(oldValue);
    }

    @Override
    public Map<K, Long> handle() {
        return this.handle;
    }

    @Override
    public String toString() {
        return this.handle.toString();
    }

    private class EntrySet extends AbstractSet<Entry<K>> {

        @Override
        public int size() {
            return JavaObject2LongMap.this.size();
        }

        @Override
        public boolean isEmpty() {
            return JavaObject2LongMap.this.isEmpty();
        }

        @Override
        public boolean contains(Object o) {
            return o instanceof Entry && JavaObject2LongMap.this.containsKey(((Entry<K>) o).getKey());
        }

        @NotNull
        @Override
        public Iterator<Entry<K>> iterator() {
            return new EntriesItr();
        }

        @Override
        public void clear() {
            JavaObject2LongMap.this.clear();
        }

    }

    private class EntriesItr implements Iterator<Entry<K>> {

        private final Iterator<Map.Entry<K, Long>> handle =
                JavaObject2LongMap.this.handle.entrySet().iterator();

        @Override
        public boolean hasNext() {
            return this.handle.hasNext();
        }

        @Override
        public Entry<K> next() {
            return new EntryImpl(this.handle.next());
        }

        @Override
        public void remove() {
            this.handle.remove();
        }
    }

    private class EntryImpl implements Entry<K> {

        private final Map.Entry<K, Long> handle;

        public EntryImpl(Map.Entry<K, Long> handle) {
            this.handle = handle;
        }

        @Override
        public K getKey() {
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
