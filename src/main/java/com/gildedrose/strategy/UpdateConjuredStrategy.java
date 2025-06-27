package com.gildedrose.strategy;

import com.gildedrose.Item;
import com.gildedrose.service.ItemService;

/**
 * Update logic for <strong>"Conjured" items</strong>.
 *
 * <p><strong>Business rules:</strong></p>
 * <ul>
 *   <li>Conjured items degrade in quality twice as fast as normal items.</li>
 *   <li>Decreases by 2 each day before expiration.</li>
 *   <li>Decreases by 4 each day after <code>sellIn &lt; 0</code>.</li>
 *   <li>Quality never goes below 0.</li>
 * </ul>
 */
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
