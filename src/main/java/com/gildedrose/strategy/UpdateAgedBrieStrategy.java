package com.gildedrose.strategy;

import com.gildedrose.Item;
import com.gildedrose.service.ItemService;

public class UpdateAgedBrieStrategy implements UpdateStrategy {
    private final ItemService itemService;

    public UpdateAgedBrieStrategy(ItemService itemService) {
        this.itemService = itemService;
    }

    @Override
    public void update(Item item) {
        itemService.increaseQuality(item);
        itemService.decreaseSellIn(item);
        if (item.sellIn < 0) {
            itemService.increaseQuality(item);
        }
    }
}
