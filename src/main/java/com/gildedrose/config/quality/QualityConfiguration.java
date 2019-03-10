package com.gildedrose.config.quality;

import java.util.ArrayList;
import java.util.List;

import com.gildedrose.model.QualityModifier;


/**
 * Configuration of the Quality operations.
 * 
 * @author kavert
 */
public final class QualityConfiguration {

    private final int minimumQuality;
    private final int maximumQuality;
    private final int legendaryQuality;

    private final int normalQualityModifier;
    private final int conjuredQualityModifier;
    private final List<QualityModifier> backstagePassQualityModifiers;

    private QualityConfiguration(Builder builder) {
        this.minimumQuality = builder.minimumQuality;
        this.maximumQuality = builder.maximumQuality;
        this.legendaryQuality = builder.legendaryQuality;

        this.normalQualityModifier = builder.normalQualityModifier;
        this.conjuredQualityModifier = builder.conjuredQualityModifier;
        this.backstagePassQualityModifiers = builder.backstagePassQualityModifiers;
    }

    /**
     * @return the minimum allowed item quality
     */
    public int getMinimumQuality() {
        return minimumQuality;
    }

    /**
     * @return the maximum allowed item quality
     */
    public int getMaximumQuality() {
        return maximumQuality;
    }

    /**
     * @return the quality of a "legendary" item
     */
    public int getLegendaryQuality() {
        return legendaryQuality;
    }

    /**
     * @return the modifier of a "normal" item quality
     */
    public int getNormalQualityModifier() {
        return normalQualityModifier;
    }

    /**
     * @return the modifier of a "conjured" item quality
     */
    public int getConjuredQualityModifier() {
        return conjuredQualityModifier;
    }

    /**
     * @return list of quality modifiers for "backstage pass" items
     */
    public List<QualityModifier> getBackstagePassQualityModifiers() {
        return backstagePassQualityModifiers;
    }

    /**
     * Builds (instantiates) a new {@link QualityConfiguration} object with the given attributes.
     * 
     * @author kavert
     */
    public static final class Builder {

        private int minimumQuality;
        private int maximumQuality;
        private int legendaryQuality;

        private int normalQualityModifier;
        private int conjuredQualityModifier;
        private List<QualityModifier> backstagePassQualityModifiers = new ArrayList<>();

        /**
         * @param minimumQuality the minimumQuality to set
         * @return This {@link Builder} object for chaining methods.
         */
        public Builder withMinimumQuality(int minimumQuality) {
            this.minimumQuality = minimumQuality;
            return this;
        }

        /**
         * @param maximumQuality the maximumQuality to set
         * @return This {@link Builder} object for chaining methods.
         */
        public Builder withMaximumQuality(int maximumQuality) {
            this.maximumQuality = maximumQuality;
            return this;
        }

        /**
         * @param legendaryQuality the legendaryQuality to set
         * @return This {@link Builder} object for chaining methods.
         */
        public Builder withLegendaryQuality(int legendaryQuality) {
            this.legendaryQuality = legendaryQuality;
            return this;
        }

        /**
         * @param normalQualityModifier the normalQualityModifier to set
         * @return This {@link Builder} object for chaining methods.
         */
        public Builder withNormalQualityModifier(int normalQualityModifier) {
            this.normalQualityModifier = normalQualityModifier;
            return this;
        }

        /**
         * @param conjuredQualityModifier the conjuredQualityModifier to set
         * @return This {@link Builder} object for chaining methods.
         */
        public Builder withConjuredQualityModifier(int conjuredQualityModifier) {
            this.conjuredQualityModifier = conjuredQualityModifier;
            return this;
        }

        /**
         * @param backstagePassQualityModifier the {@link QualityModifier} to add to the list of backstage pass modifiers
         * @return This {@link Builder} object for chaining methods.
         */
        public Builder withBackstagePassQualityModifier(QualityModifier backstagePassQualityModifier) {
            this.backstagePassQualityModifiers.add(backstagePassQualityModifier);
            return this;
        }

        /**
         * Instantiates a new {@link QualityConfiguration} object with the given attributes.
         * 
         * @return a new immutable QualityConfiguration object.
         */
        public QualityConfiguration build() {
            return new QualityConfiguration(this);
        }

    }

}
