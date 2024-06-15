package com.bgsoftware.common.collections.ints.immutable;

import com.bgsoftware.common.collections.internal.immutable.AbstractUnmodifiableMap;
import com.bgsoftware.common.collections.internal.immutable.UnmodifiableIterator;
import com.bgsoftware.common.collections.internal.immutable.UnmodifiableMap;
import com.bgsoftware.common.collections.internal.immutable.UnmodifiableSet;
import com.bgsoftware.common.collections.ints.IntCollection;
import com.bgsoftware.common.collections.ints.IntIterator;
import com.bgsoftware.common.collections.ints.Object2IntMap;

import java.util.Iterator;
import java.util.Map;
import java.util.OptionalInt;
import java.util.Set;
import java.util.function.ToIntFunction;

public class UnmodifiableObject2IntMap<K> extends AbstractUnmodifiableMap<Object2IntMap<K>,
        Map<K, Integer>, Set<Object2IntMap.Entry<K>>, Set<K>, IntCollection> implements Object2IntMap<K> {

    public static <K> UnmodifiableObject2IntMap<K> create(Object2IntMap<K> handle) {
        return handle instanceof UnmodifiableObject2IntMap ?
                (UnmodifiableObject2IntMap<K>) handle :
                new UnmodifiableObject2IntMap<>(handle);
    }

    private UnmodifiableObject2IntMap(Object2IntMap<K> handle) {
        super(handle);
    }

    @Override
    protected Set<Entry<K>> createEntrySet() {
        return UnmodifiableSet.create(this.handle.entrySet());
    }

    @Override
    protected Set<K> createKeySet() {
        return UnmodifiableSet.create(this.handle.keySet());
    }

    @Override
    protected IntCollection createValues() {
        return UnmodifiableIntCollection.create(this.handle.values());
    }

    @Override
    protected Map<K, Integer> createUnmodifiableHandle() {
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
    public Iterator<Entry<K>> entriesIterator() {
        return UnmodifiableIterator.create(this.handle.entriesIterator());
    }

    @Override
    public Iterator<K> keysIterator() {
        return UnmodifiableIterator.create(this.handle.keysIterator());
    }

    @Override
    public IntIterator valuesIterator() {
        return UnmodifiableIntIterator.create(this.handle.valuesIterator());
    }

    @Override
    public OptionalInt put(K key, int value) {
        throw new UnsupportedOperationException();
    }

    @Override
    public OptionalInt get(K key) {
        return this.handle.get(key);
    }

    @Override
    public OptionalInt remove(K key) {
        throw new UnsupportedOperationException();
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
        throw new UnsupportedOperationException();
    }

}
