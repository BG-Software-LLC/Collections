package com.bgsoftware.common.collections.internal.maps;

import com.bgsoftware.common.collections.ints.Int2IntMap;
import com.bgsoftware.common.collections.ints.Int2ObjectMap;
import com.bgsoftware.common.collections.ints.Object2IntMap;
import com.bgsoftware.common.collections.ints.java.JavaInt2IntMap;
import com.bgsoftware.common.collections.ints.java.JavaInt2ObjectMap;
import com.bgsoftware.common.collections.ints.java.JavaObject2IntMap;
import com.bgsoftware.common.collections.longs.Long2LongMap;
import com.bgsoftware.common.collections.longs.Long2ObjectMap;
import com.bgsoftware.common.collections.longs.Object2LongMap;
import com.bgsoftware.common.collections.longs.java.JavaLong2LongMap;
import com.bgsoftware.common.collections.longs.java.JavaLong2ObjectMap;
import com.bgsoftware.common.collections.longs.java.JavaObject2LongMap;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class JavaMapsFactory implements MapsFactory {

    public static final JavaMapsFactory INSTANCE = new JavaMapsFactory();

    private JavaMapsFactory() {

    }

    @Override
    public <K, V> Map<K, V> newMap(MapStrategy strategy) {
        switch (strategy) {
            case HASH_MAP:
                return new HashMap<>();
            case LINKED_HASH_MAP:
                return new LinkedHashMap<>();
            case CONCURRENT_HASH_MAP:
                return new ConcurrentHashMap<>();
        }

        throw new IllegalArgumentException("strategy: " + strategy);
    }

    @Override
    public <V> Int2ObjectMap<V> newInt2ObjectMap(MapStrategy strategy) {
        Map<Integer, V> handle;
        switch (strategy) {
            case ARRAY_MAP:
                handle = new ArrayMap<>();
                break;
            case HASH_MAP:
                handle = new HashMap<>();
                break;
            case LINKED_HASH_MAP:
                handle = new LinkedHashMap<>();
                break;
            default:
                throw new IllegalArgumentException("strategy: " + strategy);
        }

        return new JavaInt2ObjectMap<>(handle);
    }

    @Override
    public <K> Object2IntMap<K> newObject2IntMap(MapStrategy strategy) {
        Map<K, Integer> handle;
        switch (strategy) {
            case ARRAY_MAP:
                handle = new ArrayMap<>();
                break;
            case HASH_MAP:
                handle = new HashMap<>();
                break;
            case LINKED_HASH_MAP:
                handle = new LinkedHashMap<>();
                break;
            default:
                throw new IllegalArgumentException("strategy: " + strategy);
        }

        return new JavaObject2IntMap<>(handle);
    }

    @Override
    public Int2IntMap newInt2IntMap(MapStrategy strategy) {
        Map<Integer, Integer> handle;
        switch (strategy) {
            case ARRAY_MAP:
                handle = new ArrayMap<>();
                break;
            case HASH_MAP:
                handle = new HashMap<>();
                break;
            case LINKED_HASH_MAP:
                handle = new LinkedHashMap<>();
                break;
            default:
                throw new IllegalArgumentException("strategy: " + strategy);
        }

        return new JavaInt2IntMap(handle);
    }

    @Override
    public <V> Long2ObjectMap<V> newLong2ObjectMap(MapStrategy strategy) {
        Map<Long, V> handle;
        switch (strategy) {
            case ARRAY_MAP:
                handle = new ArrayMap<>();
                break;
            case HASH_MAP:
                handle = new HashMap<>();
                break;
            case LINKED_HASH_MAP:
                handle = new LinkedHashMap<>();
                break;
            default:
                throw new IllegalArgumentException("strategy: " + strategy);
        }

        return new JavaLong2ObjectMap<>(handle);
    }

    @Override
    public <K> Object2LongMap<K> newObject2LongMap(MapStrategy strategy) {
        Map<K, Long> handle;
        switch (strategy) {
            case ARRAY_MAP:
                handle = new ArrayMap<>();
                break;
            case HASH_MAP:
                handle = new HashMap<>();
                break;
            case LINKED_HASH_MAP:
                handle = new LinkedHashMap<>();
                break;
            default:
                throw new IllegalArgumentException("strategy: " + strategy);
        }

        return new JavaObject2LongMap<>(handle);
    }

    @Override
    public Long2LongMap newLong2LongMap(MapStrategy strategy) {
        Map<Long, Long> handle;
        switch (strategy) {
            case ARRAY_MAP:
                handle = new ArrayMap<>();
                break;
            case HASH_MAP:
                handle = new HashMap<>();
                break;
            case LINKED_HASH_MAP:
                handle = new LinkedHashMap<>();
                break;
            default:
                throw new IllegalArgumentException("strategy: " + strategy);
        }

        return new JavaLong2LongMap(handle);
    }
}
