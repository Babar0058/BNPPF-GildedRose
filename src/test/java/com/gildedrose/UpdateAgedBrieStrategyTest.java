package com.gildedrose;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class UpdateAgedBrieStrategyTest extends AbstractGildedRoseTest {

    @Test
    void agedBrieItem_beforeSellDate_qualityIncreasesBy1EachDay() {
        Item[] items = new Item[]{new Item("Aged Brie", 2, 0)};
        GildedRose app = simulateDaysPassed(items, 1);
        Item item = app.items[0];

        assertEquals(1, item.sellIn);
        assertEquals(1, item.quality);
    }

    @Test
    void agedBrieItem_beforeSellDate_qualityMaxed() {
        Item[] items = new Item[]{new Item("Aged Brie", 2, 50)};
        GildedRose app = simulateDaysPassed(items, 1);
        Item item = app.items[0];

        assertEquals(1, item.sellIn);
        assertEquals(50, item.quality);
    }

    @Test
    void agedBrieItem_onSellDate_qualityIncreasesBy1EachDay() {
        Item[] items = new Item[]{new Item("Aged Brie", 2, 0)};
        GildedRose app = simulateDaysPassed(items, 2);
        Item item = app.items[0];

        assertEquals(0, item.sellIn);
        assertEquals(2, item.quality);
    }

    @Test
    void agedBrieItem_afterSellDate_qualityIncreasesBy2EachDay() {
        Item[] items = new Item[]{new Item("Aged Brie", 2, 0)};
        GildedRose app = simulateDaysPassed(items, 10);
        Item item = app.items[0];

        assertEquals(-8, item.sellIn);
        assertEquals(18, item.quality);
    }

    @Test
    void agedBrieItem_afterSellDate_qualityNeverExceedsFifty() {
        Item[] items = new Item[]{new Item("Aged Brie", 2, 0)};
        GildedRose app = simulateDaysPassed(items, 30);
        Item item = app.items[0];

        assertEquals(-28, item.sellIn);
        assertEquals(50, item.quality);
    }
}
