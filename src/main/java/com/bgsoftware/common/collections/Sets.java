package com.bgsoftware.common.collections;

import com.bgsoftware.common.collections.internal.Implementation;
import com.bgsoftware.common.collections.internal.sets.FastUtilsSetsFactory;
import com.bgsoftware.common.collections.internal.sets.JavaSetsFactory;
import com.bgsoftware.common.collections.internal.sets.SetStrategy;
import com.bgsoftware.common.collections.internal.sets.SetsFactory;
import com.bgsoftware.common.collections.internal.transform.TransformedSet;
import com.bgsoftware.common.collections.ints.IntCollection;
import com.bgsoftware.common.collections.ints.IntSet;
import com.bgsoftware.common.collections.ints.empty.EmptyIntSet;
import com.bgsoftware.common.collections.ints.transform.TransformedIntSet;
import com.bgsoftware.common.collections.longs.LongCollection;
import com.bgsoftware.common.collections.longs.LongSet;
import com.bgsoftware.common.collections.longs.empty.EmptyLongSet;
import com.bgsoftware.common.collections.longs.transform.TransformedLongSet;
import com.bgsoftware.common.collections.transform.IntTransformer;
import com.bgsoftware.common.collections.transform.LongTransformer;
import com.bgsoftware.common.collections.transform.Transformer;

import java.util.Collection;
import java.util.Collections;
import java.util.Set;

public class Sets {

    private static final SetsFactory FACTORY = initializeSetsFactory();

    public static <E> Set<E> newArraySet() {
        return FACTORY.newSet(SetStrategy.ARRAY_SET);
    }

    public static <E> Set<E> newArraySet(Collection<E> other) {
        return populateSet(newArraySet(), other);
    }

    public static <E> Set<E> newArraySet(E[] other) {
        return populateSet(newArraySet(), other);
    }

    public static <E> Set<E> newHashSet() {
        return FACTORY.newSet(SetStrategy.HASH_SET);
    }

    public static <E> Set<E> newHashSet(Collection<E> other) {
        return populateSet(newHashSet(), other);
    }

    public static <E> Set<E> newHashSet(E[] other) {
        return populateSet(newHashSet(), other);
    }

    public static <E> Set<E> newLinkedHashSet() {
        return FACTORY.newSet(SetStrategy.LINKED_HASH_SET);
    }

    public static <E> Set<E> newLinkedHashSet(Collection<E> other) {
        return populateSet(newLinkedHashSet(), other);
    }

    public static <E> Set<E> newLinkedHashSet(E[] other) {
        return populateSet(newLinkedHashSet(), other);
    }

    public static <E> Set<E> newConcurrentHashSet() {
        return com.google.common.collect.Sets.newConcurrentHashSet();
    }

    public static <E> Set<E> newConcurrentHashSet(Collection<E> other) {
        return populateSet(newConcurrentHashSet(), other);
    }

    public static <E> Set<E> newConcurrentHashSet(E[] other) {
        return populateSet(newConcurrentHashSet(), other);
    }

    public static <E> Set<E> emptySet() {
        return Collections.emptySet();
    }

    public static IntSet newIntArraySet() {
        return FACTORY.newIntSet(SetStrategy.ARRAY_SET);
    }

    public static IntSet newIntArraySet(IntCollection other) {
        return populateSet(newIntArraySet(), other);
    }

    public static IntSet newIntArraySet(Collection<Integer> other) {
        return populateSet(newIntArraySet(), other);
    }

    public static IntSet newIntArraySet(int[] other) {
        return populateSet(newIntArraySet(), other);
    }

    public static IntSet newIntHashSet() {
        return FACTORY.newIntSet(SetStrategy.HASH_SET);
    }

    public static IntSet newIntHashSet(IntCollection other) {
        return populateSet(newIntHashSet(), other);
    }

    public static IntSet newIntHashSet(Collection<Integer> other) {
        return populateSet(newIntHashSet(), other);
    }

    public static IntSet newIntHashSet(int[] other) {
        return populateSet(newIntHashSet(), other);
    }

    public static IntSet newIntLinkedHashSet() {
        return FACTORY.newIntSet(SetStrategy.LINKED_HASH_SET);
    }

    public static IntSet newIntLinkedHashSet(IntCollection other) {
        return populateSet(newIntLinkedHashSet(), other);
    }

    public static IntSet newIntLinkedHashSet(Collection<Integer> other) {
        return populateSet(newIntLinkedHashSet(), other);
    }

    public static IntSet newIntLinkedHashSet(int[] other) {
        return populateSet(newIntLinkedHashSet(), other);
    }

    public static IntSet emptyIntSet() {
        return EmptyIntSet.INSTANCE;
    }

    public static LongSet newLongArraySet() {
        return FACTORY.newLongSet(SetStrategy.ARRAY_SET);
    }

    public static LongSet newLongArraySet(LongCollection other) {
        return populateSet(newLongArraySet(), other);
    }

    public static LongSet newLongArraySet(Collection<Long> other) {
        return populateSet(newLongArraySet(), other);
    }

    public static LongSet newLongArraySet(long[] other) {
        return populateSet(newLongArraySet(), other);
    }

    public static LongSet newLongHashSet() {
        return FACTORY.newLongSet(SetStrategy.HASH_SET);
    }

    public static LongSet newLongHashSet(LongCollection other) {
        return populateSet(newLongHashSet(), other);
    }

    public static LongSet newLongHashSet(Collection<Long> other) {
        return populateSet(newLongHashSet(), other);
    }

    public static LongSet newLongHashSet(long[] other) {
        return populateSet(newLongHashSet(), other);
    }

    public static LongSet newLongLinkedHashSet() {
        return FACTORY.newLongSet(SetStrategy.LINKED_HASH_SET);
    }

    public static LongSet newLongLinkedHashSet(LongCollection other) {
        return populateSet(newLongLinkedHashSet(), other);
    }

    public static LongSet newLongLinkedHashSet(Collection<Long> other) {
        return populateSet(newLongLinkedHashSet(), other);
    }

    public static LongSet newLongLinkedHashSet(long[] other) {
        return populateSet(newLongLinkedHashSet(), other);
    }

    public static LongSet emptyLongSet() {
        return EmptyLongSet.INSTANCE;
    }

    public static <A, B> Set<B> transform(Set<A> set, Transformer<A, B> transformer) {
        return TransformedSet.create(set, transformer);
    }

    public static <E> Set<E> transform(IntSet set, IntTransformer<E> transformer) {
        return TransformedIntSet.create(set, transformer);
    }

    public static <E> Set<E> transform(LongSet set, LongTransformer<E> transformer) {
        return TransformedLongSet.create(set, transformer);
    }

    private Sets() {

    }

    private static <E> Set<E> populateSet(Set<E> set, Collection<E> other) {
        set.addAll(other);
        return set;
    }

    private static <E> Set<E> populateSet(Set<E> set, E[] other) {
        Collections.addAll(set, other);
        return set;
    }

    private static IntSet populateSet(IntSet set, IntCollection other) {
        other.iterator().forEachRemaining(set::add);
        return set;
    }

    private static IntSet populateSet(IntSet set, Collection<Integer> other) {
        other.forEach(set::add);
        return set;
    }

    private static IntSet populateSet(IntSet set, int[] other) {
        for (int i : other)
            set.add(i);
        return set;
    }

    private static LongSet populateSet(LongSet set, LongCollection other) {
        other.iterator().forEachRemaining(set::add);
        return set;
    }

    private static LongSet populateSet(LongSet set, Collection<Long> other) {
        other.forEach(set::add);
        return set;
    }

    private static LongSet populateSet(LongSet set, long[] other) {
        for (long i : other)
            set.add(i);
        return set;
    }

    private static SetsFactory initializeSetsFactory() {
        switch (Implementation.getImplementation()) {
            case FAST_UTILS:
                return FastUtilsSetsFactory.INSTANCE;
            case JAVA:
            default:
                return JavaSetsFactory.INSTANCE;
        }
    }

}
