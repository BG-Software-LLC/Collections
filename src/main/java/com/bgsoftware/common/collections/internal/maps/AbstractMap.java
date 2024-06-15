package com.bgsoftware.common.collections.internal.maps;

import java.util.Map;

public abstract class AbstractMap<K, V, M extends Map<K, V>, ES, KS, VS> {

    protected final M handle;

    private ES entrySet;
    private KS keySet;
    private VS values;

    protected AbstractMap(M handle) {
        this.handle = handle;
    }

    public int size() {
        return this.handle.size();
    }

    public void clear() {
        this.handle.clear();
    }

    public ES entrySet() {
        return this.entrySet == null ? (this.entrySet = createEntrySet()) : this.entrySet;
    }

    protected abstract ES createEntrySet();

    public KS keySet() {
        return this.keySet == null ? (this.keySet = createKeySet()) : this.keySet;
    }

    protected abstract KS createKeySet();

    public VS values() {
        return this.values == null ? (this.values = createValues()) : this.values;
    }

    protected abstract VS createValues();

    public Map<K, V> handle() {
        return this.handle;
    }

    @Override
    public String toString() {
        return this.handle.toString();
    }

}
