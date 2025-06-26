package com.gildedrose.strategy;

import com.gildedrose.Item;
import com.gildedrose.service.ItemService;

public class UpdateBackstagePassesStrategy implements UpdateStrategy {
    private final ItemService itemService;

    public UpdateBackstagePassesStrategy(ItemService itemService) {
        this.itemService = itemService;
    }

    @Override
    public void update(Item item) {
        itemService.decreaseSellIn(item);

        if (item.sellIn < 0) {
            itemService.resetQuality(item);
        } else {
            itemService.increaseQuality(item);
            if (item.sellIn < 10) itemService.increaseQuality(item);
            if (item.sellIn < 5) itemService.increaseQuality(item);
        }
    }
}
