package com.gildedrose;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class GildedRoseTest extends AbstractGildedRoseTest {
    private Item[] getItems() {
        return new Item[]{
            new Item("+5 Dexterity Vest", 10, 20), //
            new Item("Aged Brie", 2, 0), //
            new Item("Elixir of the Mongoose", 5, 7), //
            new Item("Sulfuras, Hand of Ragnaros", 0, 80), //
            new Item("Sulfuras, Hand of Ragnaros", -1, 80),
            new Item("Backstage passes to a TAFKAL80ETC concert", 15, 20),
            new Item("Backstage passes to a TAFKAL80ETC concert", 10, 49),
            new Item("Backstage passes to a TAFKAL80ETC concert", 5, 49),
            new Item("Conjured Mana Cake", 3, 6)};
    }

    private Item getDefaultItem(Item[] items) {
        return items[0];
    }

    private Item getAgedBrie(Item[] items) {
        return items[1];
    }

    private Item getElixir(Item[] items) {
        return items[2];
    }

    private Item getSulfurasWithSellIn0(Item[] items) {
        return items[3];
    }

    private Item getSulfurasExpired(Item[] items) {
        return items[4];
    }

    private Item getBackstagePasses15Days(Item[] items) {
        return items[5];
    }

    private Item getBackstagePasses10Days(Item[] items) {
        return items[6];
    }

    private Item getBackstagePasses5Days(Item[] items) {
        return items[7];
    }

    private Item getConjuredManaCakeItem(Item[] items) {
        return items[8];
    }

    @Test
    void TestBasketOfItems3DaysIteration() {
        Item[] items = getItems();
        simulateDaysPassed(items, 3);

        assertEquals(7, getDefaultItem(items).sellIn);
        assertEquals(17, getDefaultItem(items).quality);

        assertEquals(-1, getAgedBrie(items).sellIn);
        assertEquals(4, getAgedBrie(items).quality);

        assertEquals(2, getElixir(items).sellIn);
        assertEquals(4, getElixir(items).quality);

        assertEquals(0, getSulfurasWithSellIn0(items).sellIn);
        assertEquals(80, getSulfurasWithSellIn0(items).quality);

        assertEquals(-1, getSulfurasExpired(items).sellIn);
        assertEquals(80, getSulfurasExpired(items).quality);

        assertEquals(12, getBackstagePasses15Days(items).sellIn);
        assertEquals(23, getBackstagePasses15Days(items).quality);

        assertEquals(7, getBackstagePasses10Days(items).sellIn);
        assertEquals(50, getBackstagePasses10Days(items).quality);

        assertEquals(2, getBackstagePasses5Days(items).sellIn);
        assertEquals(50, getBackstagePasses5Days(items).quality);

        assertEquals(0, getConjuredManaCakeItem(items).sellIn);
        assertEquals(0, getConjuredManaCakeItem(items).quality);
    }

    @Test
    void TestBasketOfItems5DaysIteration() {
        Item[] items = getItems();
        simulateDaysPassed(items, 5);

        assertEquals(5, getDefaultItem(items).sellIn);
        assertEquals(15, getDefaultItem(items).quality);

        assertEquals(-3, getAgedBrie(items).sellIn);
        assertEquals(8, getAgedBrie(items).quality);

        assertEquals(0, getElixir(items).sellIn);
        assertEquals(2, getElixir(items).quality);

        assertEquals(0, getSulfurasWithSellIn0(items).sellIn);
        assertEquals(80, getSulfurasWithSellIn0(items).quality);

        assertEquals(-1, getSulfurasExpired(items).sellIn);
        assertEquals(80, getSulfurasExpired(items).quality);

        assertEquals(10, getBackstagePasses15Days(items).sellIn);
        assertEquals(25, getBackstagePasses15Days(items).quality);

        assertEquals(5, getBackstagePasses10Days(items).sellIn);
        assertEquals(50, getBackstagePasses10Days(items).quality);

        assertEquals(0, getBackstagePasses5Days(items).sellIn);
        assertEquals(50, getBackstagePasses5Days(items).quality);

        assertEquals(-2, getConjuredManaCakeItem(items).sellIn);
        assertEquals(0, getConjuredManaCakeItem(items).quality);
    }

    @Test
    void TestBasketOfItems10DaysIteration() {
        Item[] items = getItems();
        simulateDaysPassed(items, 10);

        assertEquals(0, getDefaultItem(items).sellIn);
        assertEquals(10, getDefaultItem(items).quality);

        assertEquals(-8, getAgedBrie(items).sellIn);
        assertEquals(18, getAgedBrie(items).quality);

        assertEquals(-5, getElixir(items).sellIn);
        assertEquals(0, getElixir(items).quality);

        assertEquals(0, getSulfurasWithSellIn0(items).sellIn);
        assertEquals(80, getSulfurasWithSellIn0(items).quality);

        assertEquals(-1, getSulfurasExpired(items).sellIn);
        assertEquals(80, getSulfurasExpired(items).quality);

        assertEquals(5, getBackstagePasses15Days(items).sellIn);
        assertEquals(35, getBackstagePasses15Days(items).quality);

        assertEquals(0, getBackstagePasses10Days(items).sellIn);
        assertEquals(50, getBackstagePasses10Days(items).quality);

        assertEquals(-5, getBackstagePasses5Days(items).sellIn);
        assertEquals(0, getBackstagePasses5Days(items).quality);

        assertEquals(-7, getConjuredManaCakeItem(items).sellIn);
        assertEquals(0, getConjuredManaCakeItem(items).quality);
    }

    @Test
    void TestBasketOfItems15DaysIteration() {
        Item[] items = getItems();
        simulateDaysPassed(items, 15);

        assertEquals(-5, getDefaultItem(items).sellIn);
        assertEquals(0, getDefaultItem(items).quality);

        assertEquals(-13, getAgedBrie(items).sellIn);
        assertEquals(28, getAgedBrie(items).quality);

        assertEquals(-10, getElixir(items).sellIn);
        assertEquals(0, getElixir(items).quality);

        assertEquals(0, getSulfurasWithSellIn0(items).sellIn);
        assertEquals(80, getSulfurasWithSellIn0(items).quality);

        assertEquals(-1, getSulfurasExpired(items).sellIn);
        assertEquals(80, getSulfurasExpired(items).quality);

        assertEquals(0, getBackstagePasses15Days(items).sellIn);
        assertEquals(50, getBackstagePasses15Days(items).quality);

        assertEquals(-5, getBackstagePasses10Days(items).sellIn);
        assertEquals(0, getBackstagePasses10Days(items).quality);

        assertEquals(-10, getBackstagePasses5Days(items).sellIn);
        assertEquals(0, getBackstagePasses5Days(items).quality);

        assertEquals(-12, getConjuredManaCakeItem(items).sellIn);
        assertEquals(0, getConjuredManaCakeItem(items).quality);
    }

    @Test
    void TestBasketOfItems30DaysIteration() {
        Item[] items = getItems();
        simulateDaysPassed(items, 30);

        assertEquals(-20, getDefaultItem(items).sellIn);
        assertEquals(0, getDefaultItem(items).quality);

        assertEquals(-28, getAgedBrie(items).sellIn);
        assertEquals(50, getAgedBrie(items).quality);

        assertEquals(-25, getElixir(items).sellIn);
        assertEquals(0, getElixir(items).quality);

        assertEquals(0, getSulfurasWithSellIn0(items).sellIn);
        assertEquals(80, getSulfurasWithSellIn0(items).quality);

        assertEquals(-1, getSulfurasExpired(items).sellIn);
        assertEquals(80, getSulfurasExpired(items).quality);

        assertEquals(-15, getBackstagePasses15Days(items).sellIn);
        assertEquals(0, getBackstagePasses15Days(items).quality);

        assertEquals(-20, getBackstagePasses10Days(items).sellIn);
        assertEquals(0, getBackstagePasses10Days(items).quality);

        assertEquals(-25, getBackstagePasses5Days(items).sellIn);
        assertEquals(0, getBackstagePasses5Days(items).quality);

        assertEquals(-27, getConjuredManaCakeItem(items).sellIn);
        assertEquals(0, getConjuredManaCakeItem(items).quality);
    }

}
