package com.bgsoftware.common.collections.ints.immutable;

import com.bgsoftware.common.collections.internal.immutable.AbstractUnmodifiableMap;
import com.bgsoftware.common.collections.internal.immutable.UnmodifiableIterator;
import com.bgsoftware.common.collections.internal.immutable.UnmodifiableMap;
import com.bgsoftware.common.collections.internal.immutable.UnmodifiableSet;
import com.bgsoftware.common.collections.ints.Int2IntFunction;
import com.bgsoftware.common.collections.ints.Int2IntMap;
import com.bgsoftware.common.collections.ints.IntCollection;
import com.bgsoftware.common.collections.ints.IntIterator;
import com.bgsoftware.common.collections.ints.IntSet;

import java.util.Iterator;
import java.util.Map;
import java.util.OptionalInt;
import java.util.Set;

public class UnmodifiableInt2IntMap extends AbstractUnmodifiableMap<Int2IntMap,
        Map<Integer, Integer>, Set<Int2IntMap.Entry>, IntSet, IntCollection> implements Int2IntMap {

    public static UnmodifiableInt2IntMap create(Int2IntMap handle) {
        return handle instanceof UnmodifiableInt2IntMap ?
                (UnmodifiableInt2IntMap) handle :
                new UnmodifiableInt2IntMap(handle);
    }

    private UnmodifiableInt2IntMap(Int2IntMap handle) {
        super(handle);
    }

    @Override
    protected Set<Entry> createEntrySet() {
        return UnmodifiableSet.create(this.handle.entrySet());
    }

    @Override
    protected IntSet createKeySet() {
        return UnmodifiableIntSet.create(this.handle.keySet());
    }

    @Override
    protected IntCollection createValues() {
        return UnmodifiableIntCollection.create(this.handle.values());
    }

    @Override
    protected Map<Integer, Integer> createUnmodifiableHandle() {
        return UnmodifiableMap.create(this.handle.handle());
    }

    @Override
    public int size() {
        return this.handle.size();
    }

    @Override
    public void clear() {
        throw new UnsupportedOperationException();
    }

    @Override
    public Iterator<Entry> entriesIterator() {
        return UnmodifiableIterator.create(this.handle.entriesIterator());
    }

    @Override
    public IntIterator keysIterator() {
        return UnmodifiableIntIterator.create(this.handle.keysIterator());
    }

    @Override
    public IntIterator valuesIterator() {
        return UnmodifiableIntIterator.create(this.handle.valuesIterator());
    }

    @Override
    public OptionalInt put(int key, int value) {
        throw new UnsupportedOperationException();
    }

    @Override
    public OptionalInt get(int key) {
        return this.handle.get(key);
    }

    @Override
    public OptionalInt remove(int key) {
        throw new UnsupportedOperationException();
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
    public int getOrDefault(int key, int def) {
        return this.handle.getOrDefault(key, def);
    }

    @Override
    public int computeIfAbsent(int key, Int2IntFunction mappingFunction) {
        throw new UnsupportedOperationException();
    }

}
