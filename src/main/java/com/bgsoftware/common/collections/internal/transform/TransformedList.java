package com.bgsoftware.common.collections.internal.transform;

import com.bgsoftware.common.collections.transform.Transformer;

import java.util.Collection;
import java.util.Comparator;
import java.util.List;
import java.util.ListIterator;
import java.util.RandomAccess;
import java.util.function.Consumer;
import java.util.function.UnaryOperator;

public class TransformedList<A, B> extends TransformedCollection<A, B> implements List<B> {

    public static <A, B> TransformedList<A, B> create(List<A> handle, Transformer<A, B> transformer) {
        return handle instanceof RandomAccess ? new RandomAccessTransformedList<>(handle, transformer) :
                new TransformedList<>(handle, transformer);
    }

    private static class RandomAccessTransformedList<A, B> extends TransformedList<A, B> implements RandomAccess {

        private RandomAccessTransformedList(List<A> handle, Transformer<A, B> transformer) {
            super(handle, transformer);
        }

    }

    protected TransformedList(List<A> handle, Transformer<A, B> transformer) {
        super(handle, transformer);
    }

    @Override
    public boolean addAll(int index, Collection<? extends B> c) {
        if (c.isEmpty()) return false;
        if (c.size() == 1) return this.add(c.iterator().next());

        boolean modified = false;
        for (B e : c) {
            add(index++, e);
            modified = true;
        }
        return modified;
    }

    @Override
    public B get(int index) {
        return this.transformer.transformA(((List<A>) this.handle).get(index));
    }

    @Override
    public B set(int index, B element) {
        return this.transformer.transformA(((List<A>) this.handle).set(index, this.transformer.transformB(element)));
    }

    @Override
    public void add(int index, B element) {
        ((List<A>) this.handle).add(index, this.transformer.transformB(element));
    }

    @Override
    public B remove(int index) {
        return this.transformer.transformA(((List<A>) this.handle).remove(index));
    }

    @Override
    public int indexOf(Object o) {
        return ((List<A>) this.handle).indexOf(this.transformer.transformB((B) o));
    }

    @Override
    public int lastIndexOf(Object o) {
        return ((List<A>) this.handle).lastIndexOf(this.transformer.transformB((B) o));
    }

    @Override
    public ListIterator<B> listIterator() {
        return new TransformedListIterator(((List<A>) this.handle).listIterator());
    }

    @Override
    public ListIterator<B> listIterator(int index) {
        return new TransformedListIterator(((List<A>) this.handle).listIterator(index));
    }

    @Override
    public List<B> subList(int fromIndex, int toIndex) {
        return TransformedList.create(((List<A>) this.handle).subList(fromIndex, toIndex), this.transformer);
    }

    @Override
    public void replaceAll(UnaryOperator<B> operator) {
        ((List<A>) this.handle).replaceAll(elem -> this.transformer.transformB(operator.apply(this.transformer.transformA(elem))));
    }

    @Override
    public void sort(Comparator<? super B> c) {
        ((List<A>) this.handle).sort((o1, o2) -> c.compare(transformer.transformA(o1), transformer.transformA(o2)));
    }

    private class TransformedListIterator implements ListIterator<B> {

        private final ListIterator<A> handle;

        TransformedListIterator(ListIterator<A> handle) {
            this.handle = handle;
        }

        @Override
        public boolean hasNext() {
            return this.handle.hasNext();
        }

        @Override
        public B next() {
            return transformer.transformA(this.handle.next());
        }

        @Override
        public boolean hasPrevious() {
            return this.handle.hasPrevious();
        }

        @Override
        public B previous() {
            return transformer.transformA(this.handle.previous());
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
        public void set(B b) {
            this.handle.set(transformer.transformB(b));
        }

        @Override
        public void add(B b) {
            this.handle.add(transformer.transformB(b));
        }

        @Override
        public void forEachRemaining(Consumer<? super B> action) {
            this.handle.forEachRemaining(elem -> action.accept(transformer.transformA(elem)));
        }
    }

}
