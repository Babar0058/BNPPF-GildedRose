package com.gildedrose.strategy;

import com.gildedrose.Item;
import com.gildedrose.service.ItemService;

/**
 * Update logic for <strong>normal items</strong> (default case).
 *
 * <p><strong>Business rules:</strong></p>
 * <ul>
 *   <li>Quality decreases by 1 each day.</li>
 *   <li>After <code>sellIn &lt; 0</code>, quality decreases by 2 per day.</li>
 *   <li>Quality never goes below 0.</li>
 * </ul>
 */
public class UpdateDefaultItemStrategy implements UpdateStrategy {
    private final ItemService itemService;

    public UpdateDefaultItemStrategy(ItemService itemService) {
        this.itemService = itemService;
    }

    @Override
    public void update(Item item) {
        itemService.decreaseQuality(item);
        itemService.decreaseSellIn(item);
        if (item.sellIn < 0) {
            itemService.decreaseQuality(item);
        }
    }
}
