package com.bgsoftware.common.collections.longs.immutable;

import com.bgsoftware.common.collections.internal.immutable.UnmodifiableSet;
import com.bgsoftware.common.collections.longs.LongSet;

import java.util.Set;

public class UnmodifiableLongSet extends UnmodifiableLongCollection implements LongSet {

    public static UnmodifiableLongSet create(LongSet handle) {
        return handle instanceof UnmodifiableLongSet ?
                (UnmodifiableLongSet) handle :
                new UnmodifiableLongSet(handle);
    }

    private UnmodifiableLongSet(LongSet handle) {
        super(handle);
    }

    @Override
    public Set<Long> handle() {
        return (Set<Long>) (this.unmodifiableHandle == null ?
                (this.unmodifiableHandle = UnmodifiableSet.create(((LongSet) this.handle).handle())) :
                this.unmodifiableHandle);
    }


}
