package com.bgsoftware.common.collections.ints.java;

import com.bgsoftware.common.annotations.NotNull;
import com.bgsoftware.common.collections.ints.Int2ObjectMap;
import com.bgsoftware.common.collections.ints.IntIterator;
import com.bgsoftware.common.collections.ints.IntSet;

import java.util.AbstractSet;
import java.util.Collection;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.function.IntFunction;

public class JavaInt2ObjectMap<V> implements Int2ObjectMap<V> {

    private final Map<Integer, V> handle;

    private EntrySet entrySet;
    private IntSet keySet;

    public JavaInt2ObjectMap(Map<Integer, V> handle) {
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
    public IntSet keySet() {
        return this.keySet == null ? (this.keySet = new JavaIntSet(this.handle.keySet())) : this.keySet;
    }

    @Override
    public IntIterator keysIterator() {
        return new JavaIntIterator(this.handle.keySet().iterator());
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
    public V put(int key, V value) {
        return this.handle.put(key, value);
    }

    @Override
    public V get(int key) {
        return this.handle.get(key);
    }

    @Override
    public V remove(int key) {
        return this.handle.remove(key);
    }

    @Override
    public Map<Integer, V> handle() {
        return this.handle;
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
    public V getOrDefault(int key, V def) {
        return this.handle.getOrDefault(key, def);
    }

    @Override
    public V computeIfAbsent(int key, IntFunction<V> mappingFunction) {
        return this.handle.computeIfAbsent(key, mappingFunction::apply);
    }

    @Override
    public String toString() {
        return this.handle.toString();
    }

    private class EntrySet extends AbstractSet<Entry<V>> {

        @Override
        public int size() {
            return JavaInt2ObjectMap.this.size();
        }

        @Override
        public boolean isEmpty() {
            return JavaInt2ObjectMap.this.isEmpty();
        }

        @Override
        public boolean contains(Object o) {
            return o instanceof Entry && JavaInt2ObjectMap.this.containsKey(((Entry<V>) o).getKey());
        }

        @NotNull
        @Override
        public Iterator<Entry<V>> iterator() {
            return new EntriesItr();
        }

        @Override
        public void clear() {
            JavaInt2ObjectMap.this.clear();
        }

    }

    private class EntriesItr implements Iterator<Entry<V>> {

        private final Iterator<Map.Entry<Integer, V>> handle =
                JavaInt2ObjectMap.this.handle.entrySet().iterator();

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

        private final Map.Entry<Integer, V> handle;

        public EntryImpl(Map.Entry<Integer, V> handle) {
            this.handle = handle;
        }

        @Override
        public int getKey() {
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
