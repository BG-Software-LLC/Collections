package com.bgsoftware.common.collections.internal.maps;

import com.bgsoftware.common.collections.ints.Int2IntMap;
import com.bgsoftware.common.collections.ints.Int2ObjectMap;
import com.bgsoftware.common.collections.ints.Object2IntMap;
import com.bgsoftware.common.collections.longs.Long2LongMap;
import com.bgsoftware.common.collections.longs.Long2ObjectMap;
import com.bgsoftware.common.collections.longs.Object2LongMap;

import java.util.Map;

public interface MapsFactory {

    <K, V> Map<K, V> newMap(MapStrategy strategy);

    <V> Int2ObjectMap<V> newInt2ObjectMap(MapStrategy strategy);

    <K> Object2IntMap<K> newObject2IntMap(MapStrategy strategy);

    Int2IntMap newInt2IntMap(MapStrategy strategy);

    <V> Long2ObjectMap<V> newLong2ObjectMap(MapStrategy strategy);

    <K> Object2LongMap<K> newObject2LongMap(MapStrategy strategy);

    Long2LongMap newLong2LongMap(MapStrategy strategy);

}
