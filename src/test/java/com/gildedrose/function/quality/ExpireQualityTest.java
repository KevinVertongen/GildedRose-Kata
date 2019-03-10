package com.gildedrose.function.quality;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import com.gildedrose.function.quality.ExpireQuality;
import com.gildedrose.model.Item;
import com.gildedrose.model.QualityModifier;


public class ExpireQualityTest {

    private static final int NORMAL_QUALITY_MODIFIER = 1;
    private static final int MINIMUM_QUALITY = 0;
    private static final int MAXIMUM_QUALITY = 50;

    private static final String EXPIRING_ITEM_NAME = "Expiring item";

    private QualityModifier tenDaysBeforeModifier;
    private QualityModifier fiveDaysBeforeModifier;
    private ExpireQuality backstagePassQualityOperator;

    @Before
    public void setUp() {
        tenDaysBeforeModifier = new QualityModifier.Builder().withDaysLeft(10)
                                                             .withAmount(2)
                                                             .build();
        fiveDaysBeforeModifier = new QualityModifier.Builder().withDaysLeft(5)
                                                              .withAmount(3)
                                                              .build();
        
        backstagePassQualityOperator = new ExpireQuality(NORMAL_QUALITY_MODIFIER, MINIMUM_QUALITY, MAXIMUM_QUALITY);
        backstagePassQualityOperator.addQualityModifier(tenDaysBeforeModifier);
        backstagePassQualityOperator.addQualityModifier(fiveDaysBeforeModifier);
    }

    @Test
    public void when_BackstagePassExpiresIn15days_then_QualityIncreasesBy1() {
        Item expiringItem = new Item(EXPIRING_ITEM_NAME, 15, 20);

        Item modifiedItem = backstagePassQualityOperator.apply(expiringItem);

        assertEquals(21, modifiedItem.quality);
    }

    @Test
    public void when_BackstagePassExpiresIn10days_then_QualityIncreasesBy2() {
        Item expiringItem = new Item(EXPIRING_ITEM_NAME, 10, 20);

        Item modifiedItem = backstagePassQualityOperator.apply(expiringItem);

        assertEquals(22, modifiedItem.quality);
    }

    @Test
    public void when_BackstagePassExpiresInLessThan10days_then_QualityIncreasesBy2() {
        Item expiringItem = new Item(EXPIRING_ITEM_NAME, 8, 20);
        
        Item modifiedItem = backstagePassQualityOperator.apply(expiringItem);
        
        assertEquals(22, modifiedItem.quality);
    }

    @Test
    public void when_BackstagePassExpiresIn5_then_QualityIncreasesBy3() {
        Item expiringItem = new Item(EXPIRING_ITEM_NAME, 5, 20);

        Item modifiedItem = backstagePassQualityOperator.apply(expiringItem);

        assertEquals(23, modifiedItem.quality);
    }

    @Test
    public void when_BackstagePassExpiresInLessThan5_then_QualityIncreasesBy3() {
        Item expiringItem = new Item(EXPIRING_ITEM_NAME, 3, 20);
        
        Item modifiedItem = backstagePassQualityOperator.apply(expiringItem);
        
        assertEquals(23, modifiedItem.quality);
    }

    @Test
    public void when_BackstagePassExpired_then_QualityIsZero() {
        Item expiringItem = new Item(EXPIRING_ITEM_NAME, 0, 50);

        Item modifiedItem = backstagePassQualityOperator.apply(expiringItem);

        assertEquals(MINIMUM_QUALITY, modifiedItem.quality);
    }

    @Test
    public void when_ItemExpiredPastSellIn_then_QualityIsZero() {
        Item expiringItem = new Item(EXPIRING_ITEM_NAME, -2, 50);
        
        Item modifiedItem = backstagePassQualityOperator.apply(expiringItem);
        
        assertEquals(MINIMUM_QUALITY, modifiedItem.quality);
    }

}
