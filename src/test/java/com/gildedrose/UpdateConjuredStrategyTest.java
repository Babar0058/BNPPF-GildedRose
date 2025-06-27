package com.gildedrose;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class UpdateConjuredStrategyTest extends AbstractGildedRoseTest {

    @Test
    void conjuredItem_beforeSellDate_qualityDecreaseBy2() {
        Item[] items = new Item[]{new Item("Conjured", 5, 6)};
        GildedRose app = simulateDaysPassed(items, 2);
        Item item = app.items[0];

        assertEquals(3, item.sellIn);
        assertEquals(2, item.quality);
    }

    @Test
    void conjuredItem_afterSellDate_qualityDecreaseBy4() {
        Item[] items = new Item[]{new Item("Conjured", 1, 10)};
        GildedRose app = simulateDaysPassed(items, 2);
        Item item = app.items[0];

        assertEquals(-1, item.sellIn);
        assertEquals(4, item.quality); // 10 - 2 (1 normal day) - 4 (1 normal day after sellIn)
    }

    @Test
    void conjuredItem_afterSellDate_qualityShouldNotBeNegative() {
        Item[] items = new Item[]{new Item("Conjured", 1, 10)};
        GildedRose app = simulateDaysPassed(items, 5);
        Item item = app.items[0];

        assertEquals(-4, item.sellIn);
        assertEquals(0, item.quality);
    }
}
