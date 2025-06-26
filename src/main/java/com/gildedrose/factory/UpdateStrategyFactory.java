package com.gildedrose.factory;

import com.gildedrose.Item;
import com.gildedrose.strategy.UpdateAgedBrieStrategy;
import com.gildedrose.strategy.UpdateDefaultItemStrategy;
import com.gildedrose.strategy.UpdateStrategy;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class UpdateStrategyFactory {
    private static final Map<String, UpdateStrategy> strategyMap;

    static {
        Map<String, UpdateStrategy> map = new HashMap<>();
        map.put("Aged Brie", new UpdateAgedBrieStrategy());

        strategyMap = Collections.unmodifiableMap(map);
    }

    public static UpdateStrategy getUpdateStrategyFor(Item item) {
        return strategyMap.getOrDefault(item.name, new UpdateDefaultItemStrategy());
    }
}
