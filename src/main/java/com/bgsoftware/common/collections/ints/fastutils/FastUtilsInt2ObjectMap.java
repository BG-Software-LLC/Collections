package com.bgsoftware.common.collections.ints.fastutils;

import com.bgsoftware.common.annotations.NotNull;
import com.bgsoftware.common.collections.internal.maps.AbstractMap;
import com.bgsoftware.common.collections.ints.Int2ObjectMap;
import com.bgsoftware.common.collections.ints.IntIterator;
import com.bgsoftware.common.collections.ints.IntSet;

import java.util.AbstractSet;
import java.util.Collection;
import java.util.Iterator;
import java.util.Set;
import java.util.function.IntFunction;

public class FastUtilsInt2ObjectMap<V> extends AbstractMap<Integer, V, it.unimi.dsi.fastutil.ints.Int2ObjectMap<V>,
        Set<Int2ObjectMap.Entry<V>>, IntSet, Collection<V>> implements Int2ObjectMap<V> {

    public FastUtilsInt2ObjectMap(it.unimi.dsi.fastutil.ints.Int2ObjectMap<V> handle) {
        super(handle);
    }

    @Override
    protected Set<Entry<V>> createEntrySet() {
        return new EntrySet();
    }

    @Override
    public Iterator<Entry<V>> entriesIterator() {
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
    public Collection<V> values() {
        return this.handle.values();
    }

    @Override
    protected Collection<V> createValues() {
        throw new UnsupportedOperationException();
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
        return this.handle.computeIfAbsent(key, mappingFunction);
    }

    private class EntrySet extends AbstractSet<Entry<V>> {

        @Override
        public int size() {
            return FastUtilsInt2ObjectMap.this.size();
        }

        @Override
        public boolean isEmpty() {
            return FastUtilsInt2ObjectMap.this.isEmpty();
        }

        @Override
        public boolean contains(Object o) {
            return o instanceof Entry && FastUtilsInt2ObjectMap.this.containsKey(((Entry<V>) o).getKey());
        }

        @NotNull
        @Override
        public Iterator<Entry<V>> iterator() {
            return new EntriesItr();
        }

        @Override
        public void clear() {
            FastUtilsInt2ObjectMap.this.clear();
        }

    }

    private class EntriesItr implements Iterator<Entry<V>> {

        private final Iterator<it.unimi.dsi.fastutil.ints.Int2ObjectMap.Entry<V>> handle =
                FastUtilsInt2ObjectMap.this.handle.int2ObjectEntrySet().iterator();

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

        private final it.unimi.dsi.fastutil.ints.Int2ObjectMap.Entry<V> handle;

        public EntryImpl(it.unimi.dsi.fastutil.ints.Int2ObjectMap.Entry<V> handle) {
            this.handle = handle;
        }

        @Override
        public int getKey() {
            return this.handle.getIntKey();
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
