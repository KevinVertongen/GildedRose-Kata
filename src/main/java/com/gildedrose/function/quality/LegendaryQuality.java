package com.gildedrose.function.quality;

import com.gildedrose.model.Item;


/**
 * The quality of legendary {@link Item}s never alters.
 * 
 * @author kavert
 */
class LegendaryQuality implements QualityOperator {

    private static final String ILLEGAL_LEGENDARY_ITEM_QUALITY = "Invalid legedary item quality '%d' for item with name '%s'.";

    private final int limit;

    /**
     * The constant quality of a legendary {@link Item}.
     * 
     * @param limit single allowed quality of a Legendary {@link Item}.  
     */
    public LegendaryQuality(int limit) {
        this.limit = limit;
    }

    /**
     * A {@link QualityOperator} that always returns its input {@link Item}.
     * 
     * @param item The legendary {@link Item}.
     * @return The same {@link Item}, unaltered.
     * @throws IllegalStateException if the {@link Item} quality is invalid.
     * @see QualityOperator
     */
    @Override
    public Item apply(Item item) {
        if (item == null) {
            return item;
        }

        if (item.quality != limit) {
            throw new IllegalStateException(String.format(ILLEGAL_LEGENDARY_ITEM_QUALITY, item.quality, item.name));
        }

        return item;
    }

}
