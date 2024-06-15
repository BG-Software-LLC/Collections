package com.bgsoftware.common.collections.ints.transform;

import com.bgsoftware.common.collections.internal.transform.TransformedCollection;
import com.bgsoftware.common.collections.internal.transform.TransformedIterator;
import com.bgsoftware.common.collections.internal.transform.TransformedMap;
import com.bgsoftware.common.collections.internal.transform.TransformedSet;
import com.bgsoftware.common.collections.ints.Int2ObjectMap;
import com.bgsoftware.common.collections.ints.IntIterator;
import com.bgsoftware.common.collections.ints.IntSet;
import com.bgsoftware.common.collections.transform.Transformer;

import java.util.Collection;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.function.IntFunction;

public class TransformedInt2ObjectMap<V1, V2> implements Int2ObjectMap<V2> {

    private final Int2ObjectMap<V1> handle;
    private final Transformer<V1, V2> transformer;


    private Collection<V2> values;
    private EntrySetTransformer entrySetTransformer;
    private Set<Entry<V2>> entrySet;

    public TransformedInt2ObjectMap(Int2ObjectMap<V1> handle, Transformer<V1, V2> transformer) {
        this.handle = handle;
        this.transformer = transformer;
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
    public Set<Entry<V2>> entrySet() {
        return this.entrySet != null ? this.entrySet :
                (this.entrySet = TransformedSet.create(this.handle.entrySet(), getEntrySetTransformer()));
    }

    @Override
    public Iterator<Entry<V2>> entriesIterator() {
        return new TransformedIterator<>(this.handle.entriesIterator(), getEntrySetTransformer());
    }

    private EntrySetTransformer getEntrySetTransformer() {
        return this.entrySetTransformer != null ? this.entrySetTransformer :
                (this.entrySetTransformer = new EntrySetTransformer());
    }

    @Override
    public IntSet keySet() {
        return this.handle.keySet();
    }

    @Override
    public IntIterator keysIterator() {
        return this.handle.keysIterator();
    }

    @Override
    public Collection<V2> values() {
        return this.values != null ? this.values :
                (this.values = TransformedCollection.create(this.handle.values(), this.transformer));
    }

    @Override
    public Iterator<V2> valuesIterator() {
        return new TransformedIterator<>(this.handle.valuesIterator(), this.transformer);
    }

    @Override
    public V2 put(int key, V2 value) {
        return this.transformer.transformA(this.handle.put(key, this.transformer.transformB(value)));
    }

    @Override
    public V2 get(int key) {
        return this.transformer.transformA(this.handle.get(key));
    }

    @Override
    public V2 remove(int key) {
        return this.transformer.transformA(this.handle.remove(key));
    }

    @Override
    public Map<Integer, V2> handle() {
        return new TransformedMap<>(this.handle.handle(), null, this.transformer);
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
    public V2 getOrDefault(int key, V2 def) {
        return this.transformer.transformA(this.handle.getOrDefault(key, this.transformer.transformB(def)));
    }

    @Override
    public V2 computeIfAbsent(int key, IntFunction<V2> mappingFunction) {
        return this.transformer.transformA(this.handle.computeIfAbsent(key,
                value -> transformer.transformB(mappingFunction.apply(value))));
    }

    @Override
    public String toString() {
        Iterator<Entry<V2>> i = entrySet().iterator();
        if (! i.hasNext())
            return "{}";

        StringBuilder sb = new StringBuilder();
        sb.append('{');
        for (;;) {
            Entry<V2> e = i.next();
            int key = e.getKey();
            V2 value = e.getValue();
            sb.append(key);
            sb.append('=');
            sb.append(value == this ? "(this Map)" : value);
            if (! i.hasNext())
                return sb.append('}').toString();
            sb.append(',').append(' ');
        }
    }

    private class EntrySetTransformer extends Transformer<Entry<V1>, Entry<V2>> {

        @Override
        public Entry<V2> transformA(Entry<V1> value) {
            return new TransformedEntry(value);
        }

        @Override
        public Entry<V1> transformB(Entry<V2> value) {
            return new OppositeTransformedEntry(value);
        }

        private class TransformedEntry implements Entry<V2> {

            private final Entry<V1> handle;

            TransformedEntry(Entry<V1> handle) {
                this.handle = handle;
            }

            @Override
            public int getKey() {
                return this.handle.getKey();
            }

            @Override
            public V2 getValue() {
                return transformer.transformA(handle.getValue());
            }

            @Override
            public V2 setValue(V2 value) {
                return transformer.transformA(handle.setValue(transformer.transformB(value)));
            }
        }


        private class OppositeTransformedEntry implements Entry<V1> {

            private final Entry<V2> handle;

            OppositeTransformedEntry(Entry<V2> handle) {
                this.handle = handle;
            }

            @Override
            public int getKey() {
                return this.handle.getKey();
            }

            @Override
            public V1 getValue() {
                return transformer.transformB(this.handle.getValue());
            }

            @Override
            public V1 setValue(V1 value) {
                return transformer.transformB(handle.setValue(transformer.transformA(value)));
            }
        }

    }

}
