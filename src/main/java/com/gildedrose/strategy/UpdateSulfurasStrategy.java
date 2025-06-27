package com.gildedrose.strategy;

import com.gildedrose.Item;

/**
 * Update logic for <strong>"Sulfuras, Hand of Ragnaros"</strong>.
 *
 * <p><strong>Business rules:</strong></p>
 * <ul>
 *   <li>SellIn does not decrease.</li>
 *   <li>Quality never changes.</li>
 *   <li>Quality is constant at 80.</li>
 * </ul>
 */
public class UpdateSulfurasStrategy implements UpdateStrategy {
    @Override
    public void update(Item item) {
        // Legendary item, do nothing
    }
}
