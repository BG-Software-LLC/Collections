package com.bgsoftware.common.collections.internal.maps;

import com.bgsoftware.common.collections.ints.Int2IntMap;
import com.bgsoftware.common.collections.ints.Int2ObjectMap;
import com.bgsoftware.common.collections.ints.Object2IntMap;
import com.bgsoftware.common.collections.ints.fastutils.FastUtilsInt2IntMap;
import com.bgsoftware.common.collections.ints.fastutils.FastUtilsInt2ObjectMap;
import com.bgsoftware.common.collections.ints.fastutils.FastUtilsObject2IntMap;
import com.bgsoftware.common.collections.longs.Long2LongMap;
import com.bgsoftware.common.collections.longs.Long2ObjectMap;
import com.bgsoftware.common.collections.longs.Object2LongMap;
import com.bgsoftware.common.collections.longs.fastutils.FastUtilsLong2LongMap;
import com.bgsoftware.common.collections.longs.fastutils.FastUtilsLong2ObjectMap;
import com.bgsoftware.common.collections.longs.fastutils.FastUtilsObject2LongMap;
import it.unimi.dsi.fastutil.ints.Int2IntArrayMap;
import it.unimi.dsi.fastutil.ints.Int2IntLinkedOpenHashMap;
import it.unimi.dsi.fastutil.ints.Int2IntOpenHashMap;
import it.unimi.dsi.fastutil.ints.Int2ObjectArrayMap;
import it.unimi.dsi.fastutil.ints.Int2ObjectLinkedOpenHashMap;
import it.unimi.dsi.fastutil.ints.Int2ObjectOpenHashMap;
import it.unimi.dsi.fastutil.longs.Long2LongArrayMap;
import it.unimi.dsi.fastutil.longs.Long2LongLinkedOpenHashMap;
import it.unimi.dsi.fastutil.longs.Long2LongOpenHashMap;
import it.unimi.dsi.fastutil.longs.Long2ObjectArrayMap;
import it.unimi.dsi.fastutil.longs.Long2ObjectLinkedOpenHashMap;
import it.unimi.dsi.fastutil.longs.Long2ObjectOpenHashMap;
import it.unimi.dsi.fastutil.objects.Object2IntArrayMap;
import it.unimi.dsi.fastutil.objects.Object2IntLinkedOpenHashMap;
import it.unimi.dsi.fastutil.objects.Object2IntOpenHashMap;
import it.unimi.dsi.fastutil.objects.Object2LongArrayMap;
import it.unimi.dsi.fastutil.objects.Object2LongLinkedOpenHashMap;
import it.unimi.dsi.fastutil.objects.Object2LongOpenHashMap;
import it.unimi.dsi.fastutil.objects.Object2ObjectArrayMap;
import it.unimi.dsi.fastutil.objects.Object2ObjectLinkedOpenHashMap;
import it.unimi.dsi.fastutil.objects.Object2ObjectOpenHashMap;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class FastUtilsMapsFactory implements MapsFactory {

    public static final FastUtilsMapsFactory INSTANCE = new FastUtilsMapsFactory();

    private FastUtilsMapsFactory() {

    }

    @Override
    public <K, V> Map<K, V> newMap(MapStrategy strategy) {
        switch (strategy) {
            case ARRAY_MAP:
                return new Object2ObjectArrayMap<>();
            case HASH_MAP:
                return new Object2ObjectOpenHashMap<>();
            case LINKED_HASH_MAP:
                return new Object2ObjectLinkedOpenHashMap<>();
            case CONCURRENT_HASH_MAP:
                return new ConcurrentHashMap<>();
        }

        throw new IllegalArgumentException("strategy: " + strategy);
    }

    @Override
    public <V> Int2ObjectMap<V> newInt2ObjectMap(MapStrategy strategy) {
        it.unimi.dsi.fastutil.ints.Int2ObjectMap<V> handle;
        switch (strategy) {
            case ARRAY_MAP:
                handle = new Int2ObjectArrayMap<>();
                break;
            case HASH_MAP:
                handle = new Int2ObjectOpenHashMap<>();
                break;
            case LINKED_HASH_MAP:
                handle = new Int2ObjectLinkedOpenHashMap<>();
                break;
            default:
                throw new IllegalArgumentException("strategy: " + strategy);
        }

        return new FastUtilsInt2ObjectMap<>(handle);
    }

    @Override
    public <K> Object2IntMap<K> newObject2IntMap(MapStrategy strategy) {
        it.unimi.dsi.fastutil.objects.Object2IntMap<K> handle;
        switch (strategy) {
            case ARRAY_MAP:
                handle = new Object2IntArrayMap<>();
                break;
            case HASH_MAP:
                handle = new Object2IntOpenHashMap<>();
                break;
            case LINKED_HASH_MAP:
                handle = new Object2IntLinkedOpenHashMap<>();
                break;
            default:
                throw new IllegalArgumentException("strategy: " + strategy);
        }

        return new FastUtilsObject2IntMap<>(handle);
    }

    @Override
    public Int2IntMap newInt2IntMap(MapStrategy strategy) {
        it.unimi.dsi.fastutil.ints.Int2IntMap handle;
        switch (strategy) {
            case ARRAY_MAP:
                handle = new Int2IntArrayMap();
                break;
            case HASH_MAP:
                handle = new Int2IntOpenHashMap();
                break;
            case LINKED_HASH_MAP:
                handle = new Int2IntLinkedOpenHashMap();
                break;
            default:
                throw new IllegalArgumentException("strategy: " + strategy);
        }

        return new FastUtilsInt2IntMap(handle);
    }

    @Override
    public <V> Long2ObjectMap<V> newLong2ObjectMap(MapStrategy strategy) {
        it.unimi.dsi.fastutil.longs.Long2ObjectMap<V> handle;
        switch (strategy) {
            case ARRAY_MAP:
                handle = new Long2ObjectArrayMap<>();
                break;
            case HASH_MAP:
                handle = new Long2ObjectOpenHashMap<>();
                break;
            case LINKED_HASH_MAP:
                handle = new Long2ObjectLinkedOpenHashMap<>();
                break;
            default:
                throw new IllegalArgumentException("strategy: " + strategy);
        }

        return new FastUtilsLong2ObjectMap<>(handle);
    }

    @Override
    public <K> Object2LongMap<K> newObject2LongMap(MapStrategy strategy) {
        it.unimi.dsi.fastutil.objects.Object2LongMap<K> handle;
        switch (strategy) {
            case ARRAY_MAP:
                handle = new Object2LongArrayMap<>();
                break;
            case HASH_MAP:
                handle = new Object2LongOpenHashMap<>();
                break;
            case LINKED_HASH_MAP:
                handle = new Object2LongLinkedOpenHashMap<>();
                break;
            default:
                throw new IllegalArgumentException("strategy: " + strategy);
        }

        return new FastUtilsObject2LongMap<>(handle);
    }

    @Override
    public Long2LongMap newLong2LongMap(MapStrategy strategy) {
        it.unimi.dsi.fastutil.longs.Long2LongMap handle;
        switch (strategy) {
            case ARRAY_MAP:
                handle = new Long2LongArrayMap();
                break;
            case HASH_MAP:
                handle = new Long2LongOpenHashMap();
                break;
            case LINKED_HASH_MAP:
                handle = new Long2LongLinkedOpenHashMap();
                break;
            default:
                throw new IllegalArgumentException("strategy: " + strategy);
        }

        return new FastUtilsLong2LongMap(handle);
    }
}
