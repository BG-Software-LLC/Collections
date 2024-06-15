package com.bgsoftware.common.collections.longs.transform;

import com.bgsoftware.common.collections.internal.transform.TransformedCollection;
import com.bgsoftware.common.collections.internal.transform.TransformedSpliterator;
import com.bgsoftware.common.collections.ints.empty.EmptyIntCollection;
import com.bgsoftware.common.collections.longs.LongCollection;
import com.bgsoftware.common.collections.longs.LongIterator;
import com.bgsoftware.common.collections.transform.LongTransformer;
import com.bgsoftware.common.collections.transform.Transformer;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.RandomAccess;
import java.util.Spliterator;
import java.util.function.Consumer;
import java.util.function.Predicate;

public class TransformedLongCollection<E> implements Collection<E> {

    private static final Object[] EMPTY_ARR = new Object[0];

    protected final LongCollection handle;
    protected final LongTransformer<E> transformer;
    protected Transformer<Long, E> boxedTransformer;

    public static <E> TransformedLongCollection<E> create(LongCollection handle, LongTransformer<E> transformer) {
        return handle instanceof RandomAccess ? new RandomAccessTransformedIntCollection<>(handle, transformer) :
                new TransformedLongCollection<>(handle, transformer);
    }

    private static class RandomAccessTransformedIntCollection<E> extends TransformedLongCollection<E> implements RandomAccess {

        private RandomAccessTransformedIntCollection(LongCollection handle, LongTransformer<E> transformer) {
            super(handle, transformer);
        }

    }

    protected TransformedLongCollection(LongCollection handle, LongTransformer<E> transformer) {
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
        return new TransformedLongIterator<>(this.handle.iterator(), this.transformer);
    }

    @Override
    public Object[] toArray() {
        if(isEmpty()) return EMPTY_ARR;

        long[] elements = this.handle.toArray();
        Object[] arr = new Object[elements.length];
        for (int i = 0; i < elements.length; ++i)
            arr[i] = this.transformer.transformLong(elements[i]);
        return arr;
    }

    @Override
    public <T> T[] toArray(T[] a) {
        long[] elements = this.handle.toArray();
        int size = elements.length;

        if (a.length < size) {
            a = Arrays.copyOf(a, size);
        }

        for(int i = 0; i < size; ++i) {
            a[i] = (T) this.transformer.transformLong(elements[i]);
        }

        return a;
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

        LongIterator iterator = this.handle.iterator();
        while (iterator.hasNext()) {
            if (!c.contains(this.transformer.transformLong(iterator.next())))
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
        LongIterator iterator = this.handle.iterator();
        boolean modified = false;
        while (iterator.hasNext()) {
            if (filter.test(this.transformer.transformLong(iterator.next()))) {
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
        this.handle.iterator().forEachRemaining(elem -> action.accept(this.transformer.transformLong(elem)));
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

    protected Transformer<Long, E> getBoxedTransformer() {
        if (boxedTransformer == null) {
            this.boxedTransformer = new Transformer<Long, E>() {
                @Override
                public E transformA(Long value) {
                    return transformer.transformLong(value);
                }

                @Override
                public Long transformB(E value) {
                    return transformer.transform(value);
                }
            };
        }

        return this.boxedTransformer;
    }


}
