package com.bgsoftware.common.collections.internal.immutable;

import java.util.Collection;
import java.util.Comparator;
import java.util.List;
import java.util.ListIterator;
import java.util.function.Consumer;
import java.util.function.UnaryOperator;

public class UnmodifiableList<E> extends UnmodifiableCollection<E> implements List<E> {

    public static <E> UnmodifiableList<E> create(List<E> handle) {
        return handle instanceof UnmodifiableList ?
                (UnmodifiableList<E>) handle :
                new UnmodifiableList<>(handle);
    }

    private UnmodifiableList(List<E> handle) {
        super(handle);
    }

    @Override
    public E get(int index) {
        return ((List<E>) this.handle).get(index);
    }

    @Override
    public E set(int index, E element) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void add(int index, E element) {
        throw new UnsupportedOperationException();
    }

    @Override
    public E remove(int index) {
        throw new UnsupportedOperationException();
    }

    @Override
    public int indexOf(Object o) {
        return ((List<E>) this.handle).indexOf(o);
    }

    @Override
    public int lastIndexOf(Object o) {
        return ((List<E>) this.handle).lastIndexOf(o);
    }

    @Override
    public ListIterator<E> listIterator() {
        return new UnmodifiableListIterator<>(((List<E>) this.handle).listIterator());
    }

    @Override
    public ListIterator<E> listIterator(int index) {
        return new UnmodifiableListIterator<>(((List<E>) this.handle).listIterator(index));
    }

    @Override
    public List<E> subList(int fromIndex, int toIndex) {
        return UnmodifiableList.create(((List<E>) this.handle).subList(fromIndex, toIndex));
    }

    @Override
    public void replaceAll(UnaryOperator<E> operator) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean addAll(int index, Collection<? extends E> c) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void sort(Comparator<? super E> c) {
        throw new UnsupportedOperationException();
    }

    private static class UnmodifiableListIterator<E> implements ListIterator<E> {

        private final ListIterator<E> handle;

        UnmodifiableListIterator(ListIterator<E> handle) {
            this.handle = handle;
        }

        @Override
        public boolean hasNext() {
            return handle.hasNext();
        }

        @Override
        public E next() {
            return handle.next();
        }

        @Override
        public boolean hasPrevious() {
            return handle.hasPrevious();
        }

        @Override
        public E previous() {
            return handle.previous();
        }

        @Override
        public int nextIndex() {
            return handle.nextIndex();
        }

        @Override
        public int previousIndex() {
            return handle.previousIndex();
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException();
        }

        @Override
        public void set(E e) {
            throw new UnsupportedOperationException();
        }

        @Override
        public void add(E e) {
            throw new UnsupportedOperationException();
        }

        @Override
        public void forEachRemaining(Consumer<? super E> action) {
            handle.forEachRemaining(action);
        }

    }

}
