package com.bgsoftware.common.collections.internal.immutable;

import java.util.Set;

public class UnmodifiableSet<E> extends UnmodifiableCollection<E> implements Set<E> {

    public static <E> UnmodifiableSet<E> create(Set<E> handle) {
        return handle instanceof UnmodifiableSet ?
                (UnmodifiableSet<E>) handle :
                new UnmodifiableSet<>(handle);
    }

    private UnmodifiableSet(Set<E> handle) {
        super(handle);
    }


}
