package com.bgsoftware.common.collections.internal.transform;

import com.bgsoftware.common.collections.transform.Transformer;

import java.util.AbstractCollection;
import java.util.Collection;
import java.util.Iterator;
import java.util.RandomAccess;
import java.util.Spliterator;
import java.util.function.Consumer;
import java.util.function.Predicate;

public class TransformedCollection<A, B> extends AbstractCollection<B> implements Collection<B> {

    protected final Collection<A> handle;
    protected final Transformer<A, B> transformer;

    public static <A, B> TransformedCollection<A, B> create(Collection<A> handle, Transformer<A, B> transformer) {
        return handle instanceof RandomAccess ? new RandomAccessTransformedCollection<>(handle, transformer) :
                new TransformedCollection<>(handle, transformer);
    }

    private static class RandomAccessTransformedCollection<A, B> extends TransformedCollection<A, B> implements RandomAccess {

        private RandomAccessTransformedCollection(Collection<A> handle, Transformer<A, B> transformer) {
            super(handle, transformer);
        }

    }

    protected TransformedCollection(Collection<A> handle, Transformer<A, B> transformer) {
        this.handle = handle;
        this.transformer = transformer;
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
        return this.handle.contains(this.transformer.transformB((B) o));
    }

    @Override
    public Iterator<B> iterator() {
        return new TransformedIterator<>(this.handle.iterator(), this.transformer);
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
    public boolean add(B b) {
        return this.handle.add(this.transformer.transformB(b));
    }

    @Override
    public boolean remove(Object o) {
        return this.handle.remove(this.transformer.transformB((B) o));
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        if (c.isEmpty()) return true;
        if (c.size() == 1) return this.contains(c.iterator().next());
        return this.handle.containsAll(new TransformedCollection<>((Collection<B>) c, this.transformer.opposite()));
    }

    @Override
    public boolean addAll(Collection<? extends B> c) {
        if (c.isEmpty()) return false;
        if (c.size() == 1) return this.add(c.iterator().next());
        return this.handle.addAll(new TransformedCollection<>((Collection<B>) c, this.transformer.opposite()));
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        if (c.isEmpty()) return true;
        if (c.size() == 1) return this.remove(c.iterator().next());
        return this.handle.removeAll(new TransformedCollection<>((Collection<B>) c, this.transformer.opposite()));
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        if (c.isEmpty()) return true;
        return this.handle.retainAll(new TransformedCollection<>((Collection<B>) c, this.transformer.opposite()));
    }

    @Override
    public void clear() {
        this.handle.clear();
    }

    @Override
    public boolean removeIf(Predicate<? super B> filter) {
        return this.handle.removeIf(elem -> filter.test(this.transformer.transformA(elem)));
    }

    @Override
    public Spliterator<B> spliterator() {
        return new TransformedSpliterator<>(this.handle.spliterator(), this.transformer);
    }

    @Override
    public void forEach(Consumer<? super B> action) {
        this.handle.forEach(elem -> action.accept(this.transformer.transformA(elem)));
    }


}
