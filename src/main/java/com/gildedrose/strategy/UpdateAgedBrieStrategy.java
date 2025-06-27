package com.gildedrose.strategy;

import com.gildedrose.Item;
import com.gildedrose.service.ItemService;


/**
 * Update logic for <strong>"Aged Brie"</strong>.
 *
 * <p><strong>Business rules:</strong></p>
 * <ul>
 *   <li>"Aged Brie" increases in quality over time.</li>
 *   <li>Quality increases by 1 each day.</li>
 *   <li><code>sellIn &lt; 0</code>, quality increases by 2.</li>
 *   <li>Quality is capped at 50.</li>
 * </ul>
 */
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
