package com.bgsoftware.common.collections.ints.transform;

import com.bgsoftware.common.collections.internal.transform.TransformedIterator;
import com.bgsoftware.common.collections.internal.transform.TransformedMap;
import com.bgsoftware.common.collections.internal.transform.TransformedSet;
import com.bgsoftware.common.collections.ints.IntCollection;
import com.bgsoftware.common.collections.ints.IntIterator;
import com.bgsoftware.common.collections.ints.Object2IntMap;
import com.bgsoftware.common.collections.transform.Transformer;

import java.util.Iterator;
import java.util.Map;
import java.util.OptionalInt;
import java.util.Set;
import java.util.function.ToIntFunction;

public class TransformedObject2IntMap<K1, K2> implements Object2IntMap<K2> {

    private final Object2IntMap<K1> handle;
    private final Transformer<K1, K2> transformer;


    private Set<K2> keySet;
    private EntrySetTransformer entrySetTransformer;
    private Set<Entry<K2>> entrySet;

    public TransformedObject2IntMap(Object2IntMap<K1> handle, Transformer<K1, K2> transformer) {
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
    public Set<Entry<K2>> entrySet() {
        return this.entrySet != null ? this.entrySet :
                (this.entrySet = TransformedSet.create(this.handle.entrySet(), getEntrySetTransformer()));
    }

    @Override
    public Iterator<Entry<K2>> entriesIterator() {
        return new TransformedIterator<>(this.handle.entriesIterator(), getEntrySetTransformer());
    }

    private EntrySetTransformer getEntrySetTransformer() {
        return this.entrySetTransformer != null ? this.entrySetTransformer :
                (this.entrySetTransformer = new EntrySetTransformer());
    }

    @Override
    public Set<K2> keySet() {
        return this.keySet != null ? this.keySet :
                (this.keySet = TransformedSet.create(this.handle.keySet(), this.transformer));
    }

    @Override
    public Iterator<K2> keysIterator() {
        return new TransformedIterator<>(this.handle.keysIterator(), this.transformer);
    }

    @Override
    public IntCollection values() {
        return this.handle.values();
    }

    @Override
    public IntIterator valuesIterator() {
        return this.handle.valuesIterator();
    }

    @Override
    public OptionalInt put(K2 key, int value) {
        return this.handle.put(this.transformer.transformB(key), value);
    }

    @Override
    public OptionalInt get(K2 key) {
        return this.handle.get(this.transformer.transformB(key));
    }

    @Override
    public OptionalInt remove(K2 key) {
        return this.handle.remove(this.transformer.transformB(key));
    }

    @Override
    public Map<K2, Integer> handle() {
        return new TransformedMap<>(this.handle.handle(), this.transformer, null);
    }

    @Override
    public boolean isEmpty() {
        return this.handle.isEmpty();
    }

    @Override
    public boolean containsKey(K2 key) {
        return this.handle.containsKey(this.transformer.transformB(key));
    }

    @Override
    public int getOrDefault(K2 key, int def) {
        return this.handle.getOrDefault(this.transformer.transformB(key), def);
    }

    @Override
    public int computeIfAbsent(K2 key, ToIntFunction<K2> mappingFunction) {
        return this.handle.computeIfAbsent(this.transformer.transformB(key),
                value -> mappingFunction.applyAsInt(transformer.transformA(value)));
    }

    @Override
    public String toString() {
        Iterator<Entry<K2>> i = entrySet().iterator();
        if (! i.hasNext())
            return "{}";

        StringBuilder sb = new StringBuilder();
        sb.append('{');
        for (;;) {
            Entry<K2> e = i.next();
            K2 key = e.getKey();
            int value = e.getValue();
            sb.append(key   == this ? "(this Map)" : key);
            sb.append('=');
            sb.append(value);
            if (! i.hasNext())
                return sb.append('}').toString();
            sb.append(',').append(' ');
        }
    }

    private class EntrySetTransformer extends Transformer<Entry<K1>, Entry<K2>> {

        @Override
        public Entry<K2> transformA(Entry<K1> value) {
            return new TransformedEntry(value);
        }

        @Override
        public Entry<K1> transformB(Entry<K2> value) {
            return new OppositeTransformedEntry(value);
        }

        private class TransformedEntry implements Entry<K2> {

            private final Entry<K1> handle;

            TransformedEntry(Entry<K1> handle) {
                this.handle = handle;
            }

            @Override
            public K2 getKey() {
                return transformer.transformA(this.handle.getKey());
            }

            @Override
            public int getValue() {
                return handle.getValue();
            }

            @Override
            public int setValue(int value) {
                return this.handle.setValue(value);
            }
        }


        private class OppositeTransformedEntry implements Entry<K1> {

            private final Entry<K2> handle;

            OppositeTransformedEntry(Entry<K2> handle) {
                this.handle = handle;
            }

            @Override
            public K1 getKey() {
                return transformer.transformB(this.handle.getKey());
            }

            @Override
            public int getValue() {
                return this.handle.getValue();
            }

            @Override
            public int setValue(int value) {
                return handle.setValue(value);
            }
        }

    }

}
