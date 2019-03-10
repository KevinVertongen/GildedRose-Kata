package com.gildedrose.config.quality;

import com.gildedrose.model.Item;
import com.gildedrose.model.QualityModifier;


public class QualityConfigurationFactory {

    /**
     * The quality of an item is never negative.
     */
    private static final int MINIMUM = 0;

    /**
     * The quality of an item is never more than 50, with the exception of legendary items.
     */
    private static final int MAXIMUM = 50;

    /**
     * Legendary items have a constant quality of 80.
     */
    private static final int LEGENDARY = 80;

    /**
     * "Normal" items degrade in quality by 1.
     */
    private static final int NORMAL_MODIFIER = 1;

    /**
     * "Conjured" items degrade in quality twice as fast as normal items.
     */
    private static final int CONJURED_MODIFIER = NORMAL_MODIFIER * 2;

    /**
     * "Backstage passes" quality increases by 2 when there are 10 days or less.
     */
    private static final QualityModifier TEN_DAYS_BEFORE_MODIFIER = createItemQualityModifier(10, 2);

    /**
     * "Backstage passes" quality increases by 3 when there are 5 days or less.
     */
    private static final QualityModifier FIVE_DAYS_BEFORE_MODIFIER = createItemQualityModifier(5, 3);

    /**
     * create a new ItemQualityConfiguration object with default values.
     * 
     * @return {@link QualityConfiguration}
     */
    public static QualityConfiguration createItemQualityConfiguration() {
        return new QualityConfiguration.Builder()
                                                 .withMinimumQuality(MINIMUM)
                                                 .withMaximumQuality(MAXIMUM)
                                                 .withLegendaryQuality(LEGENDARY)
                                                 .withNormalQualityModifier(NORMAL_MODIFIER)
                                                 .withConjuredQualityModifier(CONJURED_MODIFIER)
                                                 .withBackstagePassQualityModifier(TEN_DAYS_BEFORE_MODIFIER)
                                                 .withBackstagePassQualityModifier(FIVE_DAYS_BEFORE_MODIFIER)
                                                 .build();
    }
    
    /**
     * create a new QualityModifier object.
     * 
     * @param daysLeft Amount of days left before the {@link Item} expires.Can <i>not</i> be negative.
     * @param amount How much the quality is modified. When negative the quality decreases else the quality increases.
     * @return {@link QualityModifier}
     */
    public static QualityModifier createItemQualityModifier(final int daysLeft, final int amount) {
        return new QualityModifier.Builder().withDaysLeft(daysLeft)
                                            .withAmount(amount)
                                            .build();
    }
    
} 
