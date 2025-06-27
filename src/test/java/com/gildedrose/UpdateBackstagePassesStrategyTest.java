package com.gildedrose;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class UpdateBackstagePassesStrategyTest extends AbstractGildedRoseTest {

    @Test
    void backstagePassesItem_beforeSellDate_increaseBy1WhenMoreThan10Days() {
        Item[] items = new Item[]{new Item("Backstage passes to a TAFKAL80ETC concert", 15, 20)};
        GildedRose app = simulateDaysPassed(items, 1);
        Item item = app.items[0];

        assertEquals(14, item.sellIn);
        assertEquals(21, item.quality);
    }

    @Test
    void backstagePassesItem_beforeSellDate_qualityIncreaseBy2When10To6Days() {
        Item[] items = new Item[]{new Item("Backstage passes to a TAFKAL80ETC concert", 15, 20)};
        GildedRose app = simulateDaysPassed(items, 7); // 9 days left
        Item item = app.items[0];

        assertEquals(8, item.sellIn);
        assertEquals(29, item.quality); // 20 + 5 (5 normal days) + 4 (2 double quality days)
    }

    @Test
    void backstagePassesItem_beforeSellDate_qualityIncreaseBy3When5DaysOrLess() {
        Item[] items = new Item[]{new Item("Backstage passes to a TAFKAL80ETC concert", 15, 20)};
        GildedRose app = simulateDaysPassed(items, 13);
        Item item = app.items[0];

        assertEquals(2, item.sellIn);
        assertEquals(44, item.quality); // 20 + 5 (5 normal days) + 10 (5 double quality days) + 9 (3 triple quality days)
    }

    @Test
    void backstagePassesItem_afterSellDate_qualityDropToZero() {
        Item[] items = new Item[]{new Item("Backstage passes to a TAFKAL80ETC concert", 15, 20)};
        GildedRose app = simulateDaysPassed(items, 16);
        Item item = app.items[0];

        assertEquals(-1, item.sellIn);
        assertEquals(0, item.quality);
    }

    @Test
    void backstagePassesItem_onSellDate_qualityNeverExceedsFifty() {
        Item[] items = new Item[]{new Item("Backstage passes to a TAFKAL80ETC concert", 5, 49)};
        GildedRose app = simulateDaysPassed(items, 5);
        Item item = app.items[0];

        assertEquals(0, item.sellIn);
        assertEquals(50, item.quality);
    }

}
