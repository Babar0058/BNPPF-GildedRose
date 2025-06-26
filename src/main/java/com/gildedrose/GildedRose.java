package com.gildedrose;

import com.gildedrose.factory.UpdateStrategyFactory;
import com.gildedrose.strategy.UpdateStrategy;

class GildedRose {
    Item[] items;

    public GildedRose(Item[] items) {
        this.items = items;
    }

    public void updateQuality() {
        for (Item item : items) {
            boolean isBackstagePasses = item.name.equals("Backstage passes to a TAFKAL80ETC concert");
            boolean isSulfuras = item.name.equals("Sulfuras, Hand of Ragnaros");

            if (isBackstagePasses) {
                processBackstagePasses(item);
            } else if (isSulfuras) {
                // do nothing
            } else {
                UpdateStrategy strategy = UpdateStrategyFactory.getUpdateStrategyFor(item);
                strategy.update(item);
            }
        }
    }

    private void processBackstagePasses(Item item) {
        if (item.quality < 50) {
            item.quality = item.quality + 1;

            if (item.sellIn < 11) {
                if (item.quality < 50) {
                    item.quality = item.quality + 1;
                }
            }

            if (item.sellIn < 6) {
                if (item.quality < 50) {
                    item.quality = item.quality + 1;
                }
            }
        }

        item.sellIn = item.sellIn - 1;

        if (item.sellIn < 0) {
            item.quality = 0;
        }
    }

}
