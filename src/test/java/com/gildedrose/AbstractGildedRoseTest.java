package com.gildedrose;

import com.gildedrose.factory.UpdateStrategyFactory;
import com.gildedrose.service.ItemService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.TestInfo;

public abstract class AbstractGildedRoseTest {

    GildedRose simulateDaysPassed(Item[] items, int days) {
        ItemService itemService = new ItemService();
        GildedRose app = new GildedRose(items, new UpdateStrategyFactory(itemService));
        for (int i = 0; i < days; i++) {
            app.updateQuality();
        }
        return app;
    }

    @BeforeEach
    void setUp(TestInfo testInfo) {
        System.out.println("====== Running: " + testInfo.getDisplayName() + " ======");
    }
}
