package com.gildedrose.resolver;

import com.gildedrose.Item;
import com.gildedrose.service.ItemService;
import com.gildedrose.strategy.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Resolver responsible for selecting the appropriate {@link UpdateStrategy}
 * implementation for a given {@link Item} instance.
 *
 * <p>This implementation uses a list of {@link UpdateStrategyRegistration} entries,
 * each of which defines a predicate-condition and the corresponding strategy to apply.
 * This allows for flexible and extensible matching logic
 *
 * <p>If no strategy matches the item, a {@link UpdateDefaultItemStrategy} is used
 * as the fallback.</p>
 *
 * <p>This resolver is initialized with an {@link ItemService}, which is injected into
 * each applicable strategy that requires shared item manipulation logic.</p>
 */
public class UpdateStrategyResolver {
    private final List<UpdateStrategyRegistration> registrations = new ArrayList<>();
    private final ItemService itemService;

    public UpdateStrategyResolver(ItemService itemService) {
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
