package com.gildedrose;

import com.gildedrose.resolver.UpdateStrategyResolver;
import com.gildedrose.strategy.UpdateStrategy;

class GildedRose {
    private final UpdateStrategyResolver strategyResolver;
    Item[] items;

    public GildedRose(Item[] items, UpdateStrategyResolver strategyResolver) {
        this.items = items;
        this.strategyResolver = strategyResolver;
    }

    public void updateQuality() {
        for (Item item : items) {
            UpdateStrategy strategy = strategyResolver.getUpdateStrategyFor(item);
            strategy.update(item);
        }
    }
}
