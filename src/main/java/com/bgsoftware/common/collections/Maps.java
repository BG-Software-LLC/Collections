package com.bgsoftware.common.collections;

import com.bgsoftware.common.collections.internal.Implementation;
import com.bgsoftware.common.collections.internal.maps.FastUtilsMapsFactory;
import com.bgsoftware.common.collections.internal.maps.JavaMapsFactory;
import com.bgsoftware.common.collections.internal.maps.MapStrategy;
import com.bgsoftware.common.collections.internal.maps.MapsFactory;
import com.bgsoftware.common.collections.internal.transform.TransformedMap;
import com.bgsoftware.common.collections.ints.Int2IntMap;
import com.bgsoftware.common.collections.ints.Int2ObjectMap;
import com.bgsoftware.common.collections.ints.Object2IntMap;
import com.bgsoftware.common.collections.ints.empty.EmptyInt2IntMap;
import com.bgsoftware.common.collections.ints.empty.EmptyInt2ObjectMap;
import com.bgsoftware.common.collections.ints.empty.EmptyObject2IntMap;
import com.bgsoftware.common.collections.ints.transform.TransformedInt2ObjectMap;
import com.bgsoftware.common.collections.ints.transform.TransformedObject2IntMap;
import com.bgsoftware.common.collections.longs.Long2LongMap;
import com.bgsoftware.common.collections.longs.Long2ObjectMap;
import com.bgsoftware.common.collections.longs.Object2LongMap;
import com.bgsoftware.common.collections.longs.empty.EmptyLong2LongMap;
import com.bgsoftware.common.collections.longs.empty.EmptyLong2ObjectMap;
import com.bgsoftware.common.collections.longs.empty.EmptyObject2LongMap;
import com.bgsoftware.common.collections.longs.transform.TransformedLong2ObjectMap;
import com.bgsoftware.common.collections.longs.transform.TransformedObject2LongMap;
import com.bgsoftware.common.collections.transform.Transformer;

import java.util.Collections;
import java.util.Map;

public class Maps {

    private static final MapsFactory FACTORY = initializeMapsFactory();

    public static <K, V> Map<K, V> newArrayMap() {
        return FACTORY.newMap(MapStrategy.ARRAY_MAP);
    }

    public static <K, V> Map<K, V> newArrayMap(Map<K, V> other) {
        return populateMap(newArrayMap(), other);
    }

    public static <K, V> Map<K, V> newHashMap() {
        return FACTORY.newMap(MapStrategy.HASH_MAP);
    }

    public static <K, V> Map<K, V> newHashMap(Map<K, V> other) {
        return populateMap(newHashMap(), other);
    }

    public static <K, V> Map<K, V> newLinkedHashMap() {
        return FACTORY.newMap(MapStrategy.LINKED_HASH_MAP);
    }

    public static <K, V> Map<K, V> newLinkedHashMap(Map<K, V> other) {
        return populateMap(newLinkedHashMap(), other);
    }

    public static <K, V> Map<K, V> newConcurrentHashMap() {
        return FACTORY.newMap(MapStrategy.CONCURRENT_HASH_MAP);
    }

    public static <K, V> Map<K, V> newConcurrentHashMap(Map<K, V> other) {
        return populateMap(newConcurrentHashMap(), other);
    }

    public static <K, V> Map<K, V> emptyMap() {
        return Collections.emptyMap();
    }

    public static <V> Int2ObjectMap<V> newInt2ObjectArrayMap() {
        return FACTORY.newInt2ObjectMap(MapStrategy.ARRAY_MAP);
    }

    public static <V> Int2ObjectMap<V> newInt2ObjectArrayMap(Int2ObjectMap<V> other) {
        return populateMap(newInt2ObjectArrayMap(), other);
    }

    public static <V> Int2ObjectMap<V> newInt2ObjectArrayMap(Map<Integer, V> other) {
        return populateMap(newInt2ObjectArrayMap(), other);
    }

    public static <V> Int2ObjectMap<V> newInt2ObjectHashMap() {
        return FACTORY.newInt2ObjectMap(MapStrategy.HASH_MAP);
    }

    public static <V> Int2ObjectMap<V> newInt2ObjectHashMap(Int2ObjectMap<V> other) {
        return populateMap(newInt2ObjectHashMap(), other);
    }

    public static <V> Int2ObjectMap<V> newInt2ObjectHashMap(Map<Integer, V> other) {
        return populateMap(newInt2ObjectHashMap(), other);
    }

    public static <V> Int2ObjectMap<V> newInt2ObjectLinkedHashMap() {
        return FACTORY.newInt2ObjectMap(MapStrategy.LINKED_HASH_MAP);
    }

    public static <V> Int2ObjectMap<V> newInt2ObjectLinkedHashMap(Int2ObjectMap<V> other) {
        return populateMap(newInt2ObjectLinkedHashMap(), other);
    }

    public static <V> Int2ObjectMap<V> newInt2ObjectLinkedHashMap(Map<Integer, V> other) {
        return populateMap(newInt2ObjectLinkedHashMap(), other);
    }

    public static <V> Int2ObjectMap<V> emptyInt2ObjectMap() {
        return EmptyInt2ObjectMap.INSTANCE;
    }

    public static <K> Object2IntMap<K> newObject2IntArrayMap() {
        return FACTORY.newObject2IntMap(MapStrategy.ARRAY_MAP);
    }

    public static <K> Object2IntMap<K> newObject2IntArrayMap(Object2IntMap<K> other) {
        return populateMap(newObject2IntArrayMap(), other);
    }

    public static <K> Object2IntMap<K> newObject2IntArrayMap(Map<K, Integer> other) {
        return populateMap(newObject2IntArrayMap(), other);
    }

    public static <K> Object2IntMap<K> newObject2IntHashMap() {
        return FACTORY.newObject2IntMap(MapStrategy.HASH_MAP);
    }

    public static <K> Object2IntMap<K> newObject2IntHashMap(Object2IntMap<K> other) {
        return populateMap(newObject2IntHashMap(), other);
    }

    public static <K> Object2IntMap<K> newObject2IntHashMap(Map<K, Integer> other) {
        return populateMap(newObject2IntHashMap(), other);
    }

    public static <K> Object2IntMap<K> newObject2IntLinkedHashMap() {
        return FACTORY.newObject2IntMap(MapStrategy.LINKED_HASH_MAP);
    }

    public static <K> Object2IntMap<K> newObject2IntLinkedHashMap(Object2IntMap<K> other) {
        return populateMap(newObject2IntLinkedHashMap(), other);
    }

    public static <K> Object2IntMap<K> newObject2IntLinkedHashMap(Map<K, Integer> other) {
        return populateMap(newObject2IntLinkedHashMap(), other);
    }

    public static <K> Object2IntMap<K> emptyObject2IntMap() {
        return EmptyObject2IntMap.INSTANCE;
    }

    public static Int2IntMap newInt2IntArrayMap() {
        return FACTORY.newInt2IntMap(MapStrategy.ARRAY_MAP);
    }

    public static Int2IntMap newInt2IntArrayMap(Int2IntMap other) {
        return populateMap(newInt2IntArrayMap(), other);
    }

    public static Int2IntMap newInt2IntArrayMap(Map<Integer, Integer> other) {
        return populateMap(newInt2IntArrayMap(), other);
    }

    public static Int2IntMap newInt2IntHashMap() {
        return FACTORY.newInt2IntMap(MapStrategy.HASH_MAP);
    }

    public static Int2IntMap newInt2IntHashMap(Int2IntMap other) {
        return populateMap(newInt2IntHashMap(), other);
    }

    public static Int2IntMap newInt2IntHashMap(Map<Integer, Integer> other) {
        return populateMap(newInt2IntHashMap(), other);
    }

    public static Int2IntMap newInt2IntLinkedHashMap() {
        return FACTORY.newInt2IntMap(MapStrategy.LINKED_HASH_MAP);
    }

    public static Int2IntMap newInt2IntLinkedHashMap(Int2IntMap other) {
        return populateMap(newInt2IntLinkedHashMap(), other);
    }

    public static Int2IntMap newInt2IntLinkedHashMap(Map<Integer, Integer> other) {
        return populateMap(newInt2IntLinkedHashMap(), other);
    }

    public static Int2IntMap emptyInt2IntMap() {
        return EmptyInt2IntMap.INSTANCE;
    }

    public static <V> Long2ObjectMap<V> newLong2ObjectArrayMap() {
        return FACTORY.newLong2ObjectMap(MapStrategy.ARRAY_MAP);
    }

    public static <V> Long2ObjectMap<V> newLong2ObjectArrayMap(Long2ObjectMap<V> other) {
        return populateMap(newLong2ObjectArrayMap(), other);
    }

    public static <V> Long2ObjectMap<V> newLong2ObjectArrayMap(Map<Long, V> other) {
        return populateMap(newLong2ObjectArrayMap(), other);
    }

    public static <V> Long2ObjectMap<V> newLong2ObjectHashMap() {
        return FACTORY.newLong2ObjectMap(MapStrategy.HASH_MAP);
    }

    public static <V> Long2ObjectMap<V> newLong2ObjectHashMap(Long2ObjectMap<V> other) {
        return populateMap(newLong2ObjectHashMap(), other);
    }

    public static <V> Long2ObjectMap<V> newLong2ObjectHashMap(Map<Long, V> other) {
        return populateMap(newLong2ObjectHashMap(), other);
    }

    public static <V> Long2ObjectMap<V> newLong2ObjectLinkedHashMap() {
        return FACTORY.newLong2ObjectMap(MapStrategy.LINKED_HASH_MAP);
    }

    public static <V> Long2ObjectMap<V> newLong2ObjectLinkedHashMap(Long2ObjectMap<V> other) {
        return populateMap(newLong2ObjectLinkedHashMap(), other);
    }

    public static <V> Long2ObjectMap<V> newLong2ObjectLinkedHashMap(Map<Long, V> other) {
        return populateMap(newLong2ObjectLinkedHashMap(), other);
    }

    public static <V> Long2ObjectMap<V> emptyLong2ObjectMap() {
        return EmptyLong2ObjectMap.INSTANCE;
    }

    public static <K> Object2LongMap<K> newObject2LongArrayMap() {
        return FACTORY.newObject2LongMap(MapStrategy.ARRAY_MAP);
    }

    public static <K> Object2LongMap<K> newObject2LongArrayMap(Object2LongMap<K> other) {
        return populateMap(newObject2LongArrayMap(), other);
    }

    public static <K> Object2LongMap<K> newObject2LongArrayMap(Map<K, Long> other) {
        return populateMap(newObject2LongArrayMap(), other);
    }

    public static <K> Object2LongMap<K> newObject2LongHashMap() {
        return FACTORY.newObject2LongMap(MapStrategy.HASH_MAP);
    }

    public static <K> Object2LongMap<K> newObject2LongHashMap(Object2LongMap<K> other) {
        return populateMap(newObject2LongHashMap(), other);
    }

    public static <K> Object2LongMap<K> newObject2LongHashMap(Map<K, Long> other) {
        return populateMap(newObject2LongHashMap(), other);
    }

    public static <K> Object2LongMap<K> newObject2LongLinkedHashMap() {
        return FACTORY.newObject2LongMap(MapStrategy.LINKED_HASH_MAP);
    }

    public static <K> Object2LongMap<K> newObject2LongLinkedHashMap(Object2LongMap<K> other) {
        return populateMap(newObject2LongLinkedHashMap(), other);
    }

    public static <K> Object2LongMap<K> newObject2LongLinkedHashMap(Map<K, Long> other) {
        return populateMap(newObject2LongLinkedHashMap(), other);
    }

    public static <K> Object2LongMap<K> emptyObject2LongMap() {
        return EmptyObject2LongMap.INSTANCE;
    }

    public static Long2LongMap newLong2LongArrayMap() {
        return FACTORY.newLong2LongMap(MapStrategy.ARRAY_MAP);
    }

    public static Long2LongMap newLong2LongArrayMap(Long2LongMap other) {
        return populateMap(newLong2LongArrayMap(), other);
    }

    public static Long2LongMap newLong2LongArrayMap(Map<Long, Long> other) {
        return populateMap(newLong2LongArrayMap(), other);
    }

    public static Long2LongMap newLong2LongHashMap() {
        return FACTORY.newLong2LongMap(MapStrategy.HASH_MAP);
    }

    public static Long2LongMap newLong2LongHashMap(Long2LongMap other) {
        return populateMap(newLong2LongHashMap(), other);
    }

    public static Long2LongMap newLong2LongHashMap(Map<Long, Long> other) {
        return populateMap(newLong2LongHashMap(), other);
    }

    public static Long2LongMap newLong2LongLinkedHashMap() {
        return FACTORY.newLong2LongMap(MapStrategy.LINKED_HASH_MAP);
    }

    public static Long2LongMap newLong2LongLinkedHashMap(Long2LongMap other) {
        return populateMap(newLong2LongLinkedHashMap(), other);
    }

    public static Long2LongMap newLong2LongLinkedHashMap(Map<Long, Long> other) {
        return populateMap(newLong2LongLinkedHashMap(), other);
    }

    public static Long2LongMap emptyLong2LongMap() {
        return EmptyLong2LongMap.INSTANCE;
    }

    public static <K, V, V2> Map<K, V2> transformValues(Map<K, V> map, Transformer<V, V2> valuesTransformer) {
        return new TransformedMap<>(map, null, valuesTransformer);
    }

    public static <K, V, K2> Map<K2, V> transformKeys(Map<K, V> map, Transformer<K, K2> keysTransformer) {
        return new TransformedMap<>(map, keysTransformer, null);
    }

    public static <K1, V1, K2, V2> Map<K2, V2> transform(Map<K1, V1> map, Transformer<K1, K2> keysTransformer,
                                                         Transformer<V1, V2> valuesTransformer) {
        return new TransformedMap<>(map, keysTransformer, valuesTransformer);
    }

    public static <V1, V2> Int2ObjectMap<V2> transform(Int2ObjectMap<V1> map, Transformer<V1, V2> valuesTransformer) {
        return new TransformedInt2ObjectMap<>(map, valuesTransformer);
    }

    public static <K1, K2> Object2IntMap<K2> transform(Object2IntMap<K1> map, Transformer<K1, K2> keysTransformer) {
        return new TransformedObject2IntMap<>(map, keysTransformer);
    }

    public static <V1, V2> Long2ObjectMap<V2> transform(Long2ObjectMap<V1> map, Transformer<V1, V2> valuesTransformer) {
        return new TransformedLong2ObjectMap<>(map, valuesTransformer);
    }

    public static <K1, K2> Object2LongMap<K2> transform(Object2LongMap<K1> map, Transformer<K1, K2> keysTransformer) {
        return new TransformedObject2LongMap<>(map, keysTransformer);
    }

    private Maps() {

    }

    private static <K, V> Map<K, V> populateMap(Map<K, V> map, Map<K, V> other) {
        map.putAll(other);
        return map;
    }

    private static <V> Int2ObjectMap<V> populateMap(Int2ObjectMap<V> map, Int2ObjectMap<V> other) {
        other.entriesIterator().forEachRemaining(entry -> map.put(entry.getKey(), entry.getValue()));
        return map;
    }

    private static <V> Int2ObjectMap<V> populateMap(Int2ObjectMap<V> map, Map<Integer, V> other) {
        other.forEach(map::put);
        return map;
    }

    private static <K> Object2IntMap<K> populateMap(Object2IntMap<K> map, Object2IntMap<K> other) {
        other.entriesIterator().forEachRemaining(entry -> map.put(entry.getKey(), entry.getValue()));
        return map;
    }

    private static <K> Object2IntMap<K> populateMap(Object2IntMap<K> map, Map<K, Integer> other) {
        other.forEach(map::put);
        return map;
    }

    private static Int2IntMap populateMap(Int2IntMap map, Int2IntMap other) {
        other.entriesIterator().forEachRemaining(entry -> map.put(entry.getKey(), entry.getValue()));
        return map;
    }

    private static Int2IntMap populateMap(Int2IntMap map, Map<Integer, Integer> other) {
        other.forEach(map::put);
        return map;
    }

    private static <V> Long2ObjectMap<V> populateMap(Long2ObjectMap<V> map, Long2ObjectMap<V> other) {
        other.entriesIterator().forEachRemaining(entry -> map.put(entry.getKey(), entry.getValue()));
        return map;
    }

    private static <V> Long2ObjectMap<V> populateMap(Long2ObjectMap<V> map, Map<Long, V> other) {
        other.forEach(map::put);
        return map;
    }

    private static <K> Object2LongMap<K> populateMap(Object2LongMap<K> map, Object2LongMap<K> other) {
        other.entriesIterator().forEachRemaining(entry -> map.put(entry.getKey(), entry.getValue()));
        return map;
    }

    private static <K> Object2LongMap<K> populateMap(Object2LongMap<K> map, Map<K, Long> other) {
        other.forEach(map::put);
        return map;
    }

    private static Long2LongMap populateMap(Long2LongMap map, Long2LongMap other) {
        other.entriesIterator().forEachRemaining(entry -> map.put(entry.getKey(), entry.getValue()));
        return map;
    }

    private static Long2LongMap populateMap(Long2LongMap map, Map<Long, Long> other) {
        other.forEach(map::put);
        return map;
    }

    private static MapsFactory initializeMapsFactory() {
        switch (Implementation.getImplementation()) {
            case FAST_UTILS:
                return FastUtilsMapsFactory.INSTANCE;
            case JAVA:
            default:
                return JavaMapsFactory.INSTANCE;
        }
    }

}
