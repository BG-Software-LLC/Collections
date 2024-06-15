package com.bgsoftware.common.collections.longs.java;

import com.bgsoftware.common.annotations.NotNull;
import com.bgsoftware.common.collections.longs.Long2ObjectMap;
import com.bgsoftware.common.collections.longs.LongIterator;
import com.bgsoftware.common.collections.longs.LongSet;

import java.util.AbstractSet;
import java.util.Collection;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

public class JavaLong2ObjectMap<V> implements Long2ObjectMap<V> {

    private final Map<Long, V> handle;

    private EntrySet entrySet;
    private LongSet keySet;

    public JavaLong2ObjectMap(Map<Long, V> handle) {
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
    public Set<Entry<V>> entrySet() {
        return this.entrySet == null ? (this.entrySet = new EntrySet()) : this.entrySet;
    }

    @Override
    public Iterator<Entry<V>> entriesIterator() {
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
    public Collection<V> values() {
        return this.handle.values();
    }

    @Override
    public Iterator<V> valuesIterator() {
        return this.handle.values().iterator();
    }

    @Override
    public V put(long key, V value) {
        return this.handle.put(key, value);
    }

    @Override
    public V get(long key) {
        return this.handle.get(key);
    }

    @Override
    public V remove(long key) {
        return this.handle.remove(key);
    }

    @Override
    public Map<Long, V> handle() {
        return this.handle;
    }

    @Override
    public String toString() {
        return this.handle.toString();
    }

    private class EntrySet extends AbstractSet<Entry<V>> {

        @Override
        public int size() {
            return JavaLong2ObjectMap.this.size();
        }

        @Override
        public boolean isEmpty() {
            return JavaLong2ObjectMap.this.isEmpty();
        }

        @Override
        public boolean contains(Object o) {
            return o instanceof Entry && JavaLong2ObjectMap.this.containsKey(((Entry<?>) o).getKey());
        }

        @NotNull
        @Override
        public Iterator<Entry<V>> iterator() {
            return new EntriesItr();
        }

        @Override
        public void clear() {
            JavaLong2ObjectMap.this.clear();
        }

    }

    private class EntriesItr implements Iterator<Entry<V>> {

        private final Iterator<Map.Entry<Long, V>> handle =
                JavaLong2ObjectMap.this.handle.entrySet().iterator();

        @Override
        public boolean hasNext() {
            return this.handle.hasNext();
        }

        @Override
        public Entry<V> next() {
            return new EntryImpl(this.handle.next());
        }

        @Override
        public void remove() {
            this.handle.remove();
        }
    }

    private class EntryImpl implements Entry<V> {

        private final Map.Entry<Long, V> handle;

        public EntryImpl(Map.Entry<Long, V> handle) {
            this.handle = handle;
        }

        @Override
        public long getKey() {
            return this.handle.getKey();
        }

        @Override
        public V getValue() {
            return this.handle.getValue();
        }

        @Override
        public V setValue(V newValue) {
            return this.handle.setValue(newValue);
        }
    }

}
