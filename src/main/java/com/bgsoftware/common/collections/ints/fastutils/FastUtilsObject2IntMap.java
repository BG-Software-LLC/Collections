package com.bgsoftware.common.collections.ints.fastutils;

import com.bgsoftware.common.annotations.NotNull;
import com.bgsoftware.common.collections.internal.maps.AbstractMap;
import com.bgsoftware.common.collections.ints.IntCollection;
import com.bgsoftware.common.collections.ints.IntIterator;
import com.bgsoftware.common.collections.ints.Object2IntMap;

import java.util.AbstractSet;
import java.util.Iterator;
import java.util.OptionalInt;
import java.util.Set;
import java.util.function.ToIntFunction;

public class FastUtilsObject2IntMap<K> extends AbstractMap<K, Integer, it.unimi.dsi.fastutil.objects.Object2IntMap<K>,
        Set<Object2IntMap.Entry<K>>, Set<K>, IntCollection> implements Object2IntMap<K> {


    public FastUtilsObject2IntMap(it.unimi.dsi.fastutil.objects.Object2IntMap<K> handle) {
        super(handle);
    }

    @Override
    protected Set<Entry<K>> createEntrySet() {
        return new EntrySet();
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
    protected Set<K> createKeySet() {
        throw new UnsupportedOperationException();
    }

    @Override
    public Iterator<K> keysIterator() {
        return this.handle.keySet().iterator();
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
    public OptionalInt put(K key, int value) {
        boolean containsKey = containsKey(key);
        int oldValue = this.handle.put(key, value);
        return containsKey ? OptionalInt.of(oldValue) : OptionalInt.empty();
    }

    @Override
    public OptionalInt get(K key) {
        return containsKey(key) ? OptionalInt.of(this.handle.getInt(key)) : OptionalInt.empty();
    }

    @Override
    public OptionalInt remove(K key) {
        return containsKey(key) ? OptionalInt.of(this.handle.removeInt(key)) : OptionalInt.empty();
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
        return this.handle.computeIfAbsent(key, mappingFunction);
    }

    private class EntrySet extends AbstractSet<Entry<K>> {

        @Override
        public int size() {
            return FastUtilsObject2IntMap.this.size();
        }

        @Override
        public boolean isEmpty() {
            return FastUtilsObject2IntMap.this.isEmpty();
        }

        @Override
        public boolean contains(Object o) {
            return o instanceof Entry && FastUtilsObject2IntMap.this.containsKey(((Entry<K>) o).getKey());
        }

        @NotNull
        @Override
        public Iterator<Entry<K>> iterator() {
            return new EntriesItr();
        }

        @Override
        public void clear() {
            FastUtilsObject2IntMap.this.clear();
        }

    }

    private class EntriesItr implements Iterator<Entry<K>> {

        private final Iterator<it.unimi.dsi.fastutil.objects.Object2IntMap.Entry<K>> handle =
                FastUtilsObject2IntMap.this.handle.object2IntEntrySet().iterator();

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

        private final it.unimi.dsi.fastutil.objects.Object2IntMap.Entry<K> handle;

        public EntryImpl(it.unimi.dsi.fastutil.objects.Object2IntMap.Entry<K> handle) {
            this.handle = handle;
        }

        @Override
        public K getKey() {
            return this.handle.getKey();
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
