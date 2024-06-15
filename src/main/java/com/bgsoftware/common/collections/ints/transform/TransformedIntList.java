package com.bgsoftware.common.collections.ints.transform;

import com.bgsoftware.common.collections.internal.transform.TransformedList;
import com.bgsoftware.common.collections.ints.IntList;
import com.bgsoftware.common.collections.transform.IntTransformer;

import java.util.Collection;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;
import java.util.RandomAccess;
import java.util.function.Consumer;
import java.util.function.UnaryOperator;

public class TransformedIntList<E> extends TransformedIntCollection<E> implements List<E> {

    public static <E> TransformedIntList<E> create(IntList handle, IntTransformer<E> transformer) {
        return handle instanceof RandomAccess ? new RandomAccessTransformedIntList<>(handle, transformer) :
                new TransformedIntList<>(handle, transformer);
    }

    private static class RandomAccessTransformedIntList<E> extends TransformedIntList<E> implements RandomAccess {

        private RandomAccessTransformedIntList(IntList handle, IntTransformer<E> transformer) {
            super(handle, transformer);
        }

    }

    protected TransformedIntList(IntList handle, IntTransformer<E> transformer) {
        super(handle, transformer);
    }

    @Override
    public boolean addAll(int index, Collection<? extends E> c) {
        if (c.isEmpty()) return false;
        if (c.size() == 1) return this.add(c.iterator().next());

        boolean modified = false;
        for (E e : c) {
            add(index++, e);
            modified = true;
        }
        return modified;
    }

    @Override
    public E get(int index) {
        return this.transformer.transformInt(((IntList) this.handle).getAt(index));
    }

    @Override
    public E set(int index, E element) {
        return this.transformer.transformInt(((IntList) this.handle).handle().set(index, this.transformer.transform(element)));
    }

    @Override
    public void add(int index, E element) {
        ((IntList) this.handle).handle().add(index, this.transformer.transform(element));
    }

    @Override
    public E remove(int index) {
        return this.transformer.transformInt(((IntList) this.handle).removeAt(index));
    }

    @Override
    public int indexOf(Object o) {
        return ((IntList) this.handle).indexOf(this.transformer.transform((E) o));
    }

    @Override
    public int lastIndexOf(Object o) {
        return ((IntList) this.handle).handle().lastIndexOf(this.transformer.transform((E) o));
    }

    @Override
    public ListIterator<E> listIterator() {
        return new TransformedListIterator(((IntList) this.handle).handle().listIterator());
    }

    @Override
    public ListIterator<E> listIterator(int index) {
        return new TransformedListIterator(((IntList) this.handle).handle().listIterator(index));
    }

    @Override
    public List<E> subList(int fromIndex, int toIndex) {
        return TransformedList.create(((IntList) this.handle).handle().subList(fromIndex, toIndex), getBoxedTransformer());
    }

    @Override
    public void replaceAll(UnaryOperator<E> operator) {
        ((IntList) this.handle).handle().replaceAll(elem ->
                this.transformer.transform(operator.apply(this.transformer.transformInt(elem))));
    }

    @Override
    public void sort(Comparator<? super E> c) {
        ((IntList) this.handle).handle().sort((o1, o2) -> c.compare(transformer.transformInt(o1), transformer.transformInt(o2)));
    }

    private class TransformedListIterator implements ListIterator<E> {

        private final ListIterator<Integer> handle;

        TransformedListIterator(ListIterator<Integer> handle) {
            this.handle = handle;
        }

        @Override
        public boolean hasNext() {
            return this.handle.hasNext();
        }

        @Override
        public E next() {
            return transformer.transformInt(this.handle.next());
        }

        @Override
        public boolean hasPrevious() {
            return this.handle.hasPrevious();
        }

        @Override
        public E previous() {
            return transformer.transformInt(this.handle.previous());
        }

        @Override
        public int nextIndex() {
            return this.handle.nextIndex();
        }

        @Override
        public int previousIndex() {
            return this.handle.previousIndex();
        }

        @Override
        public void remove() {
            this.handle.remove();
        }

        @Override
        public void set(E b) {
            this.handle.set(transformer.transform(b));
        }

        @Override
        public void add(E b) {
            this.handle.add(transformer.transform(b));
        }

        @Override
        public void forEachRemaining(Consumer<? super E> action) {
            this.handle.forEachRemaining(elem -> action.accept(transformer.transformInt(elem)));
        }
    }

}
