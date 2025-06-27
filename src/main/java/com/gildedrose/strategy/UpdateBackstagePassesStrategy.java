package com.gildedrose.strategy;

import com.gildedrose.Item;
import com.gildedrose.service.ItemService;

/**
 * Update logic for <strong>"Backstage passes to a TAFKAL80ETC concert"</strong>.
 *
 * <p><strong>Business rules:</strong></p>
 * <ul>
 *   <li>Quality increases as <code>sellIn</code> approaches.</li>
 *   <li>+1 when <code>sellIn &gt; 10</code>.</li>
 *   <li>+2 when <code>5 &lt; sellIn &le; 10</code>.</li>
 *   <li>+3 when <code>0 &lt; sellIn &le; 5</code>.</li>
 *   <li>Quality drops to 0 after the concert (<code>sellIn &lt; 0</code>).</li>
 *   <li>Quality is capped at 50.</li>
 * </ul>
 */
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
            // As we decrease the SellIn first, we don't need the =
            if (item.sellIn < 10) itemService.increaseQuality(item);
            if (item.sellIn < 5) itemService.increaseQuality(item);
        }
    }
}
