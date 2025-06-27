# Gilded Rose – Item Business Rules

This document describes the update logic for each supported item type in the Gilded Rose inventory system.

---

## Notes

- All items (except Sulfuras) have a maximum quality of 50.
- Item names must match exactly (case-sensitive) to be associated with their specific update logic.
- Rules are enforced by individual strategy classes using the Strategy Pattern.

---

## Aged Brie

**Update logic:**
- "Aged Brie" increases in quality over time.

**Business rules:**
- Quality increases by 1 each day.
- After `sellIn < 0`, quality increases by 2 per day.
- Quality is capped at 50.

---

## Sulfuras, Hand of Ragnaros

**Update logic:**
- Legendary item with fixed attributes.

**Business rules:**
- Never needs to be sold (`sellIn` does not change).
- Quality never changes.
- Quality is constant at 80.

---

## Backstage Passes to a TAFKAL80ETC Concert

**Update logic:**
- Quality increases as the concert date approaches.

**Business rules:**
- Increases by 1 when `sellIn > 10`.
- Increases by 2 when `5 < sellIn <= 10`.
- Increases by 3 when `0 < sellIn <= 5`.
- Quality drops to 0 after the concert (`sellIn < 0`).
- Quality is capped at 50.

---

## Normal Items (default case)

**Update logic:**
- Standard degradation model.

**Business rules:**
- Quality decreases by 1 each day.
- After `sellIn < 0`, quality decreases by 2 per day.
- Quality never goes below 0.

---

## Conjured Items

**Update logic:**
- Items that degrade twice as fast as normal items.

**Business rules:**
- Quality decreases by 2 each day before expiration.
- Quality decreases by 4 each day after `sellIn < 0`.
- Quality never goes below 0.
