package com.gildedrose.factory;

import com.gildedrose.Item;
import com.gildedrose.strategy.UpdateAgedBrieStrategy;
import com.gildedrose.strategy.UpdateBackstagePassesStrategy;
import com.gildedrose.strategy.UpdateDefaultItemStrategy;
import com.gildedrose.strategy.UpdateStrategy;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class UpdateStrategyFactory {
    private final Map<String, UpdateStrategy> strategyMap;

    public UpdateStrategyFactory() {
        Map<String, UpdateStrategy> map = new HashMap<>();
        map.put("Aged Brie", new UpdateAgedBrieStrategy());
        map.put("Backstage passes to a TAFKAL80ETC concert", new UpdateBackstagePassesStrategy());
        strategyMap = Collections.unmodifiableMap(map);
    }

    public UpdateStrategy getUpdateStrategyFor(Item item) {
        return strategyMap.getOrDefault(item.name, new UpdateDefaultItemStrategy());
    }
}
