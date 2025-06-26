package com.gildedrose;

import com.gildedrose.factory.UpdateStrategyFactory;
import com.gildedrose.strategy.UpdateStrategy;

class GildedRose {
    private final UpdateStrategyFactory strategyFactory;
    Item[] items;

    public GildedRose(Item[] items, UpdateStrategyFactory strategyFactory) {
        this.items = items;
        this.strategyFactory = strategyFactory;
    }

    public void updateQuality() {
        for (Item item : items) {
            UpdateStrategy strategy = strategyFactory.getUpdateStrategyFor(item);
            strategy.update(item);
        }
    }
}
