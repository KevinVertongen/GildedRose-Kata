package com.gildedrose.function.quality;

import java.util.function.UnaryOperator;

import com.gildedrose.model.Item;


/**
 * Represents an unary operation on the Quality of an {@link Item} in our inventory.<br>
 * Common operations are {@link DegradeQuality}, {@link ImproveQuality} or {@link ExpireQuality}.
 * 
 * @author kavert
 * 
 * @see UnaryOperator
 */
@FunctionalInterface
public interface QualityOperator extends UnaryOperator<Item> {

}
