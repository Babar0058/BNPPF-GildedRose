# Gilded Rose - Strategy Pattern solution

## Development Approach

As a starter, I decided to redo the entire test suite because i didn't like the way it was done through their tool.

Branch:
- **CodeSimplification** : Clean and simplify the existing logic without changing behavior (remove duplication, flatten conditions).
- **Refacto(/StrategyPatternImpl, /ItemServiceImpl)** : Introduce the Strategy Pattern to separate concerns and improve maintainability. Each item type is handled by its own strategy class, injected via a factory.
- **Feature/ConjuredItemImplementation** : Add support for the `"Conjured"` item type, which degrades in quality twice as fast as normal items.
- (Bonus) **Refacto/Documentation** : Javadoc & documentation about the project.
- (Bonus) **Refacto/TestRelocation** : Relocation of the tests.
- **Hotfix/BugConjuredItem** : A bug was found and needed to be quickly fix.
- **Refacto/HotfixConjuredByStrategyRegistry** : Use a predicate-based registry to resolve which strategy should be used.

## Core Components

### 1. `GildedRose`
The main entry point for updating items. It delegates the update logic to the appropriate strategy using `UpdateStrategyFactory`.

- Method: `updateQuality()`
- For each item:
    - Gets the correct `UpdateStrategy` from the factory
    - Calls `strategy.update(item)`

---

### 2. `UpdateStrategyFactory`
Responsible for returning the right strategy implementation based on the item's name.

- Maps item names to strategies like:
    - `"Aged Brie"` → `UpdateAgedBrieStrategy`
    - `"Backstage passes"` → `UpdateBackstagePassesStrategy`
    - `"Sulfuras"` → `UpdateSulfurasStrategy`
    - `"Conjured"` → `UpdateConjuredStrategy`
- Injects a shared `ItemService` instance into each strategy (Stateless class, only method & no field)

---

### 3. `UpdateStrategy` (Interface)
Defines a single method:

```java
void update(Item item);
```
Each item type has a corresponding implementation that encapsulates its specific update behavior.
Examples:
- `UpdateAgedBrieStrategy`
- `UpdateBackstagePassesStrategy`
- `UpdateSulfurasStrategy`
- `UpdateConjuredStrategy`
- `UpdateDefaultItemStrategy` (used when no match is found)

---

### 4. `ItemService`
A utility service (Stateless class) that provides reusable operations on `Item` instances:
- `increaseQuality(Item item)`
- `decreaseQuality(Item item)`
- `resetQuality(Item item)`
- `decreaseSellIn(Item item)`

This ensures consistent behavior across strategies and avoids duplicated logic.

---

## How It Works – Step by Step

1. The `GildedRose` class receives an array of items and a preconfigured `UpdateStrategyFactory` (injected)
2. For each item:
    - It calls `UpdateStrategyFactory.getUpdateStrategyFor(item)`
    - The factory returns the appropriate `UpdateStrategy` based on the item's name.
3. The selected strategy calls the necessary methods from `ItemService` to update the item according to business rules.
4. The item's `sellIn` and `quality` are updated accordingly.

---
## Extending the System

To add a new item type:
1. Create a new class implementing `UpdateStrategy`.
2. Use `ItemService` to apply standard logic.
3. Register the strategy in `UpdateStrategyFactory`.

Example:

```
map.put("New Item", new UpdateNewItemStrategy(itemService));
```

---
## Business logic

Detailed rules and descriptions of each item type: [Business Logic – Item Rules](docs/BL-items-rules.md)
