package com.gildedrose.model;

/**
 * Indicates how much the amount of an {@link Item} changes when an amount of days are left before that {@link Item} expires.<br>
 * Provide a positive {@code amount} value to increase the amount of the {@link Item}.<br>
 * Provide a negative {@code amount} value to decrease the amount of the {@link Item}.
 * 
 * @author kavert
 */
public final class QualityModifier implements Comparable<QualityModifier> {

    private final int daysLeft;
    private final int amount;

    /*
     * Can only be used by the inner class Builder, making the object immutable.
     */
    private QualityModifier(Builder builder) {
        this.daysLeft = builder.daysLeft;
        this.amount = builder.amount;
    }

    /**
     * Amount of days left before the {@link Item} expires. Never negative.
     * 
     * @return the daysLeft
     */
    public int getDaysLeft() {
        return daysLeft;
    }

    /**
     * How much the amount is modified: when negative the amount decreases.
     * 
     * @return the amount added to or subtracted from the {@link Item} amount 
     */
    public int getAmount() {
        return amount;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + daysLeft;
        result = prime * result + amount;
        return result;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        QualityModifier other = (QualityModifier) obj;
        if (daysLeft != other.daysLeft) {
            return false;
        }
        if (amount != other.amount) {
            return false;
        }
        return true;
    }

    /**
     * Ordered descending by amount of days left.
     * 
     * @see java.lang.Comparable#compareTo(java.lang.Object)
     */
    @Override
    public int compareTo(QualityModifier other) {
        return Integer.compare(other.daysLeft, this.daysLeft);
    }

    /**
     * String representation of this {@link QualityModifier}.
     * 
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return "QualityModifier [daysLeft=" + daysLeft + ", amount=" + amount + "]";
    }

    /**
     * Builds (instantiates) a new {@link QualityModifier} object with the given {@code daysLeft} and {@code amount}.
     * 
     * @author kavert
     */
    public static final class Builder {

        private int daysLeft;
        private int amount;

        /**
         * Amount of days left before the {@link Item} expires. 
         * 
         * @param daysLeft Can <i>not</i> be negative.
         * @return This {@link Builder} object for chaining methods.
         */
        public Builder withDaysLeft(int daysLeft) {
            if (daysLeft < 0) {
                throw new IllegalArgumentException("The amount of days left before an item expires can not be negative.");
            }

            this.daysLeft = daysLeft;
            return this;
        }

        /**
         * How much the amount is modified.
         * 
         * @param amount When negative the amount decreases else the amount increases.
         * @return This {@link Builder} object for chaining methods.
         */
        public Builder withAmount(int amount) {
            this.amount = Math.abs(amount);
            return this;
        }

        /**
         * Instantiates a new {@link QualityModifier} object with the given attributes.
         * 
         * @return a new immutable QualityModifier object.
         */
        public QualityModifier build() {
            return new QualityModifier(this);
        }

    }

}
