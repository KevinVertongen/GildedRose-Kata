package com.gildedrose.function.quality;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.gildedrose.model.Item;
import com.gildedrose.model.QualityModifier;


/**
 * Modify the quality of an {@link Item} with all the given {@link QualityModifier}s.
 * Modifiers will be applied ordered by {@code daysLeft} until the expiration date.
 * Once the expiration date passed quality is set to 0.
 * 
 * @author kavert
 */
class ExpireQuality implements QualityOperator {

    // The amount of quality of an expired item.
    private static final int EXPIRED = 0;

    private final int normalModifier;
    private final int lowerLimit;
    private final int upperLimit;
    private final List<QualityModifier> qualityModifiers;

    /**
     * Initiates an ExpireQualityOperator with an empty list of {@link QualityModifier}s. 
     * 
     * @param normalModifier amount to increase or decrease the quality with if no modifiers apply.
     * @param lowerLimit minimal allowed quality.
     * @param upperLimit maximal allowed quality.
     */
    public ExpireQuality(int normalModifier, int lowerLimit, int upperLimit) {
        this(normalModifier, lowerLimit, upperLimit, null);
    }

    /**
     * Initiates an ExpireQualityOperator with the given list of {@link QualityModifier}s.
     * Modifiers will be sorted by {@code daysLeft} until the expiration date.
     * A 'lowerLimit {@link QualityModifier} will be added closing the 'range' of the last quality modifier.
     * 
     * @param normalModifier amount to increase or decrease the quality with if no modifiers apply.
     * @param lowerLimit minimal allowed quality.
     * @param upperLimit maximal allowed quality.
     * @param qualityModifiers list of {@link QualityModifier}s to apply. May be null or empty.
     */
    public ExpireQuality(int normalModifier, int lowerLimit, int upperLimit, List<QualityModifier> qualityModifiers) {
        this.normalModifier = normalModifier;
        this.lowerLimit = lowerLimit;
        this.upperLimit = upperLimit;

        this.qualityModifiers = new ArrayList<>();
        prepareQualityModifiers(qualityModifiers);
    }

    /**
     * Add all given {@link QualityModifier}s to a new List and add the lower limit {@link QualityModifier}.
     * 
     * @param qualityModifiers list of {@link QualityModifier}s to clone. Never null, may be empty.
     */
    private void prepareQualityModifiers(List<QualityModifier> qualityModifiers) {
        if (qualityModifiers != null) {
            this.qualityModifiers.addAll(qualityModifiers);
        }

        // Close the 'range' of the last quality modifier.
        QualityModifier lowerLimitQualityModifier = new QualityModifier.Builder().withDaysLeft(EXPIRED)
                                                                                 .withAmount(this.lowerLimit)
                                                                                 .build();
        this.qualityModifiers.add(lowerLimitQualityModifier);
    }

    /**
     * Add a {@link QualityModifier} to the list of modifiers to apply.
     * Modifiers will be sorted by {@code daysLeft} until the expiration date.
     * 
     * @param qualityModifier {@link QualityModifier} to add to the list.
     * @return This {@link ExpireQuality} for chaining methods.
     */
    public ExpireQuality addQualityModifier(QualityModifier qualityModifier) {
        this.qualityModifiers.add(qualityModifier);
        return this;
    }

    /**
     * Modify the quality of the provided {@link Item} with the given list of {@link QualityModifier}s.
     * Once the expiration date passed quality is set to 0.
     * The quality will never decrease below the {@code lowerLimit} nor increase over the {@code upperLimit}.
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

        if (item.sellIn <= 0) {
            item.quality = lowerLimit;
            return item;
        }

        int newQuality = item.quality + getModifierAmount(item.sellIn);

        if (newQuality < lowerLimit) {
            newQuality = lowerLimit;
        }

        if (newQuality > upperLimit) {
            newQuality = upperLimit;
        }

        item.quality = newQuality;
        return item;
    }

    /**
     * Find the amount for which we will modify the quality based on the days left until expiring.
     * Amount can be negative: then the quality decreases else the quality increases.
     * 
     * @param sellIn
     * @return an amount
     */
    private int getModifierAmount(final int sellIn) {
        // Make sure the modifiers are ordered descending by amount of days left.
        Collections.sort(qualityModifiers);

        // Sell in higher then the first modifier.
        if (sellIn > qualityModifiers.get(0)
                                     .getDaysLeft()) {
            return normalModifier;
        }

        int index = 0;
        int nextIndex = 0;
        int size = qualityModifiers.size();

        while (index < size) {
            nextIndex++;

            while (nextIndex < size) {
                QualityModifier qualityModifier = qualityModifiers.get(index);
                QualityModifier nextQualityModifier = qualityModifiers.get(nextIndex);

                // Determine if the sellIn is in range and get the amount.
                if (sellIn <= qualityModifier.getDaysLeft() && sellIn > nextQualityModifier.getDaysLeft()) {
                    return qualityModifier.getAmount();
                } else if (index < nextIndex) {
                    index++;
                } else {
                    break;
                }
            }

        }

        return 0;
    }

}
