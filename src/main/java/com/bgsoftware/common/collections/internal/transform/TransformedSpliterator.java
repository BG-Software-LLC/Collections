package com.bgsoftware.common.collections.internal.transform;

import com.bgsoftware.common.collections.transform.Transformer;

import java.util.Comparator;
import java.util.Spliterator;
import java.util.function.Consumer;

public class TransformedSpliterator<A, B> implements Spliterator<B> {

    private final Spliterator<A> handle;
    private final Transformer<A, B> transformer;

    public TransformedSpliterator(Spliterator<A> handle, Transformer<A, B> transformer) {
        this.handle = handle;
        this.transformer = transformer;
    }

    @Override
    public boolean tryAdvance(Consumer<? super B> action) {
        return this.handle.tryAdvance(elem -> action.accept(this.transformer.transformA(elem)));
    }

    @Override
    public Spliterator<B> trySplit() {
        return new TransformedSpliterator<>(this.handle.trySplit(), transformer);
    }

    @Override
    public long estimateSize() {
        return this.handle.estimateSize();
    }

    @Override
    public int characteristics() {
        return this.handle.characteristics();
    }

    @Override
    public void forEachRemaining(Consumer<? super B> action) {
        this.handle.forEachRemaining(elem -> action.accept(this.transformer.transformA(elem)));
    }

    @Override
    public long getExactSizeIfKnown() {
        return this.handle.getExactSizeIfKnown();
    }

    @Override
    public boolean hasCharacteristics(int characteristics) {
        return this.handle.hasCharacteristics(characteristics);
    }

    @Override
    public Comparator<? super B> getComparator() {
        Comparator<? super A> c = handle.getComparator();
        return (Comparator<B>) (o1, o2) -> c.compare(transformer.transformB(o1), transformer.transformB(o2));
    }
}
