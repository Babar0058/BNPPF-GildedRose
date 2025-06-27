package com.gildedrose.factory;

import com.gildedrose.Item;
import com.gildedrose.strategy.UpdateStrategy;

import java.util.function.Predicate;

public class UpdateStrategyRegistration {
    private final Predicate<Item> condition;
    private final UpdateStrategy strategy;

    public UpdateStrategyRegistration(Predicate<Item> condition, UpdateStrategy strategy) {
        this.condition = condition;
        this.strategy = strategy;
    }

    public boolean matches(Item item) {
        return condition.test(item);
    }

    public UpdateStrategy getStrategy() {
        return strategy;
    }
}
