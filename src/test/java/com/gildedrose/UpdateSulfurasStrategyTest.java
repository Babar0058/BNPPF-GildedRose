package com.gildedrose;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class UpdateSulfurasStrategyTest extends AbstractGildedRoseTest {

    @Test
    void sulfurasItem_afterSellDate_SellInAndQualityDoNotUpdate() {
        Item[] items = new Item[]{new Item("Sulfuras, Hand of Ragnaros", 0, 80)};
        GildedRose app = simulateDaysPassed(items, 5);
        Item item = app.items[0];

        assertEquals(0, item.sellIn);
        assertEquals(80, item.quality);
    }

    @Test
    void sulfurasItem_sellInAndQualityNeverUpdate() {
        Item[] items = new Item[]{new Item("Sulfuras, Hand of Ragnaros", 10, 80)};
        GildedRose app = simulateDaysPassed(items, 15);
        Item item = app.items[0];

        assertEquals(10, item.sellIn);
        assertEquals(80, item.quality);
    }
}
