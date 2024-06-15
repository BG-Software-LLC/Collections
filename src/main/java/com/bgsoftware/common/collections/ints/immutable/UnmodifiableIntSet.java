package com.bgsoftware.common.collections.ints.immutable;

import com.bgsoftware.common.collections.internal.immutable.UnmodifiableSet;
import com.bgsoftware.common.collections.ints.IntSet;

import java.util.Set;

public class UnmodifiableIntSet extends UnmodifiableIntCollection implements IntSet {

    public static UnmodifiableIntSet create(IntSet handle) {
        return handle instanceof UnmodifiableIntSet ?
                (UnmodifiableIntSet) handle :
                new UnmodifiableIntSet(handle);
    }

    private UnmodifiableIntSet(IntSet handle) {
        super(handle);
    }

    @Override
    public Set<Integer> handle() {
        return (Set<Integer>) (this.unmodifiableHandle == null ?
                (this.unmodifiableHandle = UnmodifiableSet.create(((IntSet) this.handle).handle())) :
                this.unmodifiableHandle);
    }


}
