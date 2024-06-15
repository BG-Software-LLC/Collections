package com.bgsoftware.common.collections.longs.fastutils;

import com.bgsoftware.common.annotations.NotNull;
import com.bgsoftware.common.collections.longs.LongCollection;
import com.bgsoftware.common.collections.longs.LongIterator;
import com.bgsoftware.common.collections.longs.Object2LongMap;

import java.util.AbstractSet;
import java.util.Iterator;
import java.util.Map;
import java.util.OptionalLong;
import java.util.Set;
import java.util.function.ToLongFunction;

public class FastUtilsObject2LongMap<K> implements Object2LongMap<K> {

    private final it.unimi.dsi.fastutil.objects.Object2LongMap<K> handle;

    private EntrySet entrySet;
    private LongCollection values;

    public FastUtilsObject2LongMap(it.unimi.dsi.fastutil.objects.Object2LongMap<K> handle) {
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
        return this.values == null ? (this.values = new FastUtilsLongCollection(this.handle.values())) : this.values;
    }

    @Override
    public LongIterator valuesIterator() {
        return new FastUtilsLongIterator(this.handle.values().iterator());
    }

    @Override
    public OptionalLong put(K key, long value) {
        boolean containsKey = containsKey(key);
        long oldValue = this.handle.put(key, value);
        return containsKey ? OptionalLong.of(oldValue) : OptionalLong.empty();
    }

    @Override
    public OptionalLong get(K key) {
        return containsKey(key) ? OptionalLong.of(this.handle.getLong(key)) : OptionalLong.empty();
    }

    @Override
    public OptionalLong remove(K key) {
        return containsKey(key) ? OptionalLong.of(this.handle.removeLong(key)) : OptionalLong.empty();
    }

    @Override
    public Map<K, Long> handle() {
        return this.handle;
    }

    @Override
    public boolean containsKey(K key) {
        return this.handle.containsKey(key);
    }

    @Override
    public long getOrDefault(K key, long def) {
        return this.handle.getOrDefault(key, def);
    }

    @Override
    public long computeIfAbsent(K key, ToLongFunction<K> mappingFunction) {
        return this.handle.computeIfAbsent(key, mappingFunction);
    }

    @Override
    public String toString() {
        return this.handle.toString();
    }

    private class EntrySet extends AbstractSet<Entry<K>> {

        @Override
        public int size() {
            return FastUtilsObject2LongMap.this.size();
        }

        @Override
        public boolean isEmpty() {
            return FastUtilsObject2LongMap.this.isEmpty();
        }

        @Override
        public boolean contains(Object o) {
            return o instanceof Entry && FastUtilsObject2LongMap.this.containsKey(((Entry<K>) o).getKey());
        }

        @NotNull
        @Override
        public Iterator<Entry<K>> iterator() {
            return new EntriesItr();
        }

        @Override
        public void clear() {
            FastUtilsObject2LongMap.this.clear();
        }

    }

    private class EntriesItr implements Iterator<Entry<K>> {

        private final Iterator<it.unimi.dsi.fastutil.objects.Object2LongMap.Entry<K>> handle =
                FastUtilsObject2LongMap.this.handle.object2LongEntrySet().iterator();

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

        private final it.unimi.dsi.fastutil.objects.Object2LongMap.Entry<K> handle;

        public EntryImpl(it.unimi.dsi.fastutil.objects.Object2LongMap.Entry<K> handle) {
            this.handle = handle;
        }

        @Override
        public K getKey() {
            return this.handle.getKey();
        }

        @Override
        public long getValue() {
            return this.handle.getLongValue();
        }

        @Override
        public long setValue(long newValue) {
            return this.handle.setValue(newValue);
        }
    }

}
