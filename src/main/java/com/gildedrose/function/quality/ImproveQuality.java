package com.gildedrose.function.quality;

import com.gildedrose.model.Item;


/**
 * Increase the quality of an {@link Item}.
 * 
 * @author kavert
 */
class ImproveQuality implements QualityOperator {

    private final int amount;
    private final int limit;

    /**
     * Amount of quality to add to the {@link Item}.
     * 
     * @param amount to increase the quality with.
     * @param limit maximal allowed quality.
     */
    public ImproveQuality(int amount, int limit) {
        this.amount = Math.abs(amount);
        this.limit = limit;
    }

    /**
     * Increase the quality of the provided {@link Item} with the set {@code amount}.
     * The quality will never increase over the set {@code limit}.
     * 
     * @param item The {@link Item} for which to improve the quality.
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
            newQuality = item.quality + (amount * 2);
        } else {
            newQuality = item.quality + amount;
        }

        if (newQuality > limit) {
            newQuality = limit;
        }

        item.quality = newQuality;
        return item;
    }

}
