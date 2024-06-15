package com.bgsoftware.common.collections.ints.immutable;

import com.bgsoftware.common.collections.internal.immutable.AbstractUnmodifiableMap;
import com.bgsoftware.common.collections.internal.immutable.UnmodifiableCollection;
import com.bgsoftware.common.collections.internal.immutable.UnmodifiableIterator;
import com.bgsoftware.common.collections.internal.immutable.UnmodifiableMap;
import com.bgsoftware.common.collections.internal.immutable.UnmodifiableSet;
import com.bgsoftware.common.collections.ints.Int2ObjectMap;
import com.bgsoftware.common.collections.ints.IntIterator;
import com.bgsoftware.common.collections.ints.IntSet;

import java.util.Collection;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.function.IntFunction;

public class UnmodifiableInt2ObjectMap<V> extends AbstractUnmodifiableMap<Int2ObjectMap<V>,
        Map<Integer, V>, Set<Int2ObjectMap.Entry<V>>, IntSet, Collection<V>> implements Int2ObjectMap<V> {

    public static <V> UnmodifiableInt2ObjectMap<V> create(Int2ObjectMap<V> handle) {
        return handle instanceof UnmodifiableInt2ObjectMap ?
                (UnmodifiableInt2ObjectMap<V>) handle :
                new UnmodifiableInt2ObjectMap<>(handle);
    }

    private UnmodifiableInt2ObjectMap(Int2ObjectMap<V> handle) {
        super(handle);
    }

    @Override
    protected Set<Entry<V>> createEntrySet() {
        return UnmodifiableSet.create(this.handle.entrySet());
    }

    @Override
    protected IntSet createKeySet() {
        return UnmodifiableIntSet.create(this.handle.keySet());
    }

    @Override
    protected Collection<V> createValues() {
        return UnmodifiableCollection.create(this.handle.values());
    }

    @Override
    protected Map<Integer, V> createUnmodifiableHandle() {
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
    public Iterator<Entry<V>> entriesIterator() {
        return UnmodifiableIterator.create(this.handle.entriesIterator());
    }

    @Override
    public IntIterator keysIterator() {
        return UnmodifiableIntIterator.create(this.handle.keysIterator());
    }

    @Override
    public Iterator<V> valuesIterator() {
        return UnmodifiableIterator.create(this.handle.valuesIterator());
    }

    @Override
    public V put(int key, V value) {
        throw new UnsupportedOperationException();
    }

    @Override
    public V get(int key) {
        return this.handle.get(key);
    }

    @Override
    public V remove(int key) {
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
    public V getOrDefault(int key, V def) {
        return this.handle.getOrDefault(key, def);
    }

    @Override
    public V computeIfAbsent(int key, IntFunction<V> mappingFunction) {
        throw new UnsupportedOperationException();
    }

}
