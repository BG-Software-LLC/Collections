package com.bgsoftware.common.collections.longs.fastutils;

import com.bgsoftware.common.annotations.NotNull;
import com.bgsoftware.common.collections.longs.Long2LongFunction;
import com.bgsoftware.common.collections.longs.Long2LongMap;
import com.bgsoftware.common.collections.longs.LongCollection;
import com.bgsoftware.common.collections.longs.LongIterator;
import com.bgsoftware.common.collections.longs.LongSet;

import java.util.AbstractSet;
import java.util.Iterator;
import java.util.Map;
import java.util.OptionalLong;
import java.util.Set;

public class FastUtilsLong2LongMap implements Long2LongMap {

    private final it.unimi.dsi.fastutil.longs.Long2LongMap handle;

    private EntrySet entrySet;
    private LongSet keySet;
    private LongCollection values;

    public FastUtilsLong2LongMap(it.unimi.dsi.fastutil.longs.Long2LongMap handle) {
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
        return this.keySet == null ? (this.keySet = new FastUtilsLongSet(this.handle.keySet())) : this.keySet;
    }

    @Override
    public LongIterator keysIterator() {
        return new FastUtilsLongIterator(this.handle.keySet().iterator());
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
    public OptionalLong put(long key, long value) {
        boolean containsKey = containsKey(key);
        long oldValue = this.handle.put(key, value);
        return containsKey ? OptionalLong.of(oldValue) : OptionalLong.empty();
    }

    @Override
    public OptionalLong get(long key) {
        return containsKey(key) ? OptionalLong.of(this.handle.get(key)) : OptionalLong.empty();
    }

    @Override
    public OptionalLong remove(long key) {
        return containsKey(key) ? OptionalLong.of(this.handle.remove(key)) : OptionalLong.empty();
    }

    @Override
    public Map<Long, Long> handle() {
        return this.handle;
    }

    @Override
    public boolean containsKey(long key) {
        return this.handle.containsKey(key);
    }

    @Override
    public long getOrDefault(long key, long def) {
        return this.handle.getOrDefault(key, def);
    }

    @Override
    public long computeIfAbsent(long key, Long2LongFunction mappingFunction) {
        return this.handle.computeIfAbsent(key, mappingFunction::apply);
    }

    @Override
    public String toString() {
        return this.handle.toString();
    }

    private class EntrySet extends AbstractSet<Entry> {

        @Override
        public int size() {
            return FastUtilsLong2LongMap.this.size();
        }

        @Override
        public boolean isEmpty() {
            return FastUtilsLong2LongMap.this.isEmpty();
        }

        @Override
        public boolean contains(Object o) {
            return o instanceof Entry && FastUtilsLong2LongMap.this.containsKey(((Entry) o).getKey());
        }

        @NotNull
        @Override
        public Iterator<Entry> iterator() {
            return new EntriesItr();
        }

        @Override
        public void clear() {
            FastUtilsLong2LongMap.this.clear();
        }

    }

    private class EntriesItr implements Iterator<Entry> {

        private final Iterator<it.unimi.dsi.fastutil.longs.Long2LongMap.Entry> handle =
                FastUtilsLong2LongMap.this.handle.long2LongEntrySet().iterator();

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

        private final it.unimi.dsi.fastutil.longs.Long2LongMap.Entry handle;

        public EntryImpl(it.unimi.dsi.fastutil.longs.Long2LongMap.Entry handle) {
            this.handle = handle;
        }

        @Override
        public long getKey() {
            return this.handle.getLongKey();
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
