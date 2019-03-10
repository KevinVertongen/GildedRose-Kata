package com.gildedrose.function.quality;

import com.gildedrose.model.Item;


/**
 * Decrease the quality of an {@link Item}.
 * 
 * @author kavert
 */
class DegradeQuality implements QualityOperator {

    private final int amount;
    private final int limit;

    /**
     * Amount of quality to subtract from the {@link Item}.
     * 
     * @param amount to decrease the quality with.
     * @param limit minimal allowed quality.
     */
    public DegradeQuality(int amount, int limit) {
        this.amount = Math.abs(amount);
        this.limit = limit;
    }

    /**
     * Decrease the quality of the provided {@link Item} with the set {@code amount}.
     * Once the sell by date has passed, Quality degrades twice as fast.
     * The quality will never decrease below the set {@code limit}.
     * 
     * @param item The {@link Item} for which to degrade the quality.
     * @return The modified {@link Item}.
     * @see QualityOperator
     */
    @Override
    public Item apply(Item item) {
        if (item == null) {
            return item;
        }

        int newQuality;
        if (item.sellIn <= 0) {
            newQuality = item.quality - (amount * 2);
        } else {
            newQuality = item.quality - amount;
        }

        if (newQuality < limit) {
            newQuality = limit;
        }

        item.quality = newQuality;
        return item;
    }

}
