package com.bgsoftware.common.collections.internal.immutable;

import java.util.Collection;
import java.util.Map;
import java.util.Set;
import java.util.function.BiConsumer;
import java.util.function.BiFunction;
import java.util.function.Function;

public class UnmodifiableMap<K, V> implements Map<K, V> {

    protected final Map<K, V> handle;

    private Set<K> keySet;
    private Collection<V> values;
    private Set<Entry<K, V>> entrySet;

    public static <K, V> UnmodifiableMap<K, V> create(Map<K, V> handle) {
        return handle instanceof UnmodifiableMap ?
                (UnmodifiableMap<K, V>) handle :
                new UnmodifiableMap<>(handle);
    }

    private UnmodifiableMap(Map<K, V> handle) {
        this.handle = handle;
    }

    @Override
    public int size() {
        return this.handle.size();
    }

    @Override
    public boolean isEmpty() {
        return this.handle.isEmpty();
    }

    @Override
    public boolean containsKey(Object key) {
        return this.handle.containsKey(key);
    }

    @Override
    public boolean containsValue(Object value) {
        return this.handle.containsValue(value);
    }

    @Override
    public V get(Object key) {
        return this.handle.get(key);
    }

    @Override
    public V put(K key, V value) {
        throw new UnsupportedOperationException();
    }

    @Override
    public V remove(Object key) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void putAll(Map<? extends K, ? extends V> m) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void clear() {
        throw new UnsupportedOperationException();
    }

    @Override
    public Set<K> keySet() {
        return this.keySet == null ? (this.keySet = UnmodifiableSet.create(this.handle.keySet())) : this.keySet;
    }

    @Override
    public Collection<V> values() {
        return this.values == null ? (this.values = UnmodifiableCollection.create(this.handle.values())) : this.values;
    }

    @Override
    public Set<Entry<K, V>> entrySet() {
        return this.entrySet == null ? (this.entrySet = UnmodifiableSet.create(this.handle.entrySet())) : this.entrySet;
    }

    @Override
    public V getOrDefault(Object key, V defaultValue) {
        return this.handle.getOrDefault(key, defaultValue);
    }

    @Override
    public void forEach(BiConsumer<? super K, ? super V> action) {
        this.handle.forEach(action);
    }

    @Override
    public void replaceAll(BiFunction<? super K, ? super V, ? extends V> function) {
        throw new UnsupportedOperationException();
    }

    @Override
    public V putIfAbsent(K key, V value) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean remove(Object key, Object value) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean replace(K key, V oldValue, V newValue) {
        throw new UnsupportedOperationException();
    }

    @Override
    public V replace(K key, V value) {
        throw new UnsupportedOperationException();
    }

    @Override
    public V computeIfAbsent(K key, Function<? super K, ? extends V> mappingFunction) {
        throw new UnsupportedOperationException();
    }

    @Override
    public V computeIfPresent(K key, BiFunction<? super K, ? super V, ? extends V> remappingFunction) {
        throw new UnsupportedOperationException();
    }

    @Override
    public V compute(K key, BiFunction<? super K, ? super V, ? extends V> remappingFunction) {
        throw new UnsupportedOperationException();
    }

    @Override
    public V merge(K key, V value, BiFunction<? super V, ? super V, ? extends V> remappingFunction) {
        throw new UnsupportedOperationException();
    }
}
