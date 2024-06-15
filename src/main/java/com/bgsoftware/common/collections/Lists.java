package com.bgsoftware.common.collections;

import com.bgsoftware.common.collections.internal.Implementation;
import com.bgsoftware.common.collections.internal.immutable.UnmodifiableList;
import com.bgsoftware.common.collections.internal.lists.FastUtilsListsFactory;
import com.bgsoftware.common.collections.internal.lists.JavaListsFactory;
import com.bgsoftware.common.collections.internal.lists.ListStrategy;
import com.bgsoftware.common.collections.internal.lists.ListsFactory;
import com.bgsoftware.common.collections.internal.transform.TransformedList;
import com.bgsoftware.common.collections.ints.IntCollection;
import com.bgsoftware.common.collections.ints.IntList;
import com.bgsoftware.common.collections.ints.empty.EmptyIntList;
import com.bgsoftware.common.collections.ints.immutable.UnmodifiableIntList;
import com.bgsoftware.common.collections.ints.transform.TransformedIntList;
import com.bgsoftware.common.collections.longs.LongCollection;
import com.bgsoftware.common.collections.longs.LongList;
import com.bgsoftware.common.collections.longs.empty.EmptyLongList;
import com.bgsoftware.common.collections.longs.immutable.UnmodifiableLongList;
import com.bgsoftware.common.collections.longs.transform.TransformedLongList;
import com.bgsoftware.common.collections.transform.IntTransformer;
import com.bgsoftware.common.collections.transform.LongTransformer;
import com.bgsoftware.common.collections.transform.Transformer;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class Lists {

    private static final ListsFactory FACTORY = initializeListsFactory();

    public static <E> List<E> newArrayList() {
        return FACTORY.newList(ListStrategy.ARRAY_LIST);
    }

    public static <E> List<E> newArrayList(Collection<E> other) {
        return populateList(newArrayList(), other);
    }

    public static <E> List<E> newArrayList(E[] other) {
        return populateList(newArrayList(), other);
    }

    public static <E> List<E> newLinkedList() {
        return FACTORY.newList(ListStrategy.LINKED_LIST);
    }

    public static <E> List<E> newLinkedList(Collection<E> other) {
        return populateList(newLinkedList(), other);
    }

    public static <E> List<E> newLinkedList(E[] other) {
        return populateList(newLinkedList(), other);
    }

    public static <E> List<E> emptyList() {
        return Collections.emptyList();
    }

    public static IntList newIntArrayList() {
        return FACTORY.newIntArrayList();
    }

    public static IntList newIntArrayList(IntCollection other) {
        return populateList(newIntArrayList(), other);
    }

    public static IntList newIntArrayList(Collection<Integer> other) {
        return populateList(newIntArrayList(), other);
    }

    public static IntList newIntArrayList(int[] other) {
        return populateList(newIntArrayList(), other);
    }

    public static IntList emptyIntArrayList() {
        return EmptyIntList.INSTANCE;
    }

    public static LongList newLongArrayList() {
        return FACTORY.newLongArrayList();
    }

    public static LongList newLongArrayList(LongCollection other) {
        return populateList(newLongArrayList(), other);
    }

    public static LongList newLongArrayList(Collection<Long> other) {
        return populateList(newLongArrayList(), other);
    }

    public static LongList newLongArrayList(long[] other) {
        return populateList(newLongArrayList(), other);
    }

    public static LongList emptyLongArrayList() {
        return EmptyLongList.INSTANCE;
    }

    public static <A, B> List<B> transform(List<A> list, Transformer<A, B> transformer) {
        return TransformedList.create(list, transformer);
    }

    public static <E> List<E> transform(IntList list, IntTransformer<E> transformer) {
        return TransformedIntList.create(list, transformer);
    }

    public static <E> List<E> transform(LongList list, LongTransformer<E> transformer) {
        return TransformedLongList.create(list, transformer);
    }

    public static <E> List<E> unmodifiable(List<E> list) {
        return UnmodifiableList.create(list);
    }

    public static IntList unmodifiable(IntList list) {
        return UnmodifiableIntList.create(list);
    }

    public static LongList unmodifiable(LongList list) {
        return UnmodifiableLongList.create(list);
    }

    private Lists() {

    }

    private static <E> List<E> populateList(List<E> list, Collection<E> other) {
        list.addAll(other);
        return list;
    }

    private static <E> List<E> populateList(List<E> list, E[] other) {
        Collections.addAll(list, other);
        return list;
    }

    private static IntList populateList(IntList list, IntCollection other) {
        other.iterator().forEachRemaining(list::add);
        return list;
    }

    private static IntList populateList(IntList list, Collection<Integer> other) {
        other.iterator().forEachRemaining(list::add);
        return list;
    }

    private static IntList populateList(IntList list, int[] other) {
        for (int i : other)
            list.add(i);
        return list;
    }

    private static LongList populateList(LongList list, LongCollection other) {
        other.iterator().forEachRemaining(list::add);
        return list;
    }

    private static LongList populateList(LongList list, Collection<Long> other) {
        other.iterator().forEachRemaining(list::add);
        return list;
    }

    private static LongList populateList(LongList list, long[] other) {
        for (long i : other)
            list.add(i);
        return list;
    }

    private static ListsFactory initializeListsFactory() {
        switch (Implementation.getImplementation()) {
            case FAST_UTILS:
                return FastUtilsListsFactory.INSTANCE;
            case JAVA:
            default:
                return JavaListsFactory.INSTANCE;
        }
    }

}
