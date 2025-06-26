package com.gildedrose.strategy;

import com.gildedrose.Item;
import com.gildedrose.service.ItemService;

public class UpdateDefaultItemStrategy implements UpdateStrategy {
    private final ItemService itemService;

    public UpdateDefaultItemStrategy(ItemService itemService) {
        this.itemService = itemService;
    }

    @Override
    public void update(Item item) {
        itemService.decreaseQuality(item);
        itemService.decreaseSellIn(item);
        if (item.sellIn < 0) {
            itemService.decreaseQuality(item);
        }
    }
}
