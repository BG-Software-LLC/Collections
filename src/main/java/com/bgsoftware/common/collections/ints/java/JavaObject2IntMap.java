package com.bgsoftware.common.collections.ints.java;

import com.bgsoftware.common.annotations.NotNull;
import com.bgsoftware.common.collections.ints.IntCollection;
import com.bgsoftware.common.collections.ints.IntIterator;
import com.bgsoftware.common.collections.ints.Object2IntMap;

import java.util.AbstractSet;
import java.util.Iterator;
import java.util.Map;
import java.util.OptionalInt;
import java.util.Set;
import java.util.function.ToIntFunction;

public class JavaObject2IntMap<K> implements Object2IntMap<K> {

    private final Map<K, Integer> handle;

    private EntrySet entrySet;
    private IntCollection values;

    public JavaObject2IntMap(Map<K, Integer> handle) {
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
    public IntCollection values() {
        return this.values == null ? (this.values = new JavaIntCollection(this.handle.values())) : this.values;
    }

    @Override
    public IntIterator valuesIterator() {
        return new JavaIntIterator(this.handle.values().iterator());
    }

    @Override
    public OptionalInt put(K key, int value) {
        Integer oldValue = this.handle.put(key, value);
        return oldValue == null ? OptionalInt.empty() : OptionalInt.of(oldValue);
    }

    @Override
    public OptionalInt get(K key) {
        Integer value = this.handle.get(key);
        return value == null ? OptionalInt.empty() : OptionalInt.of(value);
    }

    @Override
    public OptionalInt remove(K key) {
        Integer oldValue = this.handle.remove(key);
        return oldValue == null ? OptionalInt.empty() : OptionalInt.of(oldValue);
    }

    @Override
    public Map<K, Integer> handle() {
        return this.handle;
    }

    @Override
    public boolean isEmpty() {
        return this.handle.isEmpty();
    }

    @Override
    public boolean containsKey(K key) {
        return this.handle.containsKey(key);
    }

    @Override
    public int getOrDefault(K key, int def) {
        return this.handle.getOrDefault(key, def);
    }

    @Override
    public int computeIfAbsent(K key, ToIntFunction<K> mappingFunction) {
        return this.handle.computeIfAbsent(key, mappingFunction::applyAsInt);
    }

    @Override
    public String toString() {
        return this.handle.toString();
    }

    private class EntrySet extends AbstractSet<Entry<K>> {

        @Override
        public int size() {
            return JavaObject2IntMap.this.size();
        }

        @Override
        public boolean isEmpty() {
            return JavaObject2IntMap.this.isEmpty();
        }

        @Override
        public boolean contains(Object o) {
            return o instanceof Entry && JavaObject2IntMap.this.containsKey(((Entry<K>) o).getKey());
        }

        @NotNull
        @Override
        public Iterator<Entry<K>> iterator() {
            return new EntriesItr();
        }

        @Override
        public void clear() {
            JavaObject2IntMap.this.clear();
        }

    }

    private class EntriesItr implements Iterator<Entry<K>> {

        private final Iterator<Map.Entry<K, Integer>> handle =
                JavaObject2IntMap.this.handle.entrySet().iterator();

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

        private final Map.Entry<K, Integer> handle;

        public EntryImpl(Map.Entry<K, Integer> handle) {
            this.handle = handle;
        }

        @Override
        public K getKey() {
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
