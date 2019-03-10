package com.gildedrose.function.quality;

import com.gildedrose.config.quality.QualityConfiguration;
import com.gildedrose.model.Inventory;
import com.gildedrose.model.Item;


/**
 * Create instances of an {@link QualityOperator} to modify an {@link Item}.
 * 
 * @author kavert
 */
public class QualityOperatorFactory {

    /**
     * Create an instance of a concrete {@link QualityOperator} that applies to the given {@link Item}.
     * 
     * @param configuration {@link QualityConfiguration} instance.
     * @param item The {@link Item} that needs to be modified.
     * @return The {@link QualityOperator} which applies to the given {@link Item}.
     */
    public static QualityOperator getQualityOperator(QualityConfiguration configuration, Item item) {
        if (item == null) {
            throw new IllegalArgumentException("To get a QualityOperator a non-null item must be provided.");
        }

        if (Inventory.AGED_BRIE.equals(item.name)) {
            return createImproveQualityOperator(configuration);
        }

        if (Inventory.BACKSTAGE_TAFKAL80ETC.equals(item.name)) {
            return createBackstagePassQualityOperator(configuration);
        }

        if (Inventory.SULFURAS.equals(item.name)) {
            return createLegendaryQualityOperator(configuration);
        }

        if (Inventory.MANA_CAKE.equals(item.name)) {
            return createConjuredQualityOperator(configuration);
        }

        return createDegradeQualityOperator(configuration);
    }

    /**
     * Create a {@link QualityOperator} for degrading {@link Item}s.
     * 
     * @param configuration {@link QualityConfiguration} instance.
     * @return an instance of {@link DegradeQuality} operator.
     */
    public static DegradeQuality createDegradeQualityOperator(QualityConfiguration configuration) {
        return new DegradeQuality(configuration.getNormalQualityModifier(), configuration.getMinimumQuality());
    }

    /**
     * Create a {@link QualityOperator} for improving {@link Item}s.
     * 
     * @param configuration {@link QualityConfiguration} instance.
     * @return an instance of {@link ImproveQuality} operator.
     */
    public static ImproveQuality createImproveQualityOperator(QualityConfiguration configuration) {
        return new ImproveQuality(configuration.getNormalQualityModifier(), configuration.getMaximumQuality());
    }

    /**
     * Create a {@link QualityOperator} for legendary {@link Item}s.
     * The quality of legendary items never alters.
     * 
     * @param configuration {@link QualityConfiguration} instance.
     * @return an instance of {@link LegendaryQuality} operator.
     */
    public static LegendaryQuality createLegendaryQualityOperator(QualityConfiguration configuration) {
        return new LegendaryQuality(configuration.getLegendaryQuality());
    }

    /**
     * Create a {@link QualityOperator} for conjured {@link Item}s.
     * "Conjured" items degrade in Quality twice as fast as normal items.
     * 
     * @param configuration {@link QualityConfiguration} instance.
     * @return an instance of {@link DegradeQuality} operator for conjured items.
     */
    public static DegradeQuality createConjuredQualityOperator(QualityConfiguration configuration) {
        return new DegradeQuality(configuration.getConjuredQualityModifier(), configuration.getMinimumQuality());
    }

    /**
     * Create a {@link QualityOperator} for backstage passes.
     * Quality increases by 2 when there are 10 days or less and by 3 when there are 5 days or less.
     * 
     * @param configuration {@link QualityConfiguration} instance.
     * @return an instance of {@link ExpireQuality} operator.
     */
    public static ExpireQuality createBackstagePassQualityOperator(QualityConfiguration configuration) {
        return new ExpireQuality(configuration.getNormalQualityModifier(),
                                 configuration.getMinimumQuality(),
                                 configuration.getMaximumQuality(),
                                 configuration.getBackstagePassQualityModifiers());
    }

}
