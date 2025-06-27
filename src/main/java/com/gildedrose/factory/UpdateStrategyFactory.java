package com.gildedrose.factory;

import com.gildedrose.Item;
import com.gildedrose.service.ItemService;
import com.gildedrose.strategy.*;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * Factory responsible for providing the appropriate {@link UpdateStrategy}
 * implementation for a given item type.
 *
 * <p>
 * The strategy is selected based on the item's name. If no specific strategy is
 * found, a default item strategy is returned.
 * </p>
 *
 * <p>
 * This factory is initialized with an {@link ItemService} instance and delegates
 * it to all applicable strategies that require common item operations.
 * </p>
 */
public class UpdateStrategyFactory {
    private final Map<String, UpdateStrategy> strategyMap;
    private final ItemService itemService;

    public UpdateStrategyFactory(ItemService itemService) {
        this.itemService = itemService;

        Map<String, UpdateStrategy> map = new HashMap<>();
        map.put("Aged Brie", new UpdateAgedBrieStrategy(itemService));
        map.put("Backstage passes to a TAFKAL80ETC concert", new UpdateBackstagePassesStrategy(itemService));
        map.put("Sulfuras, Hand of Ragnaros", new UpdateSulfurasStrategy());
        map.put("Conjured", new UpdateConjuredStrategy(itemService));
        strategyMap = Collections.unmodifiableMap(map);
    }

    public UpdateStrategy getUpdateStrategyFor(Item item) {
        return strategyMap.getOrDefault(item.name, new UpdateDefaultItemStrategy(itemService));
    }
}
