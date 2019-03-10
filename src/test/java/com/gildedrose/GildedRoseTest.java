package com.gildedrose;

import static org.junit.Assert.assertEquals;

import org.junit.Test;


public class GildedRoseTest {

    private static final String DEGRADING_ITEM_NAME = "Basic item";
    private static final String IMPROVING_ITEM_NAME = "Aged Brie"; // TODO: "Improving item"
    private static final String EXPIRING_ITEM_NAME = "Backstage passes to a TAFKAL80ETC concert"; // TODO "Expiring item"
    private static final String LEGENDARY_ITEM_NAME = "Sulfuras, Hand of Ragnaros"; // TODO "Legendary item"
    private static final String CONJURED_ITEM_NAME = "Conjured item";

    /*
     * Degrading item
     */
    @Test
    public void when_ItemDegrades_Then_SellInDecreases() {
        Item basicItem = new Item(DEGRADING_ITEM_NAME, 10, 20);
        Item[] basicItems = new Item[] {basicItem};
        GildedRose gildedRose = new GildedRose(basicItems);

        gildedRose.updateQuality();

        assertEquals(9, gildedRose.items[0].sellIn);
    }

    @Test
    public void when_ItemDegrades_then_QualityDecreases() {
        Item basicItem = new Item(DEGRADING_ITEM_NAME, 10, 20);
        Item[] basicItems = new Item[] {basicItem};
        GildedRose gildedRose = new GildedRose(basicItems);

        gildedRose.updateQuality();

        assertEquals(19, gildedRose.items[0].quality);
    }

    @Test
    public void when_ItemDegrades_then_QualityNotNegative() {
        Item basicItem = new Item(DEGRADING_ITEM_NAME, 10, 0);
        Item[] basicItems = new Item[] {basicItem};
        GildedRose gildedRose = new GildedRose(basicItems);

        gildedRose.updateQuality();

        assertEquals(0, gildedRose.items[0].quality);
    }

    @Test
    public void when_ItemDegrades_then_QualityMax50() {
        Item basicItem = new Item(DEGRADING_ITEM_NAME, 10, 50);
        Item[] basicItems = new Item[] {basicItem};
        GildedRose gildedRose = new GildedRose(basicItems);

        gildedRose.updateQuality();

        assertEquals(49, gildedRose.items[0].quality);
    }

    /*
     * Improving item
     */
    @Test
    public void when_ItemImproves_then_SellInDecreases() {
        Item improvingItem = new Item(IMPROVING_ITEM_NAME, 5, 1);
        Item[] agingItems = new Item[] {improvingItem};
        GildedRose gildedRose = new GildedRose(agingItems);

        gildedRose.updateQuality();

        assertEquals(4, gildedRose.items[0].sellIn);
    }

    @Test
    public void when_ItemImproves_then_QualityIncreases() {
        Item improvingItem = new Item(IMPROVING_ITEM_NAME, 5, 1);
        Item[] agingItems = new Item[] {improvingItem};
        GildedRose gildedRose = new GildedRose(agingItems);

        gildedRose.updateQuality();

        assertEquals(2, gildedRose.items[0].quality);
    }

    @Test
    public void when_ItemImproves_then_QualityNotNegative() {
        Item improvingItem = new Item(IMPROVING_ITEM_NAME, 5, 0);
        Item[] agingItems = new Item[] {improvingItem};
        GildedRose gildedRose = new GildedRose(agingItems);

        gildedRose.updateQuality();

        assertEquals(1, gildedRose.items[0].quality);
    }

    @Test
    public void when_ItemImproves_then_QualityMax50() {
        Item improvingItem = new Item(IMPROVING_ITEM_NAME, 5, 50);
        Item[] agingItems = new Item[] {improvingItem};
        GildedRose gildedRose = new GildedRose(agingItems);

        gildedRose.updateQuality();

        assertEquals(50, gildedRose.items[0].quality);
    }

    /*
     * Expiring item
     */
    @Test
    public void when_ItemExpires_then_SellInDecreases() {
        Item expiringItem = new Item(EXPIRING_ITEM_NAME, 15, 20);
        Item[] expiringItems = new Item[] {expiringItem};
        GildedRose gildedRose = new GildedRose(expiringItems);

        gildedRose.updateQuality();

        assertEquals(14, gildedRose.items[0].sellIn);
    }

    @Test
    public void when_ItemExpires_then_QualityIncreases() {
        Item expiringItem = new Item(EXPIRING_ITEM_NAME, 15, 20);
        Item[] expiringItems = new Item[] {expiringItem};
        GildedRose gildedRose = new GildedRose(expiringItems);

        gildedRose.updateQuality();

        assertEquals(21, gildedRose.items[0].quality);
    }

    @Test
    public void when_ItemExpires_then_QualityNotNegative() {
        Item expiringItem = new Item(EXPIRING_ITEM_NAME, 15, 0);
        Item[] expiringItems = new Item[] {expiringItem};
        GildedRose gildedRose = new GildedRose(expiringItems);

        gildedRose.updateQuality();

        assertEquals(1, gildedRose.items[0].quality);
    }

    @Test
    public void when_ItemExpires_then_QualityMax50() {
        Item expiringItem = new Item(EXPIRING_ITEM_NAME, 15, 50);
        Item[] expiringItems = new Item[] {expiringItem};
        GildedRose gildedRose = new GildedRose(expiringItems);

        gildedRose.updateQuality();

        assertEquals(50, gildedRose.items[0].quality);
    }

    @Test
    public void when_ItemExpiresIn10daysOrLess_then_QualityIncreasesBy2() {
        Item expiringItem = new Item(EXPIRING_ITEM_NAME, 10, 20);
        Item[] expiringItems = new Item[] {expiringItem};
        GildedRose gildedRose = new GildedRose(expiringItems);

        gildedRose.updateQuality();

        assertEquals(22, gildedRose.items[0].quality);
    }

    @Test
    public void when_ItemExpiresIn10daysOrLess_then_QualityIncreasesBy2_QualityMax50() {
        Item expiringItem = new Item(EXPIRING_ITEM_NAME, 10, 50);
        Item[] expiringItems = new Item[] {expiringItem};
        GildedRose gildedRose = new GildedRose(expiringItems);
        
        gildedRose.updateQuality();
        
        assertEquals(50, gildedRose.items[0].quality);
    }

    @Test
    public void when_ItemExpiresIn5daysOrLess_then_QualityIncreasesBy3() {
        Item expiringItem = new Item(EXPIRING_ITEM_NAME, 5, 20);
        Item[] expiringItems = new Item[] {expiringItem};
        GildedRose gildedRose = new GildedRose(expiringItems);

        gildedRose.updateQuality();

        assertEquals(23, gildedRose.items[0].quality);
    }

    @Test
    public void when_ItemExpiresIn5daysOrLess_then_QualityIncreasesBy3_QualityMax50() {
        Item expiringItem = new Item(EXPIRING_ITEM_NAME, 5, 50);
        Item[] expiringItems = new Item[] {expiringItem};
        GildedRose gildedRose = new GildedRose(expiringItems);
        
        gildedRose.updateQuality();
        
        assertEquals(50, gildedRose.items[0].quality);
    }

    @Test
    public void when_ItemExpired_then_QualityIsZero() {
        Item expiringItem = new Item(EXPIRING_ITEM_NAME, 0, 50);
        Item[] expiringItems = new Item[] {expiringItem};
        GildedRose gildedRose = new GildedRose(expiringItems);

        gildedRose.updateQuality();

        assertEquals(0, gildedRose.items[0].quality);
    }

    /*
     * Legendary item
     */
    @Test
    public void when_ItemLegendary_then_SellInNotChanged() {
        Item legendaryItem = new Item(LEGENDARY_ITEM_NAME, 0, 80);
        Item[] legendaryItems = new Item[] {legendaryItem};
        GildedRose gildedRose = new GildedRose(legendaryItems);

        gildedRose.updateQuality();

        assertEquals(0, gildedRose.items[0].sellIn);
    }

    @Test
    public void when_ItemLegendary_then_QualityNotChanged() {
        Item legendaryItem = new Item(LEGENDARY_ITEM_NAME, 0, 80);
        Item[] legendaryItems = new Item[] {legendaryItem};
        GildedRose gildedRose = new GildedRose(legendaryItems);

        gildedRose.updateQuality();

        assertEquals(80, gildedRose.items[0].quality);
    }

    @Test
    public void when_ItemLegendary_then_QualityIs80() {
        Item legendaryItem = new Item(LEGENDARY_ITEM_NAME, 0, 80);
        Item[] legendaryItems = new Item[] {legendaryItem};
        GildedRose gildedRose = new GildedRose(legendaryItems);

        gildedRose.updateQuality();

        assertEquals(80, gildedRose.items[0].quality);
    }

    /*
     * Conjured item
     */
    @Test
    public void when_ItemConjured_then_SellInDecreases() {
        Item conjuredItem = new Item(CONJURED_ITEM_NAME, 3, 6);
        Item[] conjuredItems = new Item[] {conjuredItem};
        GildedRose gildedRose = new GildedRose(conjuredItems);

        gildedRose.updateQuality();

        assertEquals(2, gildedRose.items[0].sellIn);
    }

    @Test
    public void when_ItemConjured_then_QualityDecreasesTwiceAsFast() {
        Item conjuredItem = new Item(CONJURED_ITEM_NAME, 3, 6);
        Item[] conjuredItems = new Item[] {conjuredItem};
        GildedRose gildedRose = new GildedRose(conjuredItems);

        gildedRose.updateQuality();

        assertEquals(4, gildedRose.items[0].quality);
    }

    @Test
    public void when_ItemConjured_then_QualityNotNegative() {
        Item conjuredItem = new Item(CONJURED_ITEM_NAME, 3, 0);
        Item[] conjuredItems = new Item[] {conjuredItem};
        GildedRose gildedRose = new GildedRose(conjuredItems);

        gildedRose.updateQuality();

        assertEquals(0, gildedRose.items[0].quality);
    }

    @Test
    public void when_ItemConjured_then_QualityMax50() {
        Item conjuredItem = new Item(CONJURED_ITEM_NAME, 3, 50);
        Item[] conjuredItems = new Item[] {conjuredItem};
        GildedRose gildedRose = new GildedRose(conjuredItems);

        gildedRose.updateQuality();

        assertEquals(48, gildedRose.items[0].quality);
    }
}
