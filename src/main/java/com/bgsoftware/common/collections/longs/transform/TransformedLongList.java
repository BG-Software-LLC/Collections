package com.bgsoftware.common.collections.longs.transform;

import com.bgsoftware.common.collections.internal.transform.TransformedList;
import com.bgsoftware.common.collections.longs.LongList;
import com.bgsoftware.common.collections.transform.LongTransformer;

import java.util.Collection;
import java.util.Comparator;
import java.util.List;
import java.util.ListIterator;
import java.util.RandomAccess;
import java.util.function.Consumer;
import java.util.function.UnaryOperator;

public class TransformedLongList<E> extends TransformedLongCollection<E> implements List<E> {

    public static <E> TransformedLongList<E> create(LongList handle, LongTransformer<E> transformer) {
        return handle instanceof RandomAccess ? new RandomAccessTransformedLongList<>(handle, transformer) :
                new TransformedLongList<>(handle, transformer);
    }

    private static class RandomAccessTransformedLongList<E> extends TransformedLongList<E> implements RandomAccess {

        private RandomAccessTransformedLongList(LongList handle, LongTransformer<E> transformer) {
            super(handle, transformer);
        }

    }

    protected TransformedLongList(LongList handle, LongTransformer<E> transformer) {
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
        return this.transformer.transformLong(((LongList) this.handle).getAt(index));
    }

    @Override
    public E set(int index, E element) {
        return this.transformer.transformLong(((LongList) this.handle).handle().set(index, this.transformer.transform(element)));
    }

    @Override
    public void add(int index, E element) {
        ((LongList) this.handle).handle().add(index, this.transformer.transform(element));
    }

    @Override
    public E remove(int index) {
        return this.transformer.transformLong(((LongList) this.handle).removeAt(index));
    }

    @Override
    public int indexOf(Object o) {
        return ((LongList) this.handle).indexOf(this.transformer.transform((E) o));
    }

    @Override
    public int lastIndexOf(Object o) {
        return ((LongList) this.handle).handle().lastIndexOf(this.transformer.transform((E) o));
    }

    @Override
    public ListIterator<E> listIterator() {
        return new TransformedListIterator(((LongList) this.handle).handle().listIterator());
    }

    @Override
    public ListIterator<E> listIterator(int index) {
        return new TransformedListIterator(((LongList) this.handle).handle().listIterator(index));
    }

    @Override
    public List<E> subList(int fromIndex, int toIndex) {
        return TransformedList.create(((LongList) this.handle).handle().subList(fromIndex, toIndex), getBoxedTransformer());
    }

    @Override
    public void replaceAll(UnaryOperator<E> operator) {
        ((LongList) this.handle).handle().replaceAll(elem ->
                this.transformer.transform(operator.apply(this.transformer.transformLong(elem))));
    }

    @Override
    public void sort(Comparator<? super E> c) {
        ((LongList) this.handle).handle().sort((o1, o2) -> c.compare(transformer.transformLong(o1), transformer.transformLong(o2)));
    }

    private class TransformedListIterator implements ListIterator<E> {

        private final ListIterator<Long> handle;

        TransformedListIterator(ListIterator<Long> handle) {
            this.handle = handle;
        }

        @Override
        public boolean hasNext() {
            return this.handle.hasNext();
        }

        @Override
        public E next() {
            return transformer.transformLong(this.handle.next());
        }

        @Override
        public boolean hasPrevious() {
            return this.handle.hasPrevious();
        }

        @Override
        public E previous() {
            return transformer.transformLong(this.handle.previous());
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
            this.handle.forEachRemaining(elem -> action.accept(transformer.transformLong(elem)));
        }
    }

}
