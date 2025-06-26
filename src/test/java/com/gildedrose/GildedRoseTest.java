package com.gildedrose;

import com.gildedrose.factory.UpdateStrategyFactory;
import com.gildedrose.service.ItemService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInfo;

import static org.junit.jupiter.api.Assertions.assertEquals;

class GildedRoseTest {
    @BeforeEach
    void setUp(TestInfo testInfo) {
        System.out.println("====== Running: " + testInfo.getDisplayName() + " ======");
    }

    private GildedRose simulateDaysPassed(Item[] items, int days) {
        ItemService itemService = new ItemService();
        GildedRose app = new GildedRose(items, new UpdateStrategyFactory(itemService));
        for (int i = 0; i < days; i++) {
            app.updateQuality();
        }
        return app;
    }

    @Test
    void shouldNotChangeItemNameAfterUpdate() {
        Item[] items = new Item[]{new Item("+5 Dexterity Vest", 0, 0)};
        GildedRose app = simulateDaysPassed(items, 1);
        assertEquals("+5 Dexterity Vest", app.items[0].name, "Item name should remain unchanged");
    }

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
