package com.bgsoftware.common.collections.internal.immutable;

public abstract class AbstractUnmodifiableMap<M, H, ES, KS, VS> {

    protected final M handle;

    protected H unmodifiableHandle;

    private ES entrySet;
    private KS keySet;
    private VS values;

    protected AbstractUnmodifiableMap(M handle) {
        this.handle = handle;
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

    public H handle() {
        return this.unmodifiableHandle == null ?
                (this.unmodifiableHandle = createUnmodifiableHandle()) :
                this.unmodifiableHandle;
    }

    protected abstract H createUnmodifiableHandle();

    @Override
    public String toString() {
        return this.handle.toString();
    }

}
