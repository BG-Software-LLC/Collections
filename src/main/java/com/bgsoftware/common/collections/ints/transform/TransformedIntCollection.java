package com.bgsoftware.common.collections.ints.transform;

import com.bgsoftware.common.collections.internal.transform.TransformedCollection;
import com.bgsoftware.common.collections.internal.transform.TransformedSpliterator;
import com.bgsoftware.common.collections.ints.IntCollection;
import com.bgsoftware.common.collections.ints.IntIterator;
import com.bgsoftware.common.collections.transform.IntTransformer;
import com.bgsoftware.common.collections.transform.Transformer;

import java.util.Collection;
import java.util.Iterator;
import java.util.RandomAccess;
import java.util.Spliterator;
import java.util.function.Consumer;
import java.util.function.Predicate;

public class TransformedIntCollection<E> implements Collection<E> {

    protected final IntCollection handle;
    protected final IntTransformer<E> transformer;
    protected Transformer<Integer, E> boxedTransformer;

    public static <E> TransformedIntCollection<E> create(IntCollection handle, IntTransformer<E> transformer) {
        return handle instanceof RandomAccess ? new RandomAccessTransformedIntCollection<>(handle, transformer) :
                new TransformedIntCollection<>(handle, transformer);
    }

    private static class RandomAccessTransformedIntCollection<E> extends TransformedIntCollection<E> implements RandomAccess {

        private RandomAccessTransformedIntCollection(IntCollection handle, IntTransformer<E> transformer) {
            super(handle, transformer);
        }

    }

    protected TransformedIntCollection(IntCollection handle, IntTransformer<E> transformer) {
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
        return this.handle.contains(this.transformer.transform((E) o));
    }

    @Override
    public Iterator<E> iterator() {
        return new TransformedIntIterator<>(this.handle.iterator(), this.transformer);
    }

    @Override
    public Object[] toArray() {
        return this.handle.handle().toArray();
    }

    @Override
    public <T> T[] toArray(T[] a) {
        return this.handle.handle().toArray(a);
    }

    @Override
    public boolean add(E b) {
        return this.handle.add(this.transformer.transform(b));
    }

    @Override
    public boolean remove(Object o) {
        return this.handle.remove(this.transformer.transform((E) o));
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        if (c.isEmpty()) return true;
        if (c.size() == 1) return contains(c.iterator().next());

        IntIterator iterator = this.handle.iterator();
        while (iterator.hasNext()) {
            if (!c.contains(this.transformer.transformInt(iterator.next())))
                return false;
        }
        return true;
    }

    @Override
    public boolean addAll(Collection<? extends E> c) {
        if (c.isEmpty()) return false;
        c.forEach(this::add);
        return true;
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        if (c.isEmpty()) return false;
        c.forEach(this::remove);
        return true;
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        return this.handle.handle().retainAll(TransformedCollection.create((Collection<E>) c, getBoxedTransformer().opposite()));
    }

    @Override
    public void clear() {
        this.handle.clear();
    }

    @Override
    public boolean removeIf(Predicate<? super E> filter) {
        IntIterator iterator = this.handle.iterator();
        boolean modified = false;
        while (iterator.hasNext()) {
            if (filter.test(this.transformer.transformInt(iterator.next()))) {
                iterator.remove();
                modified = true;
            }
        }
        return modified;
    }

    @Override
    public Spliterator<E> spliterator() {
        return new TransformedSpliterator<>(this.handle.handle().spliterator(), getBoxedTransformer());
    }

    @Override
    public void forEach(Consumer<? super E> action) {
        this.handle.iterator().forEachRemaining(elem -> action.accept(this.transformer.transformInt(elem)));
    }

    protected Transformer<Integer, E> getBoxedTransformer() {
        if (boxedTransformer == null) {
            this.boxedTransformer = new Transformer<Integer, E>() {
                @Override
                public E transformA(Integer value) {
                    return transformer.transformInt(value);
                }

                @Override
                public Integer transformB(E value) {
                    return transformer.transform(value);
                }
            };
        }

        return this.boxedTransformer;
    }

    @Override
    public String toString() {
        Iterator<E> it = iterator();
        if (!it.hasNext())
            return "[]";

        StringBuilder sb = new StringBuilder();
        sb.append('[');
        for (; ; ) {
            E e = it.next();
            sb.append(e == this ? "(this Collection)" : e);
            if (!it.hasNext())
                return sb.append(']').toString();
            sb.append(',').append(' ');
        }
    }


}
