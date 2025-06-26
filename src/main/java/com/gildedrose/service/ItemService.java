package com.gildedrose.service;

import com.gildedrose.Item;

public class ItemService {
    public void decreaseQuality(Item item) {
        if (item.quality > 0) item.quality--;
    }

    public void decreaseSellIn(Item item) {
        item.sellIn--;
    }
}
