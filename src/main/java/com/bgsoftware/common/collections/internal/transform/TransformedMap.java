package com.bgsoftware.common.collections.internal.transform;

import com.bgsoftware.common.annotations.Nullable;
import com.bgsoftware.common.collections.transform.Transformer;

import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.function.BiConsumer;
import java.util.function.BiFunction;
import java.util.function.Function;

public class TransformedMap<K1, V1, K2, V2> implements Map<K2, V2> {

    private final Map<K1, V1> handle;
    private final Transformer<K1, K2> keysTransformer;
    private final Transformer<V1, V2> valuesTransformer;

    private Set<K2> keySet;
    private Collection<V2> values;
    private Set<Entry<K2, V2>> entrySet;

    public TransformedMap(Map<K1, V1> handle, @Nullable Transformer<K1, K2> keysTransformer,
                          @Nullable Transformer<V1, V2> valuesTransformer) {
        if (keysTransformer == null && valuesTransformer == null)
            throw new IllegalArgumentException("Cannot have both keysTransformer and valuesTransformer as null");
        this.handle = handle;
        this.keysTransformer = keysTransformer == null ? IdenticalTransformer.INSTANCE : keysTransformer;
        this.valuesTransformer = valuesTransformer == null ? IdenticalTransformer.INSTANCE : valuesTransformer;
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
        return this.handle.containsKey(this.keysTransformer.transformB((K2) key));
    }

    @Override
    public boolean containsValue(Object value) {
        return this.handle.containsValue(this.valuesTransformer.transformB((V2) value));
    }

    @Override
    public V2 get(Object key) {
        return this.valuesTransformer.transformA(this.handle.get(this.keysTransformer.transformB((K2) key)));
    }

    @Override
    public V2 put(K2 key, V2 value) {
        return this.valuesTransformer.transformA(
                this.handle.put(this.keysTransformer.transformB(key), this.valuesTransformer.transformB(value)));
    }

    @Override
    public V2 remove(Object key) {
        return this.valuesTransformer.transformA(this.handle.remove(this.keysTransformer.transformB((K2) key)));
    }

    @Override
    public void putAll(Map<? extends K2, ? extends V2> m) {
        this.handle.putAll(new TransformedMap<>((Map<K2, V2>) m, this.keysTransformer.opposite(), this.valuesTransformer.opposite()));
    }

    @Override
    public void clear() {
        this.handle.clear();
    }

    @Override
    public Set<K2> keySet() {
        return this.keySet != null ? this.keySet :
                (this.keySet = TransformedSet.create(this.handle.keySet(), this.keysTransformer));
    }

    @Override
    public Collection<V2> values() {
        return this.values != null ? this.values :
                (this.values = new TransformedCollection<>(this.handle.values(), this.valuesTransformer));
    }

    @Override
    public Set<Entry<K2, V2>> entrySet() {
        return this.entrySet != null ? this.entrySet :
                (this.entrySet = TransformedSet.create(this.handle.entrySet(), new EntrySetTransformer()));
    }

    @Override
    public V2 getOrDefault(Object key, V2 defaultValue) {
        return this.valuesTransformer.transformA(this.handle.getOrDefault(key, this.valuesTransformer.transformB(defaultValue)));
    }

    @Override
    public void forEach(BiConsumer<? super K2, ? super V2> action) {
        this.handle.forEach((k, v) ->
                action.accept(this.keysTransformer.transformA(k), this.valuesTransformer.transformA(v)));
    }

    @Override
    public void replaceAll(BiFunction<? super K2, ? super V2, ? extends V2> function) {
        this.handle.replaceAll((k1, v1) -> valuesTransformer.transformB(
                function.apply(keysTransformer.transformA(k1), valuesTransformer.transformA(v1))));
    }

    @Override
    public V2 putIfAbsent(K2 key, V2 value) {
        return this.valuesTransformer.transformA(this.handle.putIfAbsent(
                this.keysTransformer.transformB(key), this.valuesTransformer.transformB(value)));
    }

    @Override
    public boolean remove(Object key, Object value) {
        return this.handle.remove(this.keysTransformer.transformB((K2) key),
                this.valuesTransformer.transformB((V2) value));
    }

    @Override
    public boolean replace(K2 key, V2 oldValue, V2 newValue) {
        return this.handle.replace(this.keysTransformer.transformB(key),
                this.valuesTransformer.transformB(oldValue),
                this.valuesTransformer.transformB(newValue));
    }

    @Override
    public V2 replace(K2 key, V2 value) {
        return this.valuesTransformer.transformA(this.handle.replace(
                this.keysTransformer.transformB(key), this.valuesTransformer.transformB(value)));
    }

    @Override
    public V2 computeIfAbsent(K2 key, Function<? super K2, ? extends V2> mappingFunction) {
        return this.valuesTransformer.transformA(this.handle.computeIfAbsent(
                this.keysTransformer.transformB(key),
                k1 -> valuesTransformer.transformB(mappingFunction.apply(keysTransformer.transformA(k1)))
        ));
    }

    @Override
    public V2 computeIfPresent(K2 key, BiFunction<? super K2, ? super V2, ? extends V2> remappingFunction) {
        return this.valuesTransformer.transformA(this.handle.computeIfPresent(
                this.keysTransformer.transformB(key),
                (k1, v1) -> valuesTransformer.transformB(
                        remappingFunction.apply(keysTransformer.transformA(k1), valuesTransformer.transformA(v1)))
        ));
    }

    @Override
    public V2 compute(K2 key, BiFunction<? super K2, ? super V2, ? extends V2> remappingFunction) {
        return this.valuesTransformer.transformA(this.handle.compute(this.keysTransformer.transformB(key),
                (k1, v1) -> valuesTransformer.transformB(
                        remappingFunction.apply(keysTransformer.transformA(k1), valuesTransformer.transformA(v1)))));
    }

    @Override
    public V2 merge(K2 key, V2 value, BiFunction<? super V2, ? super V2, ? extends V2> remappingFunction) {
        return this.valuesTransformer.transformA(this.handle.merge(
                this.keysTransformer.transformB(key),
                this.valuesTransformer.transformB(value),
                (v1, v12) -> valuesTransformer.transformB(
                        remappingFunction.apply(valuesTransformer.transformA(v1), valuesTransformer.transformA(v12)))));
    }

    @Override
    public String toString() {
        Iterator<Entry<K2,V2>> i = entrySet().iterator();
        if (! i.hasNext())
            return "{}";

        StringBuilder sb = new StringBuilder();
        sb.append('{');
        for (;;) {
            Entry<K2,V2> e = i.next();
            K2 key = e.getKey();
            V2 value = e.getValue();
            sb.append(key   == this ? "(this Map)" : key);
            sb.append('=');
            sb.append(value == this ? "(this Map)" : value);
            if (! i.hasNext())
                return sb.append('}').toString();
            sb.append(',').append(' ');
        }
    }

    private class EntrySetTransformer extends Transformer<Map.Entry<K1, V1>, Map.Entry<K2, V2>> {

        @Override
        public Entry<K2, V2> transformA(Entry<K1, V1> value) {
            return new TransformedEntry(value);
        }

        @Override
        public Entry<K1, V1> transformB(Entry<K2, V2> value) {
            return new OppositeTransformedEntry(value);
        }

        private class TransformedEntry implements Map.Entry<K2, V2> {

            private final Entry<K1, V1> handle;

            TransformedEntry(Entry<K1, V1> handle) {
                this.handle = handle;
            }

            @Override
            public K2 getKey() {
                return keysTransformer.transformA(handle.getKey());
            }

            @Override
            public V2 getValue() {
                return valuesTransformer.transformA(handle.getValue());
            }

            @Override
            public V2 setValue(V2 value) {
                return valuesTransformer.transformA(handle.setValue(valuesTransformer.transformB(value)));
            }
        }


        private class OppositeTransformedEntry implements Map.Entry<K1, V1> {

            private final Entry<K2, V2> handle;

            OppositeTransformedEntry(Entry<K2, V2> handle) {
                this.handle = handle;
            }

            @Override
            public K1 getKey() {
                return keysTransformer.transformB(this.handle.getKey());
            }

            @Override
            public V1 getValue() {
                return valuesTransformer.transformB(this.handle.getValue());
            }

            @Override
            public V1 setValue(V1 value) {
                return valuesTransformer.transformB(handle.setValue(valuesTransformer.transformA(value)));
            }
        }

    }

}
