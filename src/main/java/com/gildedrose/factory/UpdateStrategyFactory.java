package com.gildedrose.factory;

import com.gildedrose.Item;
import com.gildedrose.service.ItemService;
import com.gildedrose.strategy.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Factory responsible for providing the appropriate {@link UpdateStrategy}
 * implementation for a given item type.
 *
 * <p>
 * The strategy is selected based on the item's name. If no specific strategy is
 * found, a default item strategy is returned.
 * </p>
 *
 * <p>
 * This factory is initialized with an {@link ItemService} instance and delegates
 * it to all applicable strategies that require common item operations.
 * </p>
 */
public class UpdateStrategyFactory {
    private final List<UpdateStrategyRegistration> registrations = new ArrayList<>();
    private final ItemService itemService;

    public UpdateStrategyFactory(ItemService itemService) {
        this.itemService = itemService;

        registrations.add(new UpdateStrategyRegistration(
            item -> item.name.equals("Aged Brie"),
            new UpdateAgedBrieStrategy(itemService)
        ));

        registrations.add(new UpdateStrategyRegistration(
            item -> item.name.equals("Backstage passes to a TAFKAL80ETC concert"),
            new UpdateBackstagePassesStrategy(itemService)
        ));

        registrations.add(new UpdateStrategyRegistration(
            item -> item.name.equals("Sulfuras, Hand of Ragnaros"),
            new UpdateSulfurasStrategy()
        ));

        registrations.add(new UpdateStrategyRegistration(
            item -> item.name.toLowerCase().contains("conjured"),
            new UpdateConjuredStrategy(itemService)
        ));
    }

    public UpdateStrategy getUpdateStrategyFor(Item item) {
        return registrations.stream()
            .filter(reg -> reg.matches(item))
            .map(UpdateStrategyRegistration::getStrategy)
            .findFirst()
            .orElse(new UpdateDefaultItemStrategy(itemService));
    }
}
