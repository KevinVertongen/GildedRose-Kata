package com.gildedrose;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import com.gildedrose.model.Item;


public class GildedRoseTest {

    private static final int MINIMUM_QUALITY = 0;
    private static final int MAXIMUM_QUALITY = 50;
    private static final int LEGENDARY_QUALITY = 80;

    private static final String DEGRADING_ITEM_NAME = "Basic item";
    private static final String IMPROVING_ITEM_NAME = "Aged Brie"; // "Improving item"
    private static final String EXPIRING_ITEM_NAME = "Backstage passes to a TAFKAL80ETC concert"; // "Expiring item"
    private static final String LEGENDARY_ITEM_NAME = "Sulfuras, Hand of Ragnaros"; // "Legendary item"
    private static final String CONJURED_ITEM_NAME = "Conjured Mana Cake"; // "Conjured item"

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
    public void when_ItemDegrades_then_QualityNotUnderMinimum() {
        Item basicItem = new Item(DEGRADING_ITEM_NAME, 10, 0);
        Item[] basicItems = new Item[] {basicItem};
        GildedRose gildedRose = new GildedRose(basicItems);

        gildedRose.updateQuality();

        assertEquals(MINIMUM_QUALITY, gildedRose.items[0].quality);
    }

    @Test
    public void when_ItemDegrades_then_QualityNotOverMaximum() {
        Item basicItem = new Item(DEGRADING_ITEM_NAME, 10, 50);
        Item[] basicItems = new Item[] {basicItem};
        GildedRose gildedRose = new GildedRose(basicItems);

        gildedRose.updateQuality();

        assertEquals(49, gildedRose.items[0].quality);
    }

    @Test
    public void when_ItemDegradesAndSellByDateToday_then_QualityDecreasesTwiceAsFast() {
        Item basicItem = new Item(DEGRADING_ITEM_NAME, 0, 20);
        Item[] basicItems = new Item[] {basicItem};
        GildedRose gildedRose = new GildedRose(basicItems);
        
        gildedRose.updateQuality();
        
        assertEquals(18, gildedRose.items[0].quality);
    }

    @Test
    public void when_ItemDegradesAndSellByDatePast_then_QualityDecreasesTwiceAsFast() {
        Item basicItem = new Item(DEGRADING_ITEM_NAME, -1, 20);
        Item[] basicItems = new Item[] {basicItem};
        GildedRose gildedRose = new GildedRose(basicItems);
        
        gildedRose.updateQuality();
        
        assertEquals(18, gildedRose.items[0].quality);
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
    public void when_ItemImproves_then_QualityNotUnderMinimum() {
        Item improvingItem = new Item(IMPROVING_ITEM_NAME, 5, 0);
        Item[] agingItems = new Item[] {improvingItem};
        GildedRose gildedRose = new GildedRose(agingItems);

        gildedRose.updateQuality();

        assertEquals(1, gildedRose.items[0].quality);
    }

    @Test
    public void when_ItemImproves_then_QualityNotOverMaximum() {
        Item improvingItem = new Item(IMPROVING_ITEM_NAME, 5, 50);
        Item[] agingItems = new Item[] {improvingItem};
        GildedRose gildedRose = new GildedRose(agingItems);

        gildedRose.updateQuality();

        assertEquals(MAXIMUM_QUALITY, gildedRose.items[0].quality);
    }
    
    @Test
    public void when_ItemImprovesAndSellByDateToday_then_QualityIncreasesTwiceAsFast() {
        Item improvingItem = new Item(IMPROVING_ITEM_NAME, 0, 1);
        Item[] agingItems = new Item[] {improvingItem};
        GildedRose gildedRose = new GildedRose(agingItems);
        
        gildedRose.updateQuality();
        
        assertEquals(3, gildedRose.items[0].quality);
    }

    @Test
    public void when_ItemImprovesAndSellByDatePast_then_QualityIncreasesTwiceAsFast() {
        Item improvingItem = new Item(IMPROVING_ITEM_NAME, -1, 1);
        Item[] agingItems = new Item[] {improvingItem};
        GildedRose gildedRose = new GildedRose(agingItems);
        
        gildedRose.updateQuality();
        
        assertEquals(3, gildedRose.items[0].quality);
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
    public void when_ItemExpires_then_QualityNotUnderMinimum() {
        Item expiringItem = new Item(EXPIRING_ITEM_NAME, 15, 0);
        Item[] expiringItems = new Item[] {expiringItem};
        GildedRose gildedRose = new GildedRose(expiringItems);

        gildedRose.updateQuality();

        assertEquals(1, gildedRose.items[0].quality);
    }

    @Test
    public void when_ItemExpires_then_QualityNotOverMaximum() {
        Item expiringItem = new Item(EXPIRING_ITEM_NAME, 15, 50);
        Item[] expiringItems = new Item[] {expiringItem};
        GildedRose gildedRose = new GildedRose(expiringItems);

        gildedRose.updateQuality();

        assertEquals(MAXIMUM_QUALITY, gildedRose.items[0].quality);
    }

    @Test
    public void when_ItemExpiresIn10days_then_QualityIncreasesBy2() {
        Item expiringItem = new Item(EXPIRING_ITEM_NAME, 10, 20);
        Item[] expiringItems = new Item[] {expiringItem};
        GildedRose gildedRose = new GildedRose(expiringItems);

        gildedRose.updateQuality();

        assertEquals(22, gildedRose.items[0].quality);
    }

    @Test
    public void when_ItemExpiresInLessThan10days_then_QualityIncreasesBy2() {
        Item expiringItem = new Item(EXPIRING_ITEM_NAME, 8, 20);
        Item[] expiringItems = new Item[] {expiringItem};
        GildedRose gildedRose = new GildedRose(expiringItems);
        
        gildedRose.updateQuality();
        
        assertEquals(22, gildedRose.items[0].quality);
    }

    @Test
    public void when_ItemExpiresIn10days_then_QualityIncreasesBy2_QualityNotOverMaximum() {
        Item expiringItem = new Item(EXPIRING_ITEM_NAME, 10, 50);
        Item[] expiringItems = new Item[] {expiringItem};
        GildedRose gildedRose = new GildedRose(expiringItems);

        gildedRose.updateQuality();

        assertEquals(MAXIMUM_QUALITY, gildedRose.items[0].quality);
    }

    @Test
    public void when_ItemExpiresInLessThan10days_then_QualityIncreasesBy2_QualityNotOverMaximum() {
        Item expiringItem = new Item(EXPIRING_ITEM_NAME, 8, 50);
        Item[] expiringItems = new Item[] {expiringItem};
        GildedRose gildedRose = new GildedRose(expiringItems);
        
        gildedRose.updateQuality();
        
        assertEquals(MAXIMUM_QUALITY, gildedRose.items[0].quality);
    }

    @Test
    public void when_ItemExpiresIn5days_then_QualityIncreasesBy3() {
        Item expiringItem = new Item(EXPIRING_ITEM_NAME, 5, 20);
        Item[] expiringItems = new Item[] {expiringItem};
        GildedRose gildedRose = new GildedRose(expiringItems);

        gildedRose.updateQuality();

        assertEquals(23, gildedRose.items[0].quality);
    }
    
    @Test
    public void when_ItemExpiresInLessThan5days_then_QualityIncreasesBy3() {
        Item expiringItem = new Item(EXPIRING_ITEM_NAME, 3, 20);
        Item[] expiringItems = new Item[] {expiringItem};
        GildedRose gildedRose = new GildedRose(expiringItems);
        
        gildedRose.updateQuality();
        
        assertEquals(23, gildedRose.items[0].quality);
    }

    @Test
    public void when_ItemExpiresIn5days_then_QualityIncreasesBy3_QualityNotOverMaximum() {
        Item expiringItem = new Item(EXPIRING_ITEM_NAME, 5, 50);
        Item[] expiringItems = new Item[] {expiringItem};
        GildedRose gildedRose = new GildedRose(expiringItems);

        gildedRose.updateQuality();

        assertEquals(MAXIMUM_QUALITY, gildedRose.items[0].quality);
    }

    @Test
    public void when_ItemExpiresInLessThan5days_then_QualityIncreasesBy3_QualityNotOverMaximum() {
        Item expiringItem = new Item(EXPIRING_ITEM_NAME, 3, 50);
        Item[] expiringItems = new Item[] {expiringItem};
        GildedRose gildedRose = new GildedRose(expiringItems);
        
        gildedRose.updateQuality();
        
        assertEquals(MAXIMUM_QUALITY, gildedRose.items[0].quality);
    }

    @Test
    public void when_ItemExpired_then_QualityIsMinimum() {
        Item expiringItem = new Item(EXPIRING_ITEM_NAME, 0, 33);
        Item[] expiringItems = new Item[] {expiringItem};
        GildedRose gildedRose = new GildedRose(expiringItems);

        gildedRose.updateQuality();

        assertEquals(MINIMUM_QUALITY, gildedRose.items[0].quality);
    }

    @Test
    public void when_ItemExpiredPastSellIn_then_QualityIsMinimum() {
        Item expiringItem = new Item(EXPIRING_ITEM_NAME, -2, 50);
        Item[] expiringItems = new Item[] {expiringItem};
        GildedRose gildedRose = new GildedRose(expiringItems);
        
        gildedRose.updateQuality();
        
        assertEquals(MINIMUM_QUALITY, gildedRose.items[0].quality);
    }

    /*
     * Legendary item
     */
    @Test
    public void when_ItemLegendary_then_SellInNotChanged() {
        Item legendaryItem = new Item(LEGENDARY_ITEM_NAME, 0, LEGENDARY_QUALITY);
        Item[] legendaryItems = new Item[] {legendaryItem};
        GildedRose gildedRose = new GildedRose(legendaryItems);

        gildedRose.updateQuality();

        assertEquals(0, gildedRose.items[0].sellIn);
    }

    @Test
    public void when_ItemLegendary_then_QualityNotChanged() {
        Item legendaryItem = new Item(LEGENDARY_ITEM_NAME, 0, LEGENDARY_QUALITY);
        Item[] legendaryItems = new Item[] {legendaryItem};
        GildedRose gildedRose = new GildedRose(legendaryItems);

        gildedRose.updateQuality();

        assertEquals(LEGENDARY_QUALITY, gildedRose.items[0].quality);
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
    public void when_ItemConjured_then_QualityNotUnderMinimum() {
        Item conjuredItem = new Item(CONJURED_ITEM_NAME, 3, 0);
        Item[] conjuredItems = new Item[] {conjuredItem};
        GildedRose gildedRose = new GildedRose(conjuredItems);

        gildedRose.updateQuality();

        assertEquals(MINIMUM_QUALITY, gildedRose.items[0].quality);
    }

    @Test
    public void when_ItemConjured_then_QualityNotOverMaximum() {
        Item conjuredItem = new Item(CONJURED_ITEM_NAME, 3, 50);
        Item[] conjuredItems = new Item[] {conjuredItem};
        GildedRose gildedRose = new GildedRose(conjuredItems);

        gildedRose.updateQuality();

        assertEquals(48, gildedRose.items[0].quality);
    }
}
