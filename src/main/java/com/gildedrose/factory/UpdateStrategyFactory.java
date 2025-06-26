package com.gildedrose.factory;

import com.gildedrose.Item;
import com.gildedrose.service.ItemService;
import com.gildedrose.strategy.*;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class UpdateStrategyFactory {
    private final Map<String, UpdateStrategy> strategyMap;
    private final ItemService itemService;

    public UpdateStrategyFactory(ItemService itemService) {
        this.itemService = itemService;

        Map<String, UpdateStrategy> map = new HashMap<>();
        map.put("Aged Brie", new UpdateAgedBrieStrategy(itemService));
        map.put("Backstage passes to a TAFKAL80ETC concert", new UpdateBackstagePassesStrategy(itemService));
        map.put("Sulfuras, Hand of Ragnaros", new UpdateSulfurasStrategy());
        strategyMap = Collections.unmodifiableMap(map);
    }

    public UpdateStrategy getUpdateStrategyFor(Item item) {
        return strategyMap.getOrDefault(item.name, new UpdateDefaultItemStrategy(itemService));
    }
}
