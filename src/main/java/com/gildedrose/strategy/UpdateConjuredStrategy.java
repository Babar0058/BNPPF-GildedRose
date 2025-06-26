package com.gildedrose.strategy;

import com.gildedrose.Item;
import com.gildedrose.service.ItemService;

public class UpdateConjuredStrategy implements UpdateStrategy {
    private final ItemService itemService;

    public UpdateConjuredStrategy(ItemService itemService) {
        this.itemService = itemService;
    }

    @Override
    public void update(Item item) {
        itemService.decreaseSellIn(item);
        itemService.decreaseQuality(item);
        itemService.decreaseQuality(item);

        if (item.sellIn < 0) {
            itemService.decreaseQuality(item);
            itemService.decreaseQuality(item);
        }
    }
}
