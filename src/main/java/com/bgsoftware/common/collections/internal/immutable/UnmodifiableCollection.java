package com.bgsoftware.common.collections.internal.immutable;

import java.util.Collection;
import java.util.Iterator;
import java.util.Spliterator;
import java.util.function.Consumer;
import java.util.function.Predicate;
import java.util.stream.Stream;

public class UnmodifiableCollection<E> implements Collection<E> {

    protected final Collection<E> handle;

    public static <E> UnmodifiableCollection<E> create(Collection<E> handle) {
        return handle instanceof UnmodifiableCollection ?
                (UnmodifiableCollection<E>) handle :
                new UnmodifiableCollection<>(handle);
    }

    protected UnmodifiableCollection(Collection<E> handle) {
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
    public boolean contains(Object o) {
        return this.handle.contains(o);
    }

    @Override
    public Iterator<E> iterator() {
        return UnmodifiableIterator.create(this.handle.iterator());
    }

    @Override
    public Object[] toArray() {
        return this.handle.toArray();
    }

    @Override
    public <T> T[] toArray(T[] a) {
        return this.handle.toArray(a);
    }

    @Override
    public boolean add(E e) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean remove(Object o) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        return this.handle.containsAll(c);
    }

    @Override
    public boolean addAll(Collection<? extends E> c) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void clear() {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean removeIf(Predicate<? super E> filter) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Spliterator<E> spliterator() {
        return this.handle.spliterator();
    }

    @Override
    public Stream<E> stream() {
        return this.handle.stream();
    }

    @Override
    public Stream<E> parallelStream() {
        return this.handle.parallelStream();
    }

    @Override
    public void forEach(Consumer<? super E> action) {
        this.handle.forEach(action);
    }

}
