package com.gildedrose;

import com.gildedrose.factory.UpdateStrategyFactory;
import com.gildedrose.strategy.UpdateStrategy;

class GildedRose {
    private final UpdateStrategyFactory strategyFactory;
    Item[] items;

    public GildedRose(Item[] items) {
        this.items = items;
        this.strategyFactory = new UpdateStrategyFactory();
    }

    public void updateQuality() {
        for (Item item : items) {
            boolean isSulfuras = item.name.equals("Sulfuras, Hand of Ragnaros");

            if (isSulfuras) {
                // do nothing
            } else {
                UpdateStrategy strategy = strategyFactory.getUpdateStrategyFor(item);
                strategy.update(item);
            }
        }
    }
}
