package com.gildedrose;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class UpdateDefaultItemStrategyTest extends AbstractGildedRoseTest {
    @Test
    void defaultItem_onSellDate_qualityDecreasesBy1EachDay() {
        Item[] items = new Item[]{new Item("+5 Dexterity Vest", 10, 20)};
        GildedRose app = simulateDaysPassed(items, 10);
        Item item = app.items[0];

        assertEquals("+5 Dexterity Vest", item.name);
        assertEquals(0, item.sellIn);
        assertEquals(10, item.quality);
    }

    @Test
    void defaultItem_afterSellDate_qualityDecreasesBy2EachDay() {
        Item[] items = new Item[]{new Item("+5 Dexterity Vest", 10, 20)};
        GildedRose app = simulateDaysPassed(items, 12);
        Item item = app.items[0];

        assertEquals(-2, item.sellIn);
        assertEquals(6, item.quality);
    }

    @Test
    void defaultItem_afterSellDate_qualityShouldNotBeNegative() {
        Item[] items = new Item[]{new Item("+5 Dexterity Vest", 10, 20)};
        GildedRose app = simulateDaysPassed(items, 25);
        Item item = app.items[0];

        assertEquals(-15, item.sellIn);
        assertEquals(0, item.quality);
    }
}
